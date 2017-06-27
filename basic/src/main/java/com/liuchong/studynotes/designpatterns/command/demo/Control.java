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
 * 遥控器
 *
 * @author DeserveL
 * @date 2017/6/27 21:22
 * @since 1.0.0
 */
public class Control {

    private Command commandOn;
    private Command commandOff;
    private Command commandChange;

    public Control(Command commandOn, Command commandOff, Command commandChange) {
        this.commandOn = commandOn;
        this.commandOff = commandOff;
        this.commandChange = commandChange;
    }

    public void turnOn() {
        commandOn.execute();
    }

    public void turnOff() {
        commandOff.execute();
    }

    public void changeChannel() {
        commandChange.execute();
    }
}
