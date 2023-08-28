package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class AverageOfEnginePower extends AbstractCommand {
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        HashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        int commonPower = (int) hashMap.values().stream().mapToInt(Vehicle::getEnginePower).summaryStatistics().getAverage();
        return new Container(false, Integer.toString(commonPower));

    }
}
