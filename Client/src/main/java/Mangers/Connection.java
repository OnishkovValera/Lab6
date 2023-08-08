package Mangers;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Connection {
    static public @NotNull SocketChannel connect(String hostName, int port) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, port);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(inetSocketAddress);
        return socketChannel;

    }
}
