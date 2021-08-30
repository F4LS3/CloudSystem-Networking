package dev.f4ls3.cloudsystem.networking.utils;

import io.netty.channel.ChannelHandlerContext;

import java.net.Inet4Address;
import java.util.UUID;

public class Session {

    private final String sessionId;
    private final UUID sessionUUID;
    private final ChannelHandlerContext sessionCtx;
    private final String address;

    private boolean isAuthenticated;

    public Session(String sessionId, UUID sessionUUID, ChannelHandlerContext sessionCtx, String address, boolean isAuthenticated) {
        this.sessionId = sessionId;
        this.sessionUUID = sessionUUID;
        this.sessionCtx = sessionCtx;
        this.address = address;
        this.isAuthenticated = isAuthenticated;
    }

    public String getSessionId() {
        return sessionId;
    }

    public UUID getSessionUUID() {
        return sessionUUID;
    }

    public ChannelHandlerContext getSessionCtx() {
        return sessionCtx;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}