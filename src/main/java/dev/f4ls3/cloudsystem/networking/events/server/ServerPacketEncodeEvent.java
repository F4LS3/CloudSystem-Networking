package dev.f4ls3.cloudsystem.networking.events.server;

import dev.f4ls3.cloudsystem.eventbus.Event;
import dev.f4ls3.cloudsystem.networking.Packet;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;

public class ServerPacketEncodeEvent extends Event {

    private ChannelHandlerContext ctx;
    private ByteBufOutputStream bufferStream;
    private Packet packet;

    public ServerPacketEncodeEvent() {
        super(1);
    }

    public ServerPacketEncodeEvent(ChannelHandlerContext ctx, ByteBufOutputStream bufferStream, Packet packet) {
        super(1);
        this.ctx = ctx;
        this.bufferStream = bufferStream;
        this.packet = packet;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public ByteBufOutputStream getBufferStream() {
        return bufferStream;
    }

    public Packet getPacket() {
        return packet;
    }
}
