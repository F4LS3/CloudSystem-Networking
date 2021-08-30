package dev.f4ls3.cloudsystem.networking.events.server;

import dev.f4ls3.cloudsystem.eventbus.Event;
import dev.f4ls3.cloudsystem.networking.Packet;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;

public class ServerPacketDecodeEvent extends Event {

    private ChannelHandlerContext ctx;
    private ByteBufInputStream bufferStream;
    private Packet packet;

    public ServerPacketDecodeEvent() {
        super(0);
    }

    public ServerPacketDecodeEvent(ChannelHandlerContext ctx, ByteBufInputStream bufferStream, Packet packet) {
        super(0);
        this.ctx = ctx;
        this.bufferStream = bufferStream;
        this.packet = packet;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public ByteBufInputStream getBufferStream() {
        return bufferStream;
    }

    public Packet getPacket() {
        return packet;
    }
}
