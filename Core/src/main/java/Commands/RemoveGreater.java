package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class RemoveGreater extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        HashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        hashMap.keySet()
                .stream()
                .filter(integer -> hashMap.get(integer).getEnginePower() < container.getHashMap().get(0).getEnginePower())
                .forEach(hashMap::remove);

        return new Container(false, "All elements less than the given one are removed");
    }
}
