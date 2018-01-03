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
package com.deservel.designpatterns.decorator.base;

/**
 *
 * 具体的装饰器B
 *
 * @author DeserveL
 * @date 2017/6/23 11:39
 * @since 1.0.0
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void method() {
        System.out.println("针对该方法加一层B包装");
        super.method();
        System.out.println("B包装结束");
    }

    public void methodB() {
        System.out.println("被装饰器B扩展的方法");
    }
}
