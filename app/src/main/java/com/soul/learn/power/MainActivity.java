package com.soul.learn.power;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soul.learn.power.log.LogUtil;
import com.soul.learn.power.test.Ifacetest;
import com.soul.learn.power.test.Outer;
import com.soul.learn.power.test.PClass;
import com.soul.learn.power.test.SubClass;
import com.soul.learn.power.test.Test;
import com.soul.learn.power.test.ViolateTest;
import com.soul.learn.power.test.ProxyTest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    LinearLayout container;
//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    message.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    message.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    message.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ViolateTest.getValue1();
        ViolateTest.getValue2();
        int x = Test.getValue();
        LogUtil.i("x =  " + Test.getValue());

        PClass pc = new SubClass();
        x = Ifacetest.i;
        x = PClass.x;
        Outer outer = new Outer();
        Outer.Inner1 inner1 = outer.new Inner1();
//        Outer.Inner1 inner1 = new Outer.Inner1();
        Test.caesarTest();
        Test.aesTest();
        ProxyTest.getInstance().test();
    }


}
