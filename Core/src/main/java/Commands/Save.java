package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Save extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        try {
            CollectionManager.saveCollection(socketChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Container(false, "Collection has been saved");
    }
}
