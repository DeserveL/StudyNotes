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
package com.deservel.springboot.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketBroker 配置
 *
 * 在方法registerStompEndpoints()里addEndpoint方法：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址;withSockJS：指定端点使用SockJS协议
 * 在方法configureMessageBroker()里设置简单消息代理，并配置消息的发送的地址符合配置的前缀的消息才发送到这个broker
 *
 * @author DeserveL
 * @date 2018-12-18 14:11
 * @since 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        /*
          配置消息代理
          启动简单Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
         */
        config.enableSimpleBroker("/topic","/queue");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        /*
          注册 Stomp的端点
          addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
          setAllowedOrigins("*") // 添加允许跨域访问
          withSockJS：指定端点使用SockJS协议
         */
        registry.addEndpoint("/websocket-simple")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
