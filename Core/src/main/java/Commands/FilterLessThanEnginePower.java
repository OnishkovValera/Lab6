package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class FilterLessThanEnginePower extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        HashMap<Integer, Vehicle> newHashMap = new HashMap<>();
        Integer enginePower = Integer.parseInt(container.getArgument());
        CollectionManager.getSessionHashMap(socketChannel)
                .keySet()
                .stream()
                .filter(integer -> CollectionManager.getSessionHashMap(socketChannel)
                        .get(integer)
                        .getEnginePower() < enginePower)
                .forEach(integer -> newHashMap.put(integer,
                        CollectionManager.getSessionHashMap(socketChannel).get(integer)));

        return new Container(false, "All vehicles which have less than " + enginePower, newHashMap);

    }
}
