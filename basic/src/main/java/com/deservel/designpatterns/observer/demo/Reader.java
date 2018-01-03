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

import java.util.Observable;
import java.util.Observer;

/**
 * 读者类，要实现观察者接口
 *
 * @author DeserveL
 * @date 2017/6/21 11:01
 * @since 1.0.0
 */
public class Reader implements Observer {

    private String name;

    public Reader(String name) {
        super();
        this.name = name;
    }

    /**
     * 读者可以关注某一位作者，关注则代表把自己加到作者的观察者列表里
     *
     * @param writeName
     */
    public void subscribe(String writeName){
        WriterManager.getInstance().getWriter(writeName).addObserver(this);
    }

    /**
     * 读者可以取消关注某一位作者，取消关注则代表把自己从作者的观察者列表里删除
     *
     * @param writerName
     */
    public void unsubscribe(String writerName){
        WriterManager.getInstance().getWriter(writerName).deleteObserver(this);
    }

    /**
     * 当关注的作者发表新小说时，会通知读者去看
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Writer){
            Writer w = (Writer)o;
            System.out.println(name+"知道" + w.getName() + "发布了新书《" + w.getLastNovel() + "》，记得去看！");
        }
    }

    public String getName() {
        return name;
    }
}
