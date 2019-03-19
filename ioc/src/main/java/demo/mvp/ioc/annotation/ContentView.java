package demo.mvp.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //注解作用于类上
@Retention(RetentionPolicy.RUNTIME)// Jvm加载时通过反射获取注解的值
public @interface ContentView {
    int value();
}
