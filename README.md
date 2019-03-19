# IOC_Simple
自定义注解，IOC注入框架

#原理分析

## 1、布局注入原理
```java
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
...
}
```
获得类(如 activity)->布局注解->注解的值->获取指定的方法（如 contentView()）-> 执行方法

## 2、控件注入原理
```java
    @InjectView(R.id.btn)
    private Button btn;
```
获得类 -> 获得所有属性->遍历属性->获取每个属性的注解->获取注解的值->获取指定方法(findViewById()) ->执行方法->设置赋值访问私有->为控件赋值

## 3、事件注入
```java
    @OnClick({R.id.btn,R.id.btn2})   //给 button 设置一个点击事件
    public void myClick(View view) {

        switch (view.getId()){
            case R.id.btn:
                Toast.makeText(MainActivity.this,"onClick1",Toast.LENGTH_LONG).show();
                break;

            case R.id.btn2:
                Toast.makeText(MainActivity.this,"onClick2",Toast.LENGTH_LONG).show();
                break;
        }

    }
```

事件3大成员
1、监听的方法名，setOnClickListener()

2、监听的对象，View.OnClickListener()

3、回调方法，onClick(View view)

对于使用者，只需要关注回调方法即可

基于动态代理方式，拦截onClick方法，执行我们自定义的方法。

获取类->获得所有方法->遍历方法 ->每个方法的注解-> 遍历所有注解-> 获取指定的注解(类型，判空)->获取注解3大成员->获取注解值-> 遍历注解值
->赋值控件（findViewById）->获取指定方法（setOnClickListener）->执行方法


