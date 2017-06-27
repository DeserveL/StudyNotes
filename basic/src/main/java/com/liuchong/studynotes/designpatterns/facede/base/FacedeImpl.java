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
package com.liuchong.studynotes.designpatterns.facede.base;

/**
 * 外观接口
 *
 * @author DeserveL
 * @date 2017/6/27 10:55
 * @since 1.0.0
 */
public class FacedeImpl implements Facede {
    private Sub1 sub1;
    private Sub2 sub2;
    private Sub3 sub3;

    public FacedeImpl() {
        super();
        this.sub1 = new Sub1Impl();
        this.sub2 = new Sub2Impl();
        this.sub3 = new Sub3Impl();
    }

    @Override
    public void function12() {
        sub1.function1();
        sub2.function2();
    }

    @Override
    public void function13() {
        sub1.function1();
        sub3.function3();
    }

    @Override
    public void function123() {
        sub1.function1();
        sub2.function2();
        sub3.function3();
    }
}
