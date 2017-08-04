package com.soul.learn.power.ui.mainauto.presenter;

import android.widget.Toast;


import com.soul.learn.common.mvp.BasePresenter;
import com.soul.learn.power.PowerApplication;
import com.soul.learn.power.bean.AppDetailsModel;
import com.soul.learn.power.ui.mainauto.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenjianhua on 2017/5/19.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.Model,CategoryContract.View> {


    public CategoryPresenter(CategoryContract.Model model , CategoryContract.View rootView) {
        super(model, rootView);
    }

    public void loadData() {
        mModel.getAppList(10000,1)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mRootView.hideLoading();
                    }
                })
                .subscribe(new Observer<List<AppDetailsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<AppDetailsModel> result) {
                        mRootView.notifyCatgData(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(PowerApplication.getGlobalContext(),
                                e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void loadData(int page,int size) {
        mModel.getAppList(size,page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mRootView.showLoadMore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mRootView.hideLoadMore();
                    }
                })
                .subscribe(new Observer<List<AppDetailsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<AppDetailsModel> result) {
                        mRootView.notifyLoadMoreData(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(PowerApplication.getGlobalContext(),
                                e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
