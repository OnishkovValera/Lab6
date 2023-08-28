package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;

public class ReplaceIfGreater extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        Vehicle vehicle = container.getHashMap().get(0);
        if(vehicle.getEnginePower() > CollectionManager.getSessionHashMap(socketChannel)
                .get(Integer.parseInt(container.getArgument()))
                .getEnginePower()){
            CollectionManager.getSessionHashMap(socketChannel)
                    .put(Integer.parseInt(container.getArgument()),
                            new Vehicle(CollectionManager.idGenerator(CollectionManager.getSessionHashMap(socketChannel)),
                                    vehicle.getName(), vehicle.getCoordinates(),vehicle.getCreationDate() ,
                                    vehicle.getEnginePower(), vehicle.getCapacity(), vehicle.getType(),
                                    vehicle.getFuelType()));
            return new Container(false, "This vehicle has been removed and added a new one");
        }else{
            return new Container(true, "This vehicle isn't bigger than old one");
        }
    }
}
