package dev.f4ls3.cloudsystem.networking.packets;

import dev.f4ls3.cloudsystem.networking.Packet;
import dev.f4ls3.cloudsystem.networking.utils.Session;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelFutureListener;

import java.io.IOException;

public class AuthenticationPacket extends Packet {

    @Override
    public void read(ByteBufInputStream bufferStream) throws IOException {
        String minionIp = bufferStream.readUTF();
        String authenticationToken = bufferStream.readUTF();

        if(minionIp == null || authenticationToken == null) {

            return;
        }

        // TODO: Check if supplied token is bound to remote address of the connection
    }

    @Override
    public void write(ByteBufOutputStream bufferStream) throws IOException {}
}
