package Managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageHandler {
    Logger logger = Logger.getLogger(MessageHandler.class.getName());
    private Selector selector;


    public void connectClient(SocketChannel socketChannel) throws IOException {

        socketChannel.configureBlocking(false).register(selector, SelectionKey.OP_READ);
        CollectionManager.openSession(socketChannel);

        logger.log(Level.INFO, "Client from address " + socketChannel.getRemoteAddress() + " connected");

    }

    public void handleMessage(SocketChannel handlingChannel) throws IOException, ClassNotFoundException {
        handlingChannel.configureBlocking(false);
        Container container = ContainerHandler.readContainer(handlingChannel);

        if(container.setNewVariable) {
            createNewEnvironment(container.getNameOfVariable(), container.getPathOfVariable(), handlingChannel);
            logger.log(Level.INFO, "Creating new variable for client with address " + handlingChannel.getRemoteAddress());

        }else if(container.checkEnv){
            checkEnvironment(container.getEnv(), handlingChannel);
            logger.log(Level.INFO, "Check environment variable from client with address " + handlingChannel.getRemoteAddress());

        }else if(container.endConnection){
            disconnectClient(handlingChannel);

        }else{
            ContainerHandler.sendContainer(container.getCommand().execute(container, handlingChannel), handlingChannel);
            logger.log(Level.INFO, "Sending response to client with address " + handlingChannel.getRemoteAddress() );

        }
    }

    public void disconnectClient(SocketChannel socketChannel) throws IOException {
        CollectionManager.closeSession(socketChannel);
        ContainerHandler.sendContainer(new Container(false, "Session closed"), socketChannel);
    }

    public void checkEnvironment(String path, SocketChannel handlingChannel) throws IOException {
        try {
            Path envPath = Path.of(System.getenv(path));
            if (!Files.exists(envPath)) {
                ContainerHandler.sendContainer(new Container(true, "File isn't exist"), handlingChannel);


            } else if (Files.isDirectory(envPath)) {
                ContainerHandler.sendContainer(new Container(true, "This is directory"), handlingChannel);


            } else if (!Files.isReadable(envPath)) {
                ContainerHandler.sendContainer(new Container(true, "File can't be read"), handlingChannel);

            } else {
                //Инициализировать новую сессию
                CollectionManager.setVariable(handlingChannel, envPath.toString());
                ContainerHandler.sendContainer(new Container(false, "Success, variable is valid"), handlingChannel);
            }
            logger.log(Level.INFO, "Sending a response");

        }catch (NullPointerException exception){
            ContainerHandler.sendContainer(new Container(true, "No such variable" ), handlingChannel);
            logger.log(Level.INFO, "Sending a response with error");

        }
    }

    public void createNewEnvironment(String nameOfVariable, String pathOfVariable, SocketChannel socketChannel) throws IOException {
        Path newEnvPath = Path.of(pathOfVariable , nameOfVariable);

        if(Files.exists(newEnvPath)){
            ContainerHandler.sendContainer(new Container(true, "Such file is already exist" ), socketChannel);
        }else {
            try {
                File file = new File(newEnvPath.toString());
                if (file.createNewFile()) {
                    Thread.sleep(1000);
                    ContainerHandler.sendContainer(new Container(false, "File created"), socketChannel);
                    FileWriter writer = new FileWriter(newEnvPath.toString());
                    writer.write("{}");
                    writer.close();

                    CollectionManager.setVariable(socketChannel, newEnvPath.toString());
                    System.out.println(newEnvPath);

                } else {
                    ContainerHandler.sendContainer(new Container(true, "Cant create this file"), socketChannel);
                }

            } catch (IOException | InterruptedException exception) {
                ContainerHandler.sendContainer(new Container(true, "No such path"), socketChannel);
            }
        }
        logger.log(Level.INFO, "Sending a response");
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
