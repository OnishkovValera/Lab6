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
            logger.log(Level.INFO, "Waiting messages or connections");
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
                        logger.log(Level.INFO,"Get message from client with address " + ((SocketChannel)handlingKey.channel()).getRemoteAddress());
                        messageHandler.handleMessage((SocketChannel) handlingKey.channel());
                    }

                    if (handlingKey.isConnectable()) {
                        messageHandler.disconnectClient((SocketChannel) handlingKey.channel());
                        handlingKey.cancel();
                    }

                    iter.remove();
                }catch (SocketException exception){
                    try {
                        logger.log(Level.INFO, "Disconnect client with address " +
                                ((SocketChannel) handlingKey.channel()).getRemoteAddress() +
                                ", session was started in " +
                                CollectionManager.getSession((SocketChannel) handlingKey.channel()).getStartSession());
                        CollectionManager.getSessions().remove((SocketChannel) handlingKey.channel());
                        handlingKey.cancel();
                        iter.remove();

                    }catch(NullPointerException e){
                        logger.log(Level.WARNING, e.getMessage());
                    }
                }
            }
        }
    }
}
