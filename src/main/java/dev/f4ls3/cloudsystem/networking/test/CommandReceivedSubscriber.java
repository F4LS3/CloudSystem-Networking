package dev.f4ls3.cloudsystem.networking.test;

import dev.f4ls3.cloudsystem.eventbus.EventExecutor;
import dev.f4ls3.cloudsystem.eventbus.EventSubscriber;
import dev.f4ls3.cloudsystem.networking.events.CommandReceivedEvent;

public class CommandReceivedSubscriber extends EventSubscriber {

    @EventExecutor(ignoreCancelled = true)
    public void onEvent(CommandReceivedEvent event) {

    }
}
