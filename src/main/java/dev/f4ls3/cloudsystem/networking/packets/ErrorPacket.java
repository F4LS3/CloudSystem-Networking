package dev.f4ls3.cloudsystem.networking.packets;

import dev.f4ls3.cloudsystem.networking.Packet;
import dev.f4ls3.cloudsystem.networking.utils.Session;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

public class ErrorPacket extends Packet {

    private String utf;

    public ErrorPacket() {}

    public ErrorPacket(String utf) {
        this.utf = utf;
    }

    @Override
    public void read(ByteBufInputStream bufferStream, Session session) throws IOException {
        this.utf = bufferStream.readUTF();
        System.out.println("[" + session.getCtx().channel().remoteAddress() + "/" + session.getCtx().channel().id() + "] ERROR: " + this.utf);
    }

    @Override
    public void write(ByteBufOutputStream bufferStream, Session session) throws IOException {
        bufferStream.writeUTF(this.utf);
    }
}
