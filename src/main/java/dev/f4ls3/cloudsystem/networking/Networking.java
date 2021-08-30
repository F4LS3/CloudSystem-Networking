package dev.f4ls3.cloudsystem.networking;

import dev.f4ls3.cloudsystem.eventbus.EventPublisher;
import dev.f4ls3.cloudsystem.networking.events.CommandReceivedEvent;
import dev.f4ls3.cloudsystem.networking.events.server.*;

public class Networking {

    public static void init() {
        EventPublisher.PUBLISHER.registerEvent(new ServerPacketDecodeEvent());
        EventPublisher.PUBLISHER.registerEvent(new ServerPacketEncodeEvent());
        EventPublisher.PUBLISHER.registerEvent(new ServerChannelActiveEvent());
        EventPublisher.PUBLISHER.registerEvent(new ServerChannelInactiveEvent());
        EventPublisher.PUBLISHER.registerEvent(new ServerPacketReceivedEvent());
        EventPublisher.PUBLISHER.registerEvent(new ServerListeningEvent());

        EventPublisher.PUBLISHER.registerEvent(new CommandReceivedEvent());
    }
}
