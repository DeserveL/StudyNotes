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
package com.liuchong.studynotes.designpatterns.singleton.demo;

/**
 * 　比较标准的单例模式。
 * <p>
 * 　一个类的静态属性只会在第一次加载类时初始化，这是JVM帮我们保证的，
 * 　所以我们无需担心并发访问的问题。所以在初始化进行一半的时候，别的线程是无法使用的，
 * 　因为JVM会帮我们强行同步这个过程。另外由于静态变量只初始化一次，所以singleton仍然是单例的。
 *
 * @author DeserveL
 * @date 2017/6/13 15:53
 * @since 1.0.0
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonInstance.instance;
    }

    private static class SingletonInstance {
        static Singleton instance = new Singleton();
    }
}
