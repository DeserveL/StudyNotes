package com.deservel.designpatterns.adapter.base;

/**
 * 适配器类
 *
 * Created by Administrator on 2017/6/21 0021.
 */
public class Adapter implements Target {

    @Override
    public void request() {
        new Adaptee().specificRequest();
    }
}
