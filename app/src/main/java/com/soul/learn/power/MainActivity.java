package com.soul.learn.power;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.soul.learn.power.log.LogUtil;
import com.soul.learn.power.test.Ifacetest;
import com.soul.learn.power.test.Outer;
import com.soul.learn.power.test.PClass;
import com.soul.learn.power.test.SubClass;
import com.soul.learn.power.test.Test;
import com.soul.learn.power.test.ViolateTest;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ViolateTest.getValue1();
        ViolateTest.getValue2();
        int x = Test.getValue();
        LogUtil.i("x =  "+Test.getValue());

        PClass pc = new SubClass();
        x = Ifacetest.i;
        x =PClass.x;
        Outer outer = new Outer();
        Outer.Inner1 inner1 = outer.new Inner1();
//        Outer.Inner1 inner1 = new Outer.Inner1();
    }

}
