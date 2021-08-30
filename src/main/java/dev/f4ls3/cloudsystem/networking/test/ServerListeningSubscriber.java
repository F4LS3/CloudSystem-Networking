package dev.f4ls3.cloudsystem.networking.test;

import dev.f4ls3.cloudsystem.eventbus.EventExecutor;
import dev.f4ls3.cloudsystem.eventbus.EventPublisher;
import dev.f4ls3.cloudsystem.eventbus.EventSubscriber;
import dev.f4ls3.cloudsystem.networking.events.CommandReceivedEvent;
import dev.f4ls3.cloudsystem.networking.events.server.ServerListeningEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ServerListeningSubscriber extends EventSubscriber {

    @EventExecutor(ignoreCancelled = true)
    public void onEvent(ServerListeningEvent event) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] command;
        try {
            //noinspection ConstantConditions
            while ((command = reader.readLine().toLowerCase().split(" ")) != null) {
                if(command.length == 0) continue;

                String cmd = command[0];
                EventPublisher.PUBLISHER.publish(new CommandReceivedEvent(cmd, Arrays.copyOfRange(command, 1, command.length)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
