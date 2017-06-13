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
package com.liuchong.studynotes.designpatterns.strategy.demo;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URI;

/**
 * @author DeserveL
 * @date 2017/6/12 21:50
 * @since 1.0.0
 */
public class Util {

    /**
     * 处理注解，传入一个策略类，返回它的TotalValidRegion注解
     *
     * @param clazz
     * @return
     */
    public static Annotation handleAnnotation(Class<? extends CalPrice> clazz) {
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        if (declaredAnnotations == null || declaredAnnotations.length == 0) {
            return null;
        } else {
            for (int i = 0; i < declaredAnnotations.length; i++) {
                if (declaredAnnotations[i] instanceof TotalValidRegion || declaredAnnotations[i] instanceof OnceValidRegion) {
                    return declaredAnnotations[i];
                }
            }
            return null;
        }
    }

    //获取扫描的包下面所有的class文件
    public static File[] getResour(URI packagePath) {
        File file = new File(packagePath);
        return file.listFiles(f -> f.getName().endsWith(".class"));
//        return file.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                if (pathname.getName().endsWith(".class")) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
    }
}
