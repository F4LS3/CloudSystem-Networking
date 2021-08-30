package dev.f4ls3.cloudsystem.networking.utils;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SessionRegistry {

    private HashMap<UUID, Session> sessions;

    public SessionRegistry() {
        this.sessions = new HashMap<>();
    }

    public void registerSession(Session session) {
        this.sessions.put(session.getSessionUUID(), session);
        System.out.println("Registered session[id=" + session.getSessionId() + ", uuid=" + session.getSessionUUID().toString() + "] successfully");
    }

    public void unregisterSession(Session session) {
        this.sessions.remove(session);
        System.out.println("Unregistered session[id=" + session.getSessionId() + ", uuid=" + session.getSessionUUID().toString() + "] successfully");
    }

    public List<Session> getSessionByCtx(ChannelHandlerContext ctx) {
        return this.sessions.values()
                .stream()
                .filter(session -> session.getSessionCtx().channel().id().equals(ctx.channel().id()))
                .collect(Collectors.toList());
    }

    public List<Session> getSessionById(String sessionId) {
        return this.sessions.values()
                .stream()
                .filter(session -> session.getSessionId().equals(sessionId))
                .collect(Collectors.toList());
    }

    public Session getSessionByUUID(UUID uuid) {
        return this.sessions.get(uuid);
    }
}