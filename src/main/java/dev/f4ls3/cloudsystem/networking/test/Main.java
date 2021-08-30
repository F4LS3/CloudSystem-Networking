package dev.f4ls3.cloudsystem.networking.test;

import dev.f4ls3.cloudsystem.eventbus.EventPublisher;
import dev.f4ls3.cloudsystem.networking.Networking;
import dev.f4ls3.cloudsystem.networking.netty.server.EventDrivenPacketServer;
import dev.f4ls3.cloudsystem.networking.utils.Utilities;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        /*try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
        Logger.getLogger("io.netty").setLevel(Level.OFF);

        Networking.init();

        EventPublisher.PUBLISHER.subscribe(5, new ServerListeningSubscriber());
        EventPublisher.PUBLISHER.subscribe(6, new CommandReceivedSubscriber());

        EventDrivenPacketServer server = new EventDrivenPacketServer();
        server.listen(1337);*/

        System.out.println(Utilities.TOKEN_SUPPLIER.get());
    }
}
