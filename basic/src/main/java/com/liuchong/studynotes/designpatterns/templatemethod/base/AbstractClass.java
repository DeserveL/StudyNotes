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
package com.liuchong.studynotes.designpatterns.templatemethod.base;

/**
 * 实现一个模版方法，定义算法的骨架
 *
 * @author DeserveL
 * @date 2017/6/22 15:02
 * @since 1.0.0
 */
public abstract class AbstractClass {

    final public void templateMethod(){
        primitiveOperation1();
        primitiveOperation2();
    }

    abstract public void primitiveOperation1();

    abstract public void primitiveOperation2();
}
