package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RemoveGreaterKey extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        HashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        Set<Integer> keys = new HashSet<>();

        hashMap.keySet()
                .stream()
                .filter(integer -> Integer.parseInt(container.getArgument()) < integer)
                .forEach(keys::add);
        for(Integer key: keys){
            hashMap.remove(key);
        }
        return new Container(false, "All elements which have key more than the given has been deleted");

    }
}
