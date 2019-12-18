package com.wx.utils;

import com.wx.service.websocket.ChatWebSocket;

import javax.websocket.Session;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketMapUtil {
    public static ConcurrentMap<String, Session> webSocketMap = new ConcurrentHashMap<>();

    public static void put(String key, Session session) {
        webSocketMap.put(key, session);
    }

    public static Session get(String key){
        return webSocketMap.get(key);
    }

    public static void remove(String key){
        webSocketMap.remove(key);
    }

    public static Collection<Session> getValues(){
        return webSocketMap.values();
    }

    public static Collection<String> getKeys(){
        return webSocketMap.keySet();
    }

}
