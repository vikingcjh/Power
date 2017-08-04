package com.soul.learn.power.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soul.learn.power.PowerApplication;
import com.soul.learn.power.R;
import com.soul.learn.power.bean.NaviInfo;
import com.soul.learn.power.busevent.AsyncThreadEvent;
import com.soul.learn.power.busevent.BackgroundEvent;
import com.soul.learn.power.busevent.PostingThreadEvent;
import com.soul.learn.power.busevent.ShowMainEvent;
import com.soul.learn.power.busevent.ShowUserDbEvent;
import com.soul.learn.power.db.BookDao;
import com.soul.learn.power.db.PkgDao;
import com.soul.learn.power.db.UserDao;
import com.soul.learn.power.log.LogUtil;
import com.soul.learn.power.sdkmanager.GetSdkModel;
import com.soul.learn.power.test.Ifacetest;
import com.soul.learn.power.test.Outer;
import com.soul.learn.power.test.PClass;
import com.soul.learn.power.test.ProxyTest;
import com.soul.learn.power.test.SubClass;
import com.soul.learn.power.test.Test;
import com.soul.learn.power.test.ViolateTest;
import com.soul.learn.power.ui.mainauto.adapter.MainFragmentAdapter;
import com.soul.learn.power.ui.vediorecord.CameraRecordFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
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

        context = this;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.addOnPageChangeListener(new MainVpListener());
        mViewPager.setOffscreenPageLimit(navigation.getChildCount());

        initFragment(savedInstanceState);
    }

    /**
     * 初始化
     */
    private void initFragment(Bundle savedInstanceState){
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new CameraRecordFragment());

        mViewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),fragments));

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public class MainVpListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            navigation.setSelectedItemId(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
