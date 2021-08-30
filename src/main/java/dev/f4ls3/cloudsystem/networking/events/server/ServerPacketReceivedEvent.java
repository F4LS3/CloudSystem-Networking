package dev.f4ls3.cloudsystem.networking.events.server;

import dev.f4ls3.cloudsystem.eventbus.Event;
import dev.f4ls3.cloudsystem.networking.Packet;
import io.netty.channel.ChannelHandlerContext;

public class ServerPacketReceivedEvent extends Event {

    private ChannelHandlerContext ctx;
    private Packet packet;

    public ServerPacketReceivedEvent() {
        super(4);
    }

    public ServerPacketReceivedEvent(ChannelHandlerContext ctx, Packet packet) {
        super(4);
        this.ctx = ctx;
        this.packet = packet;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public Packet getPacket() {
        return packet;
    }
}
