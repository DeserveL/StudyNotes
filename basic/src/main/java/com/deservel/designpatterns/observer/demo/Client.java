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
package com.deservel.designpatterns.observer.demo;

/**
 * @author DeserveL
 * @date 2017/6/21 11:39
 * @since 1.0.0
 */
public class Client {
    public static void main(String[] args) {
        //读者
        Reader 张三 = new Reader("张三");
        Reader 赵四 = new Reader("赵四");
        Reader 王五 = new Reader("王五");
        Reader 李六 = new Reader("李六");
        //作者
        Writer 刘大师 = new Writer("刘大师");
        Writer 杨上仙 = new Writer("杨上仙");

        张三.subscribe(刘大师.getName());
        赵四.subscribe(刘大师.getName());

        赵四.subscribe(杨上仙.getName());
        王五.subscribe(杨上仙.getName());
        李六.subscribe(杨上仙.getName());

        刘大师.addNorvel("颈椎病康复指南");
        杨上仙.addNorvel("从入门到放弃");
    }
}
