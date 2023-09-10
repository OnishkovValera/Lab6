package Managers;

import InputData.Vehicle;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.stream.IntStream;

public class CollectionManager {

    private static HashMap<SocketChannel, Session> sessions = new HashMap<>();

    public static HashMap<SocketChannel, Session> getSessions() {
        return sessions;
    }

    public static HashMap<Integer, Vehicle> getSessionHashMap(SocketChannel socketChannel) {
        return sessions.get(socketChannel).getHashMap();
    }
    public static void setVariable(SocketChannel socketChannel, String path)throws JsonSyntaxException {
        sessions.get(socketChannel).initializeSession(path);
    }
    public static void openSession(SocketChannel socketChannel){
        sessions.put(socketChannel, new Session());
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

    public static Integer idGenerator(HashMap<Integer, Vehicle> hashMap){
        Set<Integer> set = new HashSet<>();

        for(Vehicle vehicle : hashMap.values()) {
            set.add(vehicle.getId());
        }

        int[] numbers = IntStream.range(1, 2000000).filter(i -> !set.contains(i)).toArray();
        int randomNumber = new Random().nextInt(numbers.length);

        return numbers[randomNumber];
    }

    public static Integer keyGenerator(HashMap<Integer, Vehicle> hashMap){
        Set<Integer> numbers = hashMap.keySet();
        Integer number;
        if(!numbers.isEmpty()) {
             number = IntStream.range(0, hashMap.size()).filter(i -> !numbers.contains(i)).min().getAsInt();
        }else{
            return 1;
        }
        return number;
    }

    public static void updateOtherCollections(SocketChannel socketChannel){
        String path = sessions.get(socketChannel).getPath();
        Set<SocketChannel> sessionOnUpdate = new HashSet<>();

        for (SocketChannel socketChannelOnUpdate: sessions.keySet()){
            if(sessions.get(socketChannelOnUpdate).getPath().equals(path)){
                sessionOnUpdate.add(socketChannelOnUpdate);
            }
        }
        for(SocketChannel chanel: sessionOnUpdate){
            sessions.get(chanel).setHashMap(sessions.get(socketChannel).getHashMap());
        }
    }
}
