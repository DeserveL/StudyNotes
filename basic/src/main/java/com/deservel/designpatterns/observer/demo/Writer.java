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

/**
 * 作者类，要继承自被观察者类
 *
 * @author DeserveL
 * @date 2017/6/21 11:04
 * @since 1.0.0
 */
public class Writer extends Observable{

    private String name; //作者姓名

    private String lastNovel; //记录作者最新发布的小说

    public Writer(String name) {
        super();
        this.name = name;
        WriterManager.getInstance().add(this);
    }

    public void addNorvel(String novelName){
        System.out.println(name + "发布新书：《" + novelName + "》");
        lastNovel = novelName;
        this.setChanged();
        this.notifyObservers();
    }

    public String getName() {
        return name;
    }

    public String getLastNovel() {
        return lastNovel;
    }
}
