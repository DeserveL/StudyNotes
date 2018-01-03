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
package com.deservel.designpatterns.strategy.demo;

/**
 * 客户端
 *
 * @author DeserveL
 * @date 2017/6/12 22:48
 * @since 1.0.0
 */
public class Customer {

    private Double totalAmount = 0D;//客户在本商店消费的总额

    private Double amount = 0D;//客户单次消费金额

    private CalPrice calPrice = new Common();//每个客户都有一个计算价格的策略，初始都是普通计算，即原价

    /**
     * 客户购买商品，就会增加它的总额
     * @param amount
     */
    public void buy(Double amount) {
        this.amount = amount;
        totalAmount += amount;
        /* 变化点，我们将策略的制定转移给了策略工厂，将这部分责任分离出去 */
        calPrice = CalPriceFactory.getInstance().createCalPrice(this);
    }

    /**
     * 计算客户最终要付的钱
     * @return
     */
    public Double calLastAmount() {
        return calPrice.calPrice(totalAmount);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getAmount() {
        return amount;
    }
}
