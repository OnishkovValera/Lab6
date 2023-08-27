package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Info extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
         ;

        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(Path.of(CollectionManager.getSessions().get(socketChannel).getPath()), BasicFileAttributes.class);
        } catch (IOException e) {
            return new Container(true, "Something went wrong");
        }
        return new Container(false,
                "Type: " + CollectionManager.getSessionHashMap(socketChannel).getClass() +
                        "\nDate: " + attr.creationTime() +
                        "\nSize: " + CollectionManager.getSessionHashMap(socketChannel).size()
        );
    }
}
