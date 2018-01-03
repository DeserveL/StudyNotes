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
package com.deservel.designpatterns.observer.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，它有一个观察者的列表，并且有一个通知所有观察者的方法，通知的方式就是调用观察者通用的接口行为update方法
 *
 * @author DeserveL
 * @date 2017/6/21 10:37
 * @since 1.0.0
 */
public class Observable {

    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void change(){
        System.out.println("我是观察者  我发生变化了哦");
        notifyObservers();//通知观察自己的所有观察者
    }

    public void notifyObservers(){
        for(Observer o : observers){
            o.update(this);
        }
    }
}
