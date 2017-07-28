package com.soul.learn.power.ui.main;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bumptech.glide.Glide;
import com.soul.learn.power.R;
import com.soul.learn.power.bean.NaviInfo;
import com.soul.learn.power.ui.base.BaseActivity;
import com.soul.learn.power.ui.main.adapter.MainFragmentAdapter;
import com.soul.learn.power.ui.main.model.MainModel;
import com.soul.learn.power.widget.custom.CstNavibar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View,CstNavibar.ISetViewpage{

    private Context mContext;
    private ViewPager mViewPager;
    private CstNavibar mCstNavibar;
    private MainVpListener mMainVpListener= new MainVpListener();

    public List<NaviInfo> mNaviInfos = new ArrayList<>();

    @Override
    public void initPresenter() {
        if (mPresenter == null){
            MainModel model = new MainModel();
            mPresenter = new MainPresenter(model,this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ui_main;
    }

    @Override
    public void initWidget() {
        mContext = this;
        mCstNavibar = (CstNavibar) findViewById(R.id.cst_navibar);
        mCstNavibar.setInterface(this);
        mNaviInfos.add(new NaviInfo(0,CstNavibar.NAVI_TYPE_CAT_TEXT,"首页","com.soul.learn.power.ui.main.fragment.CategoryFragment"));
        mNaviInfos.add(new NaviInfo(1,CstNavibar.NAVI_TYPE_CAT_TEXT,"导航地图","com.soul.learn.power.ui.main.fragment.CategoryFragment"));
        mNaviInfos.add(new NaviInfo(2,CstNavibar.NAVI_TYPE_CAT_TEXT,"音乐播放","com.soul.learn.power.ui.main.fragment.CategoryFragment"));
        mNaviInfos.add(new NaviInfo(3,CstNavibar.NAVI_TYPE_CAT_TEXT,"新闻阅读","com.soul.learn.power.ui.main.fragment.CategoryFragment"));
        mNaviInfos.add(new NaviInfo(4,CstNavibar.NAVI_TYPE_LOCAL_ICON,"设置","com.soul.learn.power.ui.main.fragment.CategoryFragment"));
        mCstNavibar.initTabs(mNaviInfos);

        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mViewPager.addOnPageChangeListener(new MainVpListener());
        mViewPager.setOffscreenPageLimit(mNaviInfos.size());

    }

    @Override
    public void startModel() {
//        mPresenter.loadData();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragment(savedInstanceState,mNaviInfos);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Glide.get(this).clearMemory();
    }

    @Override
    public void showData(String s) {
//        tv.setText(s);
    }

    /**
     * @todo
     */
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showPage(int id) {
        if (mViewPager != null){
            mViewPager.setCurrentItem(id);
        }
    }


    public class MainVpListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mCstNavibar.setTabId(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 初始化
     */
    private void initFragment(Bundle savedInstanceState,List<NaviInfo> naviInfos){
        List<Fragment> fragments = new ArrayList<>();
        for (NaviInfo info : naviInfos) {
            if (info.className!=null && (!info.className.equals(""))){
                try {
                    Class<?> c = Class.forName(info.className);
                    Fragment obj = (Fragment)(c.newInstance());
                    if (obj instanceof Fragment){
                        Bundle bd = new Bundle();
                        bd.putInt("id",info.id);
                        bd.putString("name",info.name);
                        ((Fragment) obj).setArguments(bd);
                        fragments.add((Fragment) obj);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),fragments));

    }

}
