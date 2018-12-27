package com.deservel.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DeserveL
 * @date 2018-12-27 15:32
 * @since 1.0.0
 */
@FeignClient("stock")
public interface StockClient {

    /**
     * 减少库存
     *
     * @return
     */
    @GetMapping("/reduceStock")
    Integer reduceStock();
}
