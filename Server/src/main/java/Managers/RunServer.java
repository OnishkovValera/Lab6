package Managers;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class RunServer {
    public void run(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException, ClassNotFoundException {
        MessageHandler messageHandler = new MessageHandler();

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeySet.iterator();

            if(iter.hasNext()){
                SelectionKey handlingKey = iter.next();

                if(!handlingKey.isValid()){
                    continue;
                }

                if(handlingKey.isAcceptable()){
                    messageHandler.connectClient(serverSocketChannel.accept());
                }

                if(handlingKey.isReadable()){
                    messageHandler.handleMessage((SocketChannel) handlingKey.channel());

                }

                if(!handlingKey.isConnectable()){
                    messageHandler.disconnectClient((SocketChannel) handlingKey.channel());
                }

                iter.remove();

            }
        }
    }
}
