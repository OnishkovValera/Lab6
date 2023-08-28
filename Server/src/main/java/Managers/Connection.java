package Managers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Connection {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public Connection(int port) throws IOException {
        serverSoketInitialization(port);
        selectorInitialization();
    }

    public void serverSoketInitialization(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        InetSocketAddress serverPort = new InetSocketAddress(port);
        serverSocketChannel.bind(serverPort);
    }

    public Selector selectorInitialization() throws IOException {
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }
}
