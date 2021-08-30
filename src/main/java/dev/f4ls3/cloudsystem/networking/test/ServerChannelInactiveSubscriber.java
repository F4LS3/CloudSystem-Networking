package dev.f4ls3.cloudsystem.networking.test;

import dev.f4ls3.cloudsystem.eventbus.EventExecutor;
import dev.f4ls3.cloudsystem.eventbus.EventSubscriber;
import dev.f4ls3.cloudsystem.networking.events.server.ServerChannelInactiveEvent;

public class ServerChannelInactiveSubscriber extends EventSubscriber {

    @EventExecutor(ignoreCancelled = true)
    public void onEvent(ServerChannelInactiveEvent event) {
        
    }
}
