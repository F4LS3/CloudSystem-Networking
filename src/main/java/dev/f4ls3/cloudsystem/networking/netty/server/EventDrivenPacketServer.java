package dev.f4ls3.cloudsystem.networking.netty.server;

import dev.f4ls3.cloudsystem.eventbus.EventPublisher;
import dev.f4ls3.cloudsystem.networking.Packet;
import dev.f4ls3.cloudsystem.networking.events.server.*;
import dev.f4ls3.cloudsystem.networking.utils.Session;
import dev.f4ls3.cloudsystem.networking.utils.Utilities;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EventDrivenPacketServer extends ChannelInitializer<SocketChannel> {

    private final HashMap<Integer, Class<? extends Packet>> packets;

    private SelfSignedCertificate certificate;
    private SslContext sslContext;

    public EventDrivenPacketServer() {
        this.packets = new HashMap<>();

        try {
            this.certificate = new SelfSignedCertificate();
            this.sslContext = SslContext.newServerContext(this.certificate.certificate(), this.certificate.privateKey());
        } catch (CertificateException | SSLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(sslContext.newHandler(ch.alloc()))
                .addLast(new ByteToMessageDecoder() {
                    @Override
                    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                        int id = in.readInt();
                        if (id == -1) throw new IOException("NETWORKING_PACKET_DECODER: Invalid packet id");

                        Packet packet = packets.get(id).getDeclaredConstructor().newInstance();
                        ByteBufInputStream bufferStream = new ByteBufInputStream(in);

                        packet.read(bufferStream);
                        out.add(packet);

                        EventPublisher.PUBLISHER.publish(new ServerPacketDecodeEvent(ctx, bufferStream, packet));
                    }
                })
                .addLast(new MessageToByteEncoder<Packet>() {
                    @Override
                    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
                        int id = packets.entrySet()
                                .stream()
                                .filter(entry -> Objects.equals(entry.getValue(), packet.getClass()))
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElse(-1);

                        if (id == -1) throw new IOException("NETWORKING_PACKET_ENCODER: Invalid packet id");

                        ByteBufOutputStream bufferStream = new ByteBufOutputStream(out);

                        bufferStream.writeInt(id);
                        packet.write(bufferStream);

                        EventPublisher.PUBLISHER.publish(new ServerPacketEncodeEvent(ctx, bufferStream, packet));
                    }
                })
                .addLast(new SimpleChannelInboundHandler<Packet>() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        EventPublisher.PUBLISHER.publish(new ServerChannelActiveEvent(ctx));
                    }

                    @Override
                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                        EventPublisher.PUBLISHER.publish(new ServerChannelInactiveEvent(ctx));
                    }

                    @Override
                    protected void messageReceived(ChannelHandlerContext ctx, Packet packet) throws Exception {
                        EventPublisher.PUBLISHER.publish(new ServerPacketReceivedEvent(ctx, packet));
                    }
                });
    }

    public void listen(int port) {
        EventLoopGroup bossGroup = Utilities.EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workerGroup = Utilities.EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(Utilities.EPOLL ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
        bootstrap.childHandler(this);

        bootstrap.option(ChannelOption.SO_BACKLOG, 50);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture future = bootstrap.bind(port);
        EventPublisher.PUBLISHER.publish(new ServerListeningEvent(future, port));
    }
}
