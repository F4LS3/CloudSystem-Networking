package dev.f4ls3.cloudsystem.networking.events;

import dev.f4ls3.cloudsystem.eventbus.Event;

public class CommandReceivedEvent extends Event {

    private String command;
    private String[] args;

    public CommandReceivedEvent() {
        super(6);
    }

    public CommandReceivedEvent(String command, String[] args) {
        super(6);
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
