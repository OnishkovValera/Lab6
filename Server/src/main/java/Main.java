import Managers.Connection;
import Managers.RunServer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter port");
        Connection connection = new Connection(scn.nextInt());
        RunServer server = new RunServer();
        server.run(connection.getSelector(), connection.getServerSocketChannel());

    }
}
