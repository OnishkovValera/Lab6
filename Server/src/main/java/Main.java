import Managers.Connection;
import Managers.RunServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Connection connection = new Connection(8080);
        RunServer server = new RunServer();
        server.run(connection.getSelector(), connection.getServerSocketChannel());
    }
}
