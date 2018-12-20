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
package com.deservel.springboot.demo.pojo;

import lombok.Data;

import java.util.Set;

/**
 * chat消息
 *
 * @author DeserveL
 * @date 2018-12-19 12:31
 * @since 1.0.0
 */
@Data
public class Message {
    /**
     * messageType 1代表上线 2代表下线 3代表普通消息
     */
    public static final Integer ONLINE = 1;
    public static final Integer OFFLINE = 2;
    public static final Integer MESSAGE = 3;
    /**
     * 发送给全部人
     */
    public static final String TO_ALL_USER = "All_001";

    private Integer messageType;
    private String fromUserName;
    private String toUserName;
    private Set<String> onlineUsers;
    private String message;
}
