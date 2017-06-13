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
package com.liuchong.studynotes.designpatterns.strategy.base;

/**
 * 客户端代码
 *
 * @author DeserveL
 * @date 2017/6/12 21:17
 * @since 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();

        //实例化不同的策略
        context.setStrategy(new ConcreteStrategyA());
        context.ContextInterface();

        context.setStrategy(new ConcreteStrategyB());
        context.ContextInterface();

        context.setStrategy(new ConcreteStrategyC());
        context.ContextInterface();
    }
}
