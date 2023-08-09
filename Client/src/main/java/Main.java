import Managers.ContainerHandler;
import Mangers.Connection;
import Mangers.EnvironmentHandler;
import Mangers.RunClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ContainerHandler containerHandler = new ContainerHandler(Connection.connect("localhost", 8080));
        EnvironmentHandler.setContainerHandler(containerHandler);
        RunClient client = new RunClient();
        client.run(containerHandler);

    }
}
