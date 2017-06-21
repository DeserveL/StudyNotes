package com.liuchong.studynotes.designpatterns.adapter.base;

/**
 * Created by Administrator on 2017/6/21 0021.
 */
public class Client {
    public static void main(String[] args) {
        Target t = new Adapter();
        t.request();
    }
}
