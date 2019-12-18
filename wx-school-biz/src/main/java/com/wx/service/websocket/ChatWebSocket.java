package com.wx.service.websocket;

import com.alibaba.fastjson.JSONObject;
import com.wx.config.MyEndpointConfigure;
import com.wx.utils.WebSocketMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@Component
@ServerEndpoint(value = "/chat/{userId}", configurator = MyEndpointConfigure.class)
public class ChatWebSocket {

    private Session session = null;

    /**
     * 建立连接的时候
     *
     * @param userId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        WebSocketMapUtil.put(userId, session);
        System.out.println("连接成功");
    }

    /**
     * 收到消息的时候
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        //是不是回显给客户端
        String userId = "";
        String showMsg = "";
        try {
            JSONObject msgJSON = JSONObject.parseObject(message);
            userId = msgJSON.getString("userId");
            showMsg = msgJSON.getString("msg");
//            packageMsg(message)
        } catch (Exception e) {
            log.error("", e);
            return;
        }

        try {
            for (String key : WebSocketMapUtil.getKeys()) {
                if (key.equals(userId)) {
                    continue;
                }
                Session tempSession = WebSocketMapUtil.get(key);
                tempSession.getBasicRemote().sendText(showMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("得到消息：" + message);
    }

    /**
     * 关闭之后
     */
    @OnClose
    public void onClose() {
        WebSocketMapUtil.remove(this.getSession().getId());
        System.out.println("连接关闭");
    }

    /**
     * 发生异常后
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("websocket出现错误");
        WebSocketMapUtil.remove(session.getId());
        error.printStackTrace();
    }

    public void noticeClient(String odds) {
        if (WebSocketMapUtil.getValues().size() == 0) {
            return;
        }
        for (Session session : WebSocketMapUtil.getValues()) {
            try {
                session.getBasicRemote().sendText(odds);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public Session getSession() {
        return this.session;
    }
}
