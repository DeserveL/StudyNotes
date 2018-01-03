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
package com.deservel.designpatterns.singleton.demo;

import com.deservel.designpatterns.singleton.base.Singleton;
import com.deservel.studynotes.designpatterns.singleton.base.Singleton;
import com.liuchong.studynotes.designpatterns.singleton.base.Singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试base中懒汉模式并发问题
 *
 * @author DeserveL
 * @date 2017/6/13 14:54
 * @since 1.0.0
 */
public class TestSingleton {

    boolean lock;

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        //返回一个同步的(线程安全的)有序set由指定的有序set支持。
        final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<String>());
        final TestSingleton testSingleton = new TestSingleton();
        testSingleton.setLock(true);
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(
                    () -> {
                        while (true) {
                            if (!testSingleton.isLock()) {
                                com.deservel.designpatterns.singleton.base.Singleton instance = Singleton.getInstance();
                                instanceSet.add(instance.toString());
                                break;
                            }
                        }
                    }
            );
        }
        Thread.sleep(5000);
        testSingleton.setLock(false);
        Thread.sleep(5000);
        System.out.println("------并发情况下我们取到的实例------");
        for (String instance : instanceSet) {
            System.out.println(instance);
        }
        executorService.shutdown();
    }
}

