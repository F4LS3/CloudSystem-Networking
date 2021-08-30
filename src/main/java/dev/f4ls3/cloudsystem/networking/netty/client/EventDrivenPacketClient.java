package dev.f4ls3.cloudsystem.networking.netty.client;

import dev.f4ls3.cloudsystem.networking.Packet;
import dev.f4ls3.cloudsystem.networking.utils.Utilities;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.InetAddress;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;

public class EventDrivenPacketClient extends ChannelInitializer<SocketChannel> {

    private final HashMap<Integer, Class<? extends Packet>> packets;


    private SelfSignedCertificate certificate;
    private SslContext sslContext;

    public EventDrivenPacketClient() {
        this.packets = new HashMap<>();

        try {
            this.certificate = new SelfSignedCertificate();
            this.sslContext = SslContext.newServerContext(this.certificate.certificate(), this.certificate.privateKey());
        } catch (CertificateException | SSLException e) {
            e.printStackTrace();
        }
    }

    public void connect(InetAddress address, int port) {
        EventLoopGroup workerGroup = Utilities.EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workerGroup);
        bootstrap.channel(Utilities.EPOLL ? EpollSocketChannel.class : NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(this);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new ByteToMessageDecoder() {
                    @Override
                    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                        int id = in.readInt();

                        if(id == -1) throw new IOException("NETWORKING_PACKET_DECODER: Invalid packet id");

                        Packet packet = packets.get(id).getDeclaredConstructor().newInstance();
                        ByteBufInputStream bufferStream = new ByteBufInputStream(in);

                        //packet.read(bufferStream);
                    }
                });
    }
}
