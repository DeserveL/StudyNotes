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
package com.liuchong.studynotes.designpatterns.observer.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理器，保持一份独有的作者列表
 *
 * @author DeserveL
 * @date 2017/6/21 11:08
 * @since 1.0.0
 */
public class WriterManager {

    private Map<String, Writer> writerMap = new HashMap<>(); // 作者列表

    /**
     * 添加作者
     *
     * @param w
     */
    public void add(Writer w){
        writerMap.put(w.getName(), w);
    }

    public Writer getWriter(String writerName){
        return writerMap.get(writerName);
    }

    //单例模式
    private WriterManager(){}

    public static WriterManager getInstance(){
        return WriterManagerInstance.writerManager;
    }

    private static class WriterManagerInstance{
        private static WriterManager writerManager = new WriterManager();
    }
}
