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
package com.liuchong.studynotes.designpatterns.command.base;

/**
 * 具体命令类，实现具体命令。
 *
 * @author DeserveL
 * @date 2017/6/27 21:46
 * @since 1.0.0
 */
public class ConcreteCommand implements Command{

    //具体命令类包含有一个接收者，将这个接收者对象绑定于一个动作
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     *说这个实现是“虚”的，因为它是通过调用接收者相应的操作来实现Execute的
     */
    @Override
    public void execute() {
        receiver.action();
    }
}
