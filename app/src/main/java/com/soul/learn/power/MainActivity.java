package com.soul.learn.power;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soul.learn.power.busEvent.AsyncThreadEvent;
import com.soul.learn.power.busEvent.BackgroundEvent;
import com.soul.learn.power.busEvent.PostingThreadEvent;
import com.soul.learn.power.busEvent.ShowMainEvent;
import com.soul.learn.power.busEvent.ShowUserDbEvent;
import com.soul.learn.power.db.BookDao;
import com.soul.learn.power.db.PkgDao;
import com.soul.learn.power.db.UserDao;
import com.soul.learn.power.db.entity.Book;
import com.soul.learn.power.db.entity.User;
import com.soul.learn.power.db.model.UserDbManager;
import com.soul.learn.power.log.LogUtil;
import com.soul.learn.power.test.Ifacetest;
import com.soul.learn.power.test.Outer;
import com.soul.learn.power.test.PClass;
import com.soul.learn.power.test.SubClass;
import com.soul.learn.power.test.Test;
import com.soul.learn.power.test.ViolateTest;
import com.soul.learn.power.test.ProxyTest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv_user)
    TextView tvUser;


    private Context context;
    private UserDao userDao;
    private PkgDao pkgDao;
    private BookDao bookDao;

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
//                    message.setText(R.string.title_notifications);
                    EventBus.getDefault().post(new ShowMainEvent("hello world!!"));
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
        userDao = ((PowerApplication)getApplication()).getDaoSession().getUserDao();
        bookDao = ((PowerApplication)getApplication()).getDaoSession().getBookDao();
//        findViewById(R.id.container);
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.btn})
    public void onBtnClick(Button btn){
        switch (btn.getId()) {
            case R.id.btn:
                User user = new User();
                user.setAge(20);
                user.setName("test"+System.currentTimeMillis());
                userDao.insert(user);

                UserDbManager.getInstance().getAllList();
                /*List<User> list = userDao.loadAll();
                StringBuffer sb = new StringBuffer();
                for (User user1 : list) {
                    sb.append("id:");
                    sb.append(user1.getId());
                    sb.append("\r\n");
                    sb.append("name:");
                    sb.append(user1.getName());
                    sb.append("\r\n");
                    sb.append("age:");
                    sb.append(user1.getAge());
                    sb.append("\r\n");
                }
                tvUser.setText(sb.toString());*/

                break;
        }

    }

    /**
     * 主线程 不能进行耗时操作
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMainUi(ShowMainEvent event) {
        message.setText(event.message);
        Toast.makeText(context,event.message,Toast.LENGTH_SHORT).show();
    }

    /**
     * background 事件，公用background线程，不能进行耗时操作
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEvent(BackgroundEvent event) {

    }

    /**
     * 与poster同线程
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostingThreadEvent(PostingThreadEvent event) {

    }

    /**
     * 异步线程处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsycThreadEvent(AsyncThreadEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onShowUserDbEvent(ShowUserDbEvent event) {
        tvUser.setText(event.message);
    }

    private void sendEvent(String msg){
//        EventBus.getDefault().post(new ShowMainEvent("hello world!!"));
        EventBus.getDefault().post(new ShowMainEvent(msg));
    }
}
