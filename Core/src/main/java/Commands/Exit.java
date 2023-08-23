package Commands;

import Managers.Container;

import java.nio.channels.SocketChannel;

public class Exit extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        return null;
    }

}
