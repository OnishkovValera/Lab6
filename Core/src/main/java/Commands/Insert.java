package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.util.HashMap;

public class Insert extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        HashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        Vehicle vehicle = container.getHashMap().get(0);
        hashMap.put(Integer.parseInt(container.getArgument()), new Vehicle(CollectionManager.idGenerator(hashMap), vehicle.getName(), vehicle.getCoordinates(), LocalDate.now(), vehicle.getEnginePower(), vehicle.getCapacity(), vehicle.getType(), vehicle.getFuelType()));
        return new Container(false, "Added");
    }

}
