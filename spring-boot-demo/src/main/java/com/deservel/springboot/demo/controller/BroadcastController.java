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
package com.deservel.springboot.demo.controller;

import com.deservel.springboot.demo.pojo.RequestMessage;
import com.deservel.springboot.demo.pojo.ResponseMessage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket demo
 *
 * @author DeserveL
 * @date 2018-12-18 14:20
 * @since 1.0.0
 */
@Controller
@Slf4j
public class BroadcastController {

    /**
     * 收到消息记数
     */
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     * @param requestMessage 1
     * @return 1
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public ResponseMessage broadcast(RequestMessage requestMessage){
        log.info("receive Message = {}" , new Gson().toJson(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive [" + count.incrementAndGet() + "] records。发送者：" + requestMessage.getName());
        return responseMessage;
    }
}
