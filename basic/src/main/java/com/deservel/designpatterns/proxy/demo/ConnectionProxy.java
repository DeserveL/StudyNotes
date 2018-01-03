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
package com.deservel.designpatterns.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author DeserveL
 * @date 2017/6/13 18:38
 * @since 1.0.0
 */
public class ConnectionProxy implements InvocationHandler {

    private Connection connection;

    public ConnectionProxy(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Connection.class.isAssignableFrom(proxy.getClass()) && method.getName().equals("close")){
            //我们不执行真正的close方法
            //method.invoke(connection, args);
            //将连接归还连接池
            DataSource.getInstance().recoveryConnection(connection);
        }else{
            return method.invoke(connection,args);
        }
        return null;
    }

    public Connection getConnection() {
        return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{Connection.class},this);
    }
}
