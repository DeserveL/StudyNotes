package com.deservel.client;

import org.springframework.stereotype.Component;

/**
 * @author DeserveL
 * @date 2018-12-27 15:32
 * @since 1.0.0
 */
@Component
public class StorageClientFallBack implements StorageClient{

    /**
     * 发货
     *
     */
    @Override
    public void storage(){
        System.out.println("发货系统调用失败");
    }
}
