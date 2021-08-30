package dev.f4ls3.cloudsystem.networking.test;

import dev.f4ls3.cloudsystem.eventbus.EventExecutor;
import dev.f4ls3.cloudsystem.eventbus.EventSubscriber;
import dev.f4ls3.cloudsystem.networking.events.server.ServerChannelActiveEvent;
import dev.f4ls3.cloudsystem.networking.utils.Session;

import java.util.UUID;

public class ServerChannelActiveSubscriber extends EventSubscriber {

    @EventExecutor(ignoreCancelled = true)
    public void onEvent(ServerChannelActiveEvent event) {
        Session session = new Session(
                event.getCtx().channel().id().asLongText(),
                UUID.randomUUID(),
                event.getCtx(),
                event.getCtx().channel().remoteAddress().toString(),
                false
        );



        // TODO: Send AuthenticationPacket
        //session.getCtx().channel().writeAndFlush();
    }
}
