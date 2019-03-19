package demo.mvp.ioc.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseEvent {

    //    1、监听方法名
    String listenerSetter();

    //    2、监听的对象
    Class<?> listenerType();

    //   3、回调的方法名 “onClick”
    String listenerCallback();
}
