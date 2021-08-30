package dev.f4ls3.cloudsystem.networking;

import dev.f4ls3.cloudsystem.networking.utils.Session;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

public abstract class Packet {
    public abstract void read(ByteBufInputStream bufferStream) throws IOException;
    public abstract void write(ByteBufOutputStream bufferStream) throws IOException;
}
