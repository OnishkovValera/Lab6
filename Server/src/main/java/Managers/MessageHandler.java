package Managers;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class MessageHandler {

    private Selector selector;


    public void connectClient(SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false).register(selector, SelectionKey.OP_READ);
        SessionManager.openSession(socketChannel, new Session());
    }

    public void handleMessage(SocketChannel handlingChannel) throws IOException, ClassNotFoundException {
        handlingChannel.configureBlocking(false);
        Container container = ContainerHandler.readContainer(handlingChannel);

    }

    public void disconnectClient(SocketChannel socketChannel) throws IOException {
        SessionManager.closeSession(socketChannel);
    }





    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public MessageHandler(Selector selector) {
        this.selector = selector;
    }


}
