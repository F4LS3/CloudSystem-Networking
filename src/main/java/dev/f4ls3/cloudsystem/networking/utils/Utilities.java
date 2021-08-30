package dev.f4ls3.cloudsystem.networking.utils;

import io.netty.channel.epoll.Epoll;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.function.Supplier;

public class Utilities {
    public static final boolean EPOLL = Epoll.isAvailable();

    public static final Supplier<String> TOKEN_SUPPLIER = () -> {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[48];
        secureRandom.nextBytes(randomBytes);
        return Instant.now().getNano() + "-" + Base64.getUrlEncoder().encodeToString(randomBytes) + "-" + Instant.now().toEpochMilli();
    };
}
