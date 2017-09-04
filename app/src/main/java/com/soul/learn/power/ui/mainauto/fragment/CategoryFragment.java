package com.soul.learn.power.ui.mainauto.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;


import com.soul.learn.power.R;
import com.soul.learn.power.bean.AppDetailsModel;
import com.soul.learn.power.ui.base.BaseFragment;
import com.soul.learn.power.ui.mainauto.adapter.CategoryAdapter;
import com.soul.learn.power.ui.mainauto.contract.CategoryContract;
import com.soul.learn.power.ui.mainauto.model.CategoryModel;
import com.soul.learn.power.ui.mainauto.presenter.CategoryPresenter;
import com.soul.learn.power.widget.custom.CstItemDecoration;
import com.soul.learn.power.widget.custom.CstRcvOnScrollListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenjianhua on 2017/6/13.
 */

public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryContract.View{

    private int id;
    private String name;

    private RecyclerView mRcv;

    private List<AppDetailsModel> mInfoList = new ArrayList<>();
    private CategoryAdapter mAdapter;
    private int pageIndex = 0;
    public static final int PAGE_SIZE = 10;
    public boolean isLoadAll = false;
    public boolean isLoading = false;

    private CstRcvOnScrollListener.Callbacks mCallBacks = new CstRcvOnScrollListener.Callbacks() {
        @Override
        public void onLoadMore() {
            if (!isLoadAll){
                isLoading = true;
                mPresenter.loadData(pageIndex+1,PAGE_SIZE);
            }
        }

        @Override
        public boolean isLoading() {
            return isLoading;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return isLoadAll;
        }
    };

    public CategoryFragment(){

    }

    @Override
    public void initPresenter() {
        if (mPresenter == null){
            CategoryModel model = new CategoryModel();
            mPresenter = new CategoryPresenter(model,this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initWidget() {
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            name = getArguments().getString("name");
        }
        Log.i("NIODATA","id = "+id);

        mRcv = (RecyclerView) rootView.findViewById(R.id.rcv_content);
        mRcv.setLayoutManager(new GridLayoutManager(mContext,5));
        mRcv.addItemDecoration(new CstItemDecoration(mContext,-1,5));

        mInfoList.clear();
        mAdapter = new CategoryAdapter(mContext, mInfoList);
        ((SimpleItemAnimator)mRcv.getItemAnimator()).setSupportsChangeAnimations(false);
//        mRcv.getItemAnimator().setSupportsChangeAnimations(false);
        mRcv.setAdapter(mAdapter);
        mRcv.addOnScrollListener(new CstRcvOnScrollListener(mCallBacks));
    }

    @Override
    public void startModel() {
        if (id == 1 || id==0) mPresenter.loadData(pageIndex+1,PAGE_SIZE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
    public void showData(String str) {

    }

    @Override
    public void notifyCatgData(List<AppDetailsModel> infos) {
        if (infos==null || infos.size()<1){
            return;
        }
        mInfoList.clear();
        mInfoList.addAll(infos);
        sortAppAndHandleInstallState();
        pageIndex++;

        int size = infos.size();
        if (size>0 && size>=PAGE_SIZE){
            mAdapter.notifyDataSetChanged();
        }
        else if (size>0 && size<PAGE_SIZE) {
            isLoadAll = true;
            mAdapter.displayLoadingRow(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyLoadMoreData(List<AppDetailsModel> infos) {
        if (infos==null){
            return;
        }
        int size = infos.size();
        if (size>0 && size>=PAGE_SIZE){
            mInfoList.addAll(infos);
            sortAppAndHandleInstallState();

            pageIndex++;
            mAdapter.notifyDataSetChanged();
        }
        else if (size>0 && size<PAGE_SIZE) {
            mInfoList.addAll(infos);
            sortAppAndHandleInstallState();

            pageIndex++;
            isLoadAll = true;
            mAdapter.displayLoadingRow(false);
            mAdapter.notifyDataSetChanged();
        } else {
            isLoadAll = true;
            mAdapter.displayLoadingRow(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadMore() {
        isLoading = true;
    }

    @Override
    public void hideLoadMore() {
        isLoading = false;
    }


    private void sortAppAndHandleInstallState() {
        for (int i = 0; i < mInfoList.size(); i++) {
            mInfoList.get(i).downloadStaus = -1;
        }
    }

}
