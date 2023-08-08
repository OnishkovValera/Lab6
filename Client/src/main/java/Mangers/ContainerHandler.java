package Mangers;

import Managers.Container;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ContainerHandler {
    public static void sendContainer(Container container, @NotNull SocketChannel socketChannel) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(container);
        socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
    }
    public static Container readContainer(@NotNull SocketChannel socketChannel) throws IOException, ClassNotFoundException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        socketChannel.read(byteBuffer);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Container container = (Container) objectInputStream.readObject();

        return container;
    }
}
