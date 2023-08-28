package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;

public class RemoveAllByCapacity extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        CollectionManager.getSessionHashMap(socketChannel)
                .keySet()
                .stream()
                .filter(integer -> (Double) CollectionManager.getSessionHashMap(socketChannel)
                        .get(integer)
                        .getCapacity() == Double.parseDouble(container.getArgument()))
                .forEach(integer -> CollectionManager.getSessionHashMap(socketChannel)
                        .remove(integer));
        return new Container(false, "All vehicles with" + container.getArgument() + " have been deleted");
    }

}
