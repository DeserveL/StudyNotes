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
package com.deservel.designpatterns.command.base;

/**
 * 接收者类，知道如何实施与执行一个请求相关的操作，任何类都可能作为一个接收者。
 *
 * @author DeserveL
 * @date 2017/6/27 21:42
 * @since 1.0.0
 */
public class Receiver {
    public void action() {
        System.out.println(this.getClass().getName()+"这件事我来做");
    }
}
