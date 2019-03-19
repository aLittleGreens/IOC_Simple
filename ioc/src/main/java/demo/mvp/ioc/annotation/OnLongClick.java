package demo.mvp.ioc.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@BaseEvent(listenerSetter ="setOnLongClickListener",listenerType = View.OnLongClickListener.class,listenerCallback = "onLongClick")
public @interface OnLongClick {
    int[] value();
}
