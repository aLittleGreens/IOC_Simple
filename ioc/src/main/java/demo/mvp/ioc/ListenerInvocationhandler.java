package demo.mvp.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ListenerInvocationhandler implements InvocationHandler {

//    需要拦截的目标，比如需要拦截MainActivity中的方法
    private Object target;

    //有可能有多个onClick方法，
    private HashMap<String,Method> hashMap = new HashMap<>();

    public ListenerInvocationhandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(target != null){
            String name = method.getName();
            method = hashMap.get(name);
            if(method != null){
                //执行自定义的方法
               return method.invoke(target,args);
            }

        }

        return null;
    }

    /**
     * 添加拦截方法
     * @param methodName 将要拦截的方法
     * @param method 代替执行的方法
     */
    public void addMethod(String methodName,Method method){
        hashMap.put(methodName,method);
    }
}
