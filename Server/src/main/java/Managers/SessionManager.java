package Managers;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class SessionManager {
    static HashMap<SocketChannel, Session> sessions = new HashMap<>();
    public static void openSession(SocketChannel socketChannel, Session session){
        sessions.put(socketChannel, session);
    }
    public static void closeSession(SocketChannel socketChannel) throws IOException {
        sessions.get(socketChannel).endSession();
        sessions.remove(socketChannel);
    }
    public static Session getSession(SocketChannel socketChannel){
        return sessions.get(socketChannel);
    }

}
