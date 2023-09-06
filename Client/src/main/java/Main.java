import Managers.ContainerHandler;
import Mangers.Connection;
import Mangers.EnvironmentHandler;
import Mangers.RunClient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter port");
        int port = scn.nextInt();
        ContainerHandler containerHandler = new ContainerHandler(Connection.connect("s368608@se.ifmo.ru", port));
        EnvironmentHandler.setContainerHandler(containerHandler);
        RunClient client = new RunClient();
        client.run(containerHandler);

    }
}
