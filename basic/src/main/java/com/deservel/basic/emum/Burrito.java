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
package com.deservel.basic.emum;

import java.lang.reflect.Method;

import static com.liuchong.studynotes.basic.emum.Spiciness.*;
/**
 * @author DeserveL
 * @date 2017/2/27 14:13
 * @since 1.0.0
 */
public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree){
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Burrito is "+ degree;
    }

    public void change(){
        switch (degree){
            case NOT:degree = MILD;
                break;
        }
    }

    public static void main(String[] args){
        System.out.println(new Burrito(NOT));
        for (Method method : Spiciness.class.getMethods()) {
            System.out.println(method.getName());
        }
        for (Method method : Enum.class.getMethods()) {
            System.out.println(method.getName());
        }
    }
}
