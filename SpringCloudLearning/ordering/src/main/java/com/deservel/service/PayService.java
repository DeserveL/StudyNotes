/*
 * Copyright 2002-2018 the original author or authors.
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
package com.deservel.service;

import com.deservel.client.CreditClient;
import com.deservel.client.StockClient;
import com.deservel.client.StorageClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DeserveL
 * @date 2018-12-27 16:43
 * @since 1.0.0
 */
@Service
public class PayService {

    @Autowired
    CreditClient creditClient;
    @Autowired
    StockClient stockClient;
    @Autowired
    StorageClient storageClient;

    @HystrixCommand(fallbackMethod = "stockFallback")
    public void stock(){
        Integer stock = stockClient.reduceStock();
        System.out.println("库存数量：" + stock);
    }

    public void stockFallback() {
        System.out.println("库存系统调用失败");
    }

    @HystrixCommand(fallbackMethod = "storageFallback")
    public void storage(){
        storageClient.storage();
        System.out.println("发货完成。");
    }

    public void storageFallback() {
        System.out.println("发货系统调用失败");
    }

    @HystrixCommand(fallbackMethod = "creditFallback")
    public void credit(){
        Integer credit = creditClient.addCredit();
        System.out.println("积分：" + credit);
    }

    public void creditFallback() {
        System.out.println("积分系统调用失败");
    }
}
