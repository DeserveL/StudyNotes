package com.liuchong.studynotes.designpatterns.strategy.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个嵌套注解
 *
 * @author DeserveL
 * @date 2017/6/12 21:23
 * @since 1.0.0
 */
@Target(ElementType.TYPE) //表示只能给类添加该注解
@Retention(RetentionPolicy.RUNTIME) //注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
public @interface ValidRegion {
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;

    //既然可以任意组合，我们就需要给策略定义下顺序，就比如刚才说的2000那个例子，按先返后打折的顺序是800，反过来就是600了。
    //所以我们必须支持这一特性，默认0，为最优先
    int order() default 0;
}
