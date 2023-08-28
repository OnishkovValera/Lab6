package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class RemoveGreaterKey extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        HashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        hashMap.keySet()
                .stream()
                .filter(integer -> Integer.parseInt(container.getArgument()) < integer)
                .forEach(hashMap::remove);
        return new Container(false, "All elements which have key more than the given has been deleted");

    }
}
