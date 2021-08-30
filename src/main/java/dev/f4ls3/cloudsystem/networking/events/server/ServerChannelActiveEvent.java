package dev.f4ls3.cloudsystem.networking.events.server;

import dev.f4ls3.cloudsystem.eventbus.Event;
import io.netty.channel.ChannelHandlerContext;

public class ServerChannelActiveEvent extends Event {

    private ChannelHandlerContext ctx;

    public ServerChannelActiveEvent() {
        super(2);
    }

    public ServerChannelActiveEvent(ChannelHandlerContext ctx) {
        super(2);
        this.ctx = ctx;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }
}
