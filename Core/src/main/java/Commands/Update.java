package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;

public class Update extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        Vehicle vehicle = container.getHashMap().get(0);
        CollectionManager.getSessionHashMap(socketChannel).put(Integer.parseInt(container.getArgument()),
                new Vehicle(CollectionManager.idGenerator(CollectionManager.getSessionHashMap(socketChannel)),
                        vehicle.getName(), vehicle.getCoordinates(),
                        vehicle.getCreationDate(), vehicle.getEnginePower(),
                        vehicle.getCapacity(), vehicle.getType(), vehicle.getFuelType()));
        CollectionManager.updateOtherCollections(socketChannel);
        return new Container(false,"Vehicle has been updated");
    }
}
