package com.deservel.client;

import org.springframework.stereotype.Component;

/**
 * 积分hystrix 回调
 *
 *
 * @author DeserveL
 * @date 2018-12-27 15:32
 * @since 1.0.0
 */
@Component
public class CreditClientFallBack implements CreditClient{

    /**
     * 添加积分
     *
     * @return
     */
    @Override
    public Integer addCredit(){
        System.out.println("积分系统调用失败");
        return 0;
    }
}
