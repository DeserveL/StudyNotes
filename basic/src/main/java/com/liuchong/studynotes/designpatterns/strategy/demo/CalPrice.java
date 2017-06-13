package com.liuchong.studynotes.designpatterns.strategy.demo;

/**
 * 超市价格计算，支持策略的重叠
 *
 * @author DeserveL
 * @date 2017/6/12 21:35
 * @since 1.0.0
 */
public interface CalPrice {

    //根据原价返回一个最终的价格
    Double calPrice(Double originalPrice);

}
