/*
 * Copyright 2002-2017 the original author or authors.
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
package com.liuchong.studynotes.designpatterns.command.demo;

/**
 * 命令接收者
 *
 * @author DeserveL
 * @date 2017/6/27 21:08
 * @since 1.0.0
 */
public class Tv {
    public int currentChannel = 0;

    public void turnOn() {
        System.out.println("开机");
    }

    public void turnOff() {
        System.out.println("关机");
    }

    public void changeChannel(int channel) {
        this.currentChannel = channel;
        System.out.println("现在的频道是：" + channel);
    }
}
