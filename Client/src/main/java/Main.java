import Managers.ContainerHandler;
import Mangers.Connection;
import Mangers.EnvironmentHandler;
import Mangers.RunClient;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {

        ContainerHandler containerHandler = new ContainerHandler(Connection.connect("localhost", 8080));
        EnvironmentHandler.setContainerHandler(containerHandler);

        while(true) {
            try {
                RunClient client = new RunClient();
                client.run(containerHandler);

            } catch (IOException exception) {
                System.err.println("Server reset connection");
                Thread.sleep(100);
                containerHandler = new ContainerHandler(Connection.connect("localhost", 8080));
                EnvironmentHandler.setContainerHandler(containerHandler);

            }
        }
    }
}