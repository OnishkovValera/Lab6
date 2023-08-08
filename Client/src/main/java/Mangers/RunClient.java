package Mangers;

import Managers.Container;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class RunClient {

    String env;
    public void sendContainer(Container container, @NotNull SocketChannel socketChannel) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(container);
        socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
    }
    public Container readContainer(@NotNull SocketChannel socketChannel) throws IOException, ClassNotFoundException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        socketChannel.read(byteBuffer);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Container container = (Container) objectInputStream.readObject();

        return container;
    }


    public void run(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        Validator vld = new Validator();
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name of environment variable");
            System.out.print("$");

            env = scn.nextLine();

            Container container = new Container(true, env);
            sendContainer(container, socketChannel);
            container = readContainer(socketChannel);

            if(container.getError()){
                System.err.println(container.getCommand());

            }else{

                System.out.println(container.getCommand());
                break;

            }
        }
        while (true){

            System.out.println("Write command");
            System.out.print("$");
            Container container = null;

            try {
                container = vld.validateData(scn.nextLine());
            }catch (IllegalArgumentException exception){
                System.err.println("No such command");
                continue;
            }

            sendContainer(container, socketChannel);

            if(container.getCommand() == "exit"){
                System.out.println("App is closing");
                System.exit(1);

            }

            container = readContainer(socketChannel);

            if(container.getError()){
                System.err.println(container.getCommand());
                continue;

            }

            if(container.getHashMap() == null){
                System.out.println(container.getCommand());
                continue;
            }

            for(Integer key : container.getHashMap().keySet()){
                System.out.println(container.getHashMap().get(key).toString());
            }
        }
    }


}
