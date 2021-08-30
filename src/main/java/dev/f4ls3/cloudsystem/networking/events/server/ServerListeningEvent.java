package dev.f4ls3.cloudsystem.networking.events.server;

import dev.f4ls3.cloudsystem.eventbus.Event;
import io.netty.channel.ChannelFuture;

public class ServerListeningEvent extends Event {

    private ChannelFuture future;
    private int port;

    public ServerListeningEvent() {
        super(5);
    }

    public ServerListeningEvent(ChannelFuture future, int port) {
        super(5);
        this.future = future;
        this.port = port;
    }

    public ChannelFuture getFuture() {
        return future;
    }

    public int getPort() {
        return port;
    }
}
