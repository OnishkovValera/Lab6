package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;

public class RemoveKey extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        CollectionManager.getSessionHashMap(socketChannel).remove(Integer.parseInt(container.getArgument()));
        return new Container(false, "Element with key=" + container.getArgument() + " has been deleted");
    }
}
