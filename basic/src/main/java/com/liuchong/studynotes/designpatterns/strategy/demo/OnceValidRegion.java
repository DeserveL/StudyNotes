package com.liuchong.studynotes.designpatterns.strategy.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这是我们针对单次消费的有效区间注解，可以给策略添加有效区间的设置
 *
 * @author DeserveL
 * @date 2017/6/12 21:23
 * @since 1.0.0
 */
@Target(ElementType.TYPE) //表示只能给类添加该注解
@Retention(RetentionPolicy.RUNTIME) //注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
public @interface OnceValidRegion {
    ValidRegion value() default @ValidRegion;
}
