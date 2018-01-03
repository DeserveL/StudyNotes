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
package com.deservel.basic.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author DeserveL
 * @date 2017/3/8 15:19
 * @since 1.0.0
 */
public class DynamicProxy {

    public static void main(String[] args) {
        Subject real = new RealSubject();
        //getClassLoader()：取得该Class对象的类装载器
        //类装载器负责从Java字符文件将字符流读入内存，并构造Class类对象
        /*ClassLoader loader
        ----指定被代理对象的类加载器
        Class[] Interfaces
        ----指定被代理对象所实现的接口
        InvocationHandler h ----指定需要调用的InvocationHandler对象*/
        Subject proxySubject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),new Class[]{Subject.class},new ProxyHandel(real));
        String a = proxySubject.doSomething();
        System.out.println(a);
    }

    public static void creatProxyClassFile(){
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name,new Class[]{Subject.class});
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name+".class");
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
