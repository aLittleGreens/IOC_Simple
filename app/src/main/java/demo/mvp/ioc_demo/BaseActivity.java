package demo.mvp.ioc_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import demo.mvp.ioc.InjectManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //帮助子类进行布局、控件、事件注入
        InjectManager.inject(this);
    }
}
