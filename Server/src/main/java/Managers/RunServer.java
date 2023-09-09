package Managers;

import java.io.IOException;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunServer {
    Logger logger = Logger.getLogger(RunServer.class.getName());
    public void run(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException, ClassNotFoundException {
        MessageHandler messageHandler = new MessageHandler(selector);
        logger.log(Level.INFO, "Selector waiting");

        while (true){

            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeySet.iterator();

            if(iter.hasNext()){
                SelectionKey handlingKey = iter.next();
                try{
                    if (!handlingKey.isValid()) {
                        continue;
                    }

                    if (handlingKey.isAcceptable()) {
                        messageHandler.connectClient(serverSocketChannel.accept());
                    }

                    if (handlingKey.isReadable()) {
                        messageHandler.handleMessage((SocketChannel) handlingKey.channel());
                    }

                    if (handlingKey.isConnectable()) {
                        messageHandler.disconnectClient((SocketChannel) handlingKey.channel());
                        handlingKey.cancel();
                    }

                    iter.remove();
                }catch (SocketException exception){
                    System.out.println("Client dropped the connection");
                    CollectionManager.getSessions().remove((SocketChannel) handlingKey.channel());
                    handlingKey.cancel();
                    iter.remove();

                }
            }
        }
    }
}
