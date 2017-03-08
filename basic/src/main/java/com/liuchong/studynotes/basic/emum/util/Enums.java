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
package com.liuchong.studynotes.basic.emum.util;

import java.util.Random;

/**
 * 随机选择
 *
 * @author DeserveL
 * @date 2017/2/27 15:05
 * @since 1.0.0
 */
public class Enums {

    /**
     *默认当前系统时间对应的相对时间有关的数字作为种子数
     */
    private static Random rand = new Random();

    /**
     * 从enum中随机选择
     *
     * @param ec
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }

    /**
     *
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T random(T[] values){
        return values[rand.nextInt(values.length)];
    }
}
