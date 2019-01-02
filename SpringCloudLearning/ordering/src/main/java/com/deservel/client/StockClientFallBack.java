package com.deservel.client;

import org.springframework.stereotype.Component;

/**
 * @author DeserveL
 * @date 2018-12-27 15:32
 * @since 1.0.0
 */
@Component
public class StockClientFallBack implements StockClient{

    /**
     * 减少库存
     *
     * @return
     */
    @Override
    public Integer reduceStock(){
        System.out.println("库存系统调用失败");
        return 0;
    }
}
