/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deservel.springboot.demo.websocket;

import com.deservel.springboot.demo.pojo.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天
 *
 * @author DeserveL
 * @date 2018-12-19 11:56
 * @since 1.0.0
 */
@Slf4j
@Component
@ServerEndpoint("/chat/{username}")
public class ChatWebSocket {

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, ChatWebSocket> clients = new ConcurrentHashMap<>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        //在线人数加1
        onlineNumber++;
        log.info("有新连接加入！用户id：{}   用户名：{}", session.getId(), username);
        log.info("当前在线人数：{}", onlineNumber);

        this.username = username;
        this.session = session;

        //把自己的信息加入到map当中去
        clients.put(username, this);

        //给所有人发送通知，说我上线了
        Message onLineMessage = new Message();
        onLineMessage.setMessageType(Message.ONLINE);
        onLineMessage.setFromUserName("系统消息");
        onLineMessage.setToUserName(Message.TO_ALL_USER);
        onLineMessage.setOnlineUsers(clients.keySet());
        onLineMessage.setMessage(username + " 上线了！！！");
        sendMessage(onLineMessage);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        //在线人数减1
        onlineNumber--;
        log.info("有新连接断开！用户id：{}   用户名：{}", session.getId(), username);
        log.info("当前在线人数：{}", onlineNumber);

        //删除自己
        clients.remove(username);

        //给所有人发送通知，说我上线了
        Message offLineMessage = new Message();
        offLineMessage.setMessageType(Message.OFFLINE);
        offLineMessage.setFromUserName("系统消息");
        offLineMessage.setToUserName(Message.TO_ALL_USER);
        offLineMessage.setOnlineUsers(clients.keySet());
        offLineMessage.setMessage(username + " 下线了！！！");
        sendMessage(offLineMessage);
    }

    @OnError
    public void onError(Throwable error) {
        log.info("服务端发生了错误", error);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端消息：{}  客户端的id是：{}   用户名：{}", message, session.getId(), username);
        Message messageBo = new Gson().fromJson(message, Message.class);
        messageBo.setMessageType(Message.MESSAGE);
        messageBo.setFromUserName(username);
        messageBo.setOnlineUsers(clients.keySet());
        if(null != messageBo.getToUserName() && !"".equals(messageBo.getToUserName())){
            sendMessage(messageBo);
        }
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(Message message){
        if (Message.TO_ALL_USER.equals(message.getToUserName())) {
            sendMessageAll(message);
        } else {
            sendMessageTo(message);
        }
    }

    /**
     * 发送消息给指定人
     *
     * @param message
     */
    public void sendMessageTo(Message message) {
        ChatWebSocket chatWebSocket = clients.get(message.getToUserName());
        chatWebSocket.session.getAsyncRemote().sendText(new Gson().toJson(message));
    }

    /**
     * 发送消息给所有人
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(Message message) {
        for (ChatWebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(new Gson().toJson(message));
        }
    }

}
