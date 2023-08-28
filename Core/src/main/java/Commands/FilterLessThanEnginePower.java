package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class FilterLessThanEnginePower extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        Integer enginePower = Integer.parseInt(container.getArgument());
        Integer[] newKeys = (Integer[]) CollectionManager.getSessionHashMap(socketChannel)
                .keySet()
                .stream()
                .filter(integer -> CollectionManager.getSessionHashMap(socketChannel)
                        .get(integer)
                        .getEnginePower() < enginePower)
                .toArray();
        HashMap<Integer, Vehicle> newHashMap = new HashMap<>();
        for(Integer i : newKeys){
            newHashMap.put(i, CollectionManager.getSessionHashMap(socketChannel).get(i));
        }
        return new Container(false, "All vehicles which have less than" + enginePower, newHashMap);

    }
}
