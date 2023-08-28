package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;

public class Clear extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        CollectionManager.getSessionHashMap(socketChannel).clear();
        return new Container(false, "Collection has been cleaned");
    }
}
