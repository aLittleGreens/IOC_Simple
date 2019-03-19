package demo.mvp.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import demo.mvp.ioc.annotation.BaseEvent;
import demo.mvp.ioc.annotation.ContentView;
import demo.mvp.ioc.annotation.InjectView;

public class InjectManager {

    public static void inject(Activity activity) {
        //布局注入
        injectLayout(activity);
        //控件注入
        injectViews(activity);
        //事件注入
        injectEvent(activity);


    }

    private static void injectEvent(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();

//        获取当前类所有的方法，包括私有
        Method[] methods = aClass.getDeclaredMethods();

        for (Method method : methods) {

//            获取每个方法的注解（override、onClick）
            Annotation[] annotations = method.getAnnotations();
//            遍历该方法所有注解
            for (Annotation annotation : annotations) {
                //获取onClick上所有注解
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    BaseEvent baseEvent = annotationType.getAnnotation(BaseEvent.class);
                    if (baseEvent != null) {
                        //获取点击事件重要3个成员
                        String listenerSetter = baseEvent.listenerSetter();
                        Class<?> listenerType = baseEvent.listenerType();
                        String listenerCallback = baseEvent.listenerCallback();

                        //通过代理的方式操作这个对象，并且拦截onClick，执行自定义事件

                        ListenerInvocationhandler listenerInvocationhandler = new ListenerInvocationhandler(activity);
                        listenerInvocationhandler.addMethod(listenerCallback,method);
                        Object instener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, listenerInvocationhandler);

                        try {
                            //获取onClick的value值
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            //获取注解上的值
                            int viewIds[] = (int[]) valueMethod.invoke(annotation);


                            for (int viewId : viewIds) {
//                                找到控件
                                View view = activity.findViewById(viewId);
//                                 获取指定的方法
                                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
//                                执行方法
                                setter.invoke(view, instener);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        }

    }

    private static void injectViews(Activity activity) {

        Class<? extends Activity> aClass = activity.getClass();
        //获取所有属性，包括私有
        Field[] fields = aClass.getDeclaredFields();

        if (fields != null) {

            for (Field field : fields) {
                // 拿到注解的值
                InjectView fieldAnnotation = field.getAnnotation(InjectView.class);
                if (fieldAnnotation != null) {
                    //拿到属性的值
                    int viewId = fieldAnnotation.value();

                    //方式一
//                    View viewById = activity.findViewById(viewId);
                    try {
//                    方式二  通过反射的方式
                        Method findViewById = aClass.getMethod("findViewById", int.class);
                        Object viewById = findViewById.invoke(activity, viewId);

                        field.setAccessible(true); // 设置访问private权限
                        // 赋值
                        field.set(activity, viewById);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }

        }


    }

    private static void injectLayout(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        ContentView annotation = aClass.getAnnotation(ContentView.class);
        if (annotation != null) {
            int layoutId = annotation.value();
//              方式一
            activity.setContentView(layoutId);

//            方式二  通过反射调用setContentView
            try {
                Method method = aClass.getMethod("setContentView", int.class);
                method.invoke(activity, layoutId);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
