package Managers;

import InputData.Vehicle;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class SessionManager {
    public static HashMap<SocketChannel, Session> sessions = new HashMap<>();

    public static HashMap<Integer, Vehicle> getSessionHashMap(SocketChannel socketChannel) {
        return sessions.get(socketChannel).getHashMap();
    }
    public static void setVariable(SocketChannel socketChannel, String path){
        sessions.get(socketChannel).initializeSession(path);
    }
    public static void openSession(SocketChannel socketChannel, Session session){
        sessions.put(socketChannel, session);
    }

    public static void closeSession(SocketChannel socketChannel) throws IOException {
        sessions.get(socketChannel).saveCollection();
        sessions.remove(socketChannel);
    }

    public static void saveCollection(SocketChannel socketChannel) throws IOException {
        sessions.get(socketChannel).saveCollection();
    }

    public static Session getSession(SocketChannel socketChannel){
        return sessions.get(socketChannel);
    }

}
