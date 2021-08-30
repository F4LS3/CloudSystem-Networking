package dev.f4ls3.cloudsystem.networking.events.server;

import dev.f4ls3.cloudsystem.eventbus.Event;
import io.netty.channel.ChannelHandlerContext;

public class ServerChannelInactiveEvent extends Event {

    private ChannelHandlerContext ctx;

    public ServerChannelInactiveEvent() {
        super(3);
    }

    public ServerChannelInactiveEvent(ChannelHandlerContext ctx) {
        super(3);
        this.ctx = ctx;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }
}
