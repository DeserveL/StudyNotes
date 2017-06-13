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
package com.liuchong.studynotes.designpatterns.strategy.demo;

/**
 * 客户端调用
 *
 * @author DeserveL
 * @date 2017/6/13 9:28
 * @since 1.0.0
 */
public class Client {
    public static void main(String[] args) {
        Customer customer = new Customer();

        customer.buy(500D);
        System.out.println(customer.calLastAmount());

        customer.buy(1200D);
        System.out.println(customer.calLastAmount());

        customer.buy(1200D);
        System.out.println(customer.calLastAmount());

        customer.buy(1200D);
        System.out.println(customer.calLastAmount());

        customer.buy(2500D);
        System.out.println(customer.calLastAmount());
    }
}
