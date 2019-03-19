package demo.mvp.ioc_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import demo.mvp.ioc.annotation.ContentView;
import demo.mvp.ioc.annotation.InjectView;
import demo.mvp.ioc.annotation.OnClick;
import demo.mvp.ioc.annotation.OnLongClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @InjectView(R.id.btn)
    private Button btn;

    @InjectView(R.id.btn2)
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Toast.makeText(this,btn.getText(),Toast.LENGTH_LONG).show();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "onClick: ");
//                Toast.makeText(MainActivity.this,"onClick",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.e(TAG, "onLongClick: ");
//                Toast.makeText(MainActivity.this,"onLongClick",Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
    }

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

    @OnLongClick({R.id.btn,R.id.btn2}) //给 button 设置一个长按事件
    public boolean myOnLongClick(View view){
        switch (view.getId()){
            case R.id.btn:
                Toast.makeText(MainActivity.this,"myOnLongClick1",Toast.LENGTH_LONG).show();
                break;

            case R.id.btn2:
                Toast.makeText(MainActivity.this,"myOnLongClick2",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
