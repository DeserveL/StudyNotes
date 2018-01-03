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
 * @author DeserveL
 * @date 2017/6/12 22:33
 * @since 1.0.0
 */

/**
 * 使用嵌套注解，并且制定我们打折的各个策略顺序是99，这算是很靠后的
 */
@TotalValidRegion(@ValidRegion(max = 1000, order = 99))
class Common implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice;
    }

}

@TotalValidRegion(@ValidRegion(min = 1000, max = 2000, order = 99))
class Vip implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice * 8 / 10;
    }

}

@TotalValidRegion(@ValidRegion(min = 2000, max = 3000, order = 99))
class SuperVip implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice * 7 / 10;
    }

}

@TotalValidRegion(@ValidRegion(min = 3000, order = 99))
class GoldVip implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice * 5 / 10;
    }

}

/**
 * 满1000返200和满2000返400，并且优先级高于打折，也就是说会先计算现金返回，再打折
 */
@OnceValidRegion(@ValidRegion(min = 1000, max = 2000, order = 40))
class OneTDTwoH implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice - 200;
    }

}

@OnceValidRegion(@ValidRegion(min = 2000, order = 40))
class TwotDFourH implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice - 400;
    }

}
