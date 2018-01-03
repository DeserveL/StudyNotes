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

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 策略工厂 产生策略
 *
 * @author DeserveL
 * @date 2017/6/12 22:41
 * @since 1.0.0
 */
public class CalPriceFactory {

    private static final String CAL_PRICE_PACKAGE = "com.liuchong.studynotes.designpatterns.strategy.demo";//这里是一个常量，表示我们扫描策略的包

    private ClassLoader classLoader = getClass().getClassLoader();//我们加载策略时的类加载器，我们任何类运行时信息必须来自该类加载器

    private List<Class<? extends CalPrice>> calPriceList;//策略列表

    /**
     * 根据客户的总金额产生相应的策略
     *
     * @param customer
     * @return
     */
    public CalPrice createCalPrice(Customer customer) {
        //为了支持优先级排序，我们采用可排序的MAP支持,这个Map是为了储存我们当前策略的运行时类信息
        SortedMap<Integer, Class<? extends CalPrice>> clazzMap = new TreeMap<>();
        //在策略列表查找策略
        for (Class<? extends CalPrice> clazz : calPriceList) {
            //获取策略注解
            Annotation annotation = Util.handleAnnotation(clazz);
            //根据注解类型进行不同判断
            if (annotation instanceof TotalValidRegion) {
                TotalValidRegion totalValidRegion = (TotalValidRegion) annotation;
                //判断总金额是否在注解的区间
                if (customer.getTotalAmount() > totalValidRegion.value().min() && customer.getTotalAmount() <= totalValidRegion.value().max()) {
                    //将采用的策略放入MAP
                    clazzMap.put(totalValidRegion.value().order(), clazz);
                }
            } else if (annotation instanceof OnceValidRegion) {
                OnceValidRegion onceValidRegion = (OnceValidRegion) annotation;
                //判断单次金额是否在注解的区间，注意这次判断的是客户当次消费的金额
                if (customer.getAmount() > onceValidRegion.value().min() && customer.getAmount() <= onceValidRegion.value().max()) {
                    //将采用的策略放入MAP
                    clazzMap.put(onceValidRegion.value().order(), clazz);
                }
            }
        }
        //没有直接返回策略实例，而是将满足条件的策略类信息传递给代理，产生一个代理，从而满足策略重叠
        return CalPriceProxy.getProxy(clazzMap);
    }

    //单例，并且我们需要在工厂初始化的时候
    private CalPriceFactory(){
        init();
    }

    private void init(){
        calPriceList = new ArrayList<>();
        File[] resources = null;
        try {
            resources = Util.getResour(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".", "/")).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Class<CalPrice> calPriceClazz = null;
        try {
            calPriceClazz = (Class<CalPrice>) classLoader.loadClass(CalPrice.class.getName());//使用相同的加载器加载策略接口
            //载入包下的类
            for (int i = 0; i < resources.length; i++) {
                Class<?> clazz = classLoader.loadClass(CAL_PRICE_PACKAGE + "."+resources[i].getName().replace(".class", ""));
                if(calPriceClazz.isAssignableFrom(clazz) && clazz!=calPriceClazz){
                    calPriceList.add((Class<? extends CalPrice>) clazz);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static CalPriceFactory getInstance(){
        return CalPriceFactoryInstance.instance;
    }

    private static class CalPriceFactoryInstance{
        private static CalPriceFactory instance = new CalPriceFactory();
    }
}
