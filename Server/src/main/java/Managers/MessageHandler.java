package Managers;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class MessageHandler {

    private Selector selector;


    public void connectClient(SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false).register(selector, SelectionKey.OP_READ);
        SessionManager.openSession(socketChannel, new Session());
    }

    public void handleMessage(SocketChannel handlingChannel) throws IOException, ClassNotFoundException {
        handlingChannel.configureBlocking(false);
        Container container = ContainerHandler.readContainer(handlingChannel);
        if(container.checkEnv){
            chekEnvironment(container.getEnv(), handlingChannel);
        }else if(container.endConnection){
            disconnectClient(handlingChannel);

        }else if(container.setNewVariable){
            SessionManager.saveCollection(handlingChannel);
            chekEnvironment(container.getEnv(), handlingChannel);

        }else{
            container.getCommand().execute(container);
        }

    }

    public void disconnectClient(SocketChannel socketChannel) throws IOException {
        SessionManager.closeSession(socketChannel);
    }

    public void chekEnvironment(String path, SocketChannel handlingChannel) throws IOException {
        Path envPath = Path.of(System.getenv(path)).toAbsolutePath();
        if(Files.exists(envPath)){
            ContainerHandler.sendContainer(new Container(true, "File isn't exist"), handlingChannel);


        }else if (Files.isDirectory(envPath)) {
            ContainerHandler.sendContainer(new Container(true, "This is directory"), handlingChannel);


        } else if(!Files.isReadable(envPath)){
            ContainerHandler.sendContainer(new Container(true, "File can't be read"), handlingChannel);

        }else{
            //Инициализировать новую сессию
            SessionManager.setVariable(handlingChannel, envPath.toString());
            ContainerHandler.sendContainer(new Container(false, "Success, variable is valid"), handlingChannel);
        }
    }


    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public MessageHandler(Selector selector) {
        this.selector = selector;
    }


}
