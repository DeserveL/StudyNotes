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
package com.deservel.controller;

import com.deservel.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付
 *
 * @author DeserveL
 * @date 2018-12-27 14:32
 * @since 1.0.0
 */
@RestController
public class PayController {

    @Autowired
    PayService payService;

    @RequestMapping("/pay")
    public Map pay(Integer flag) {
        Map<String, String> rs = new HashMap<>(2);
        if (flag == 1) {
            payService.stock();
            payService.storage();
            payService.credit();
            rs.put("msg", "支付成功");
        } else {
            rs.put("msg", "支付失败");
        }
        return rs;
    }
}
