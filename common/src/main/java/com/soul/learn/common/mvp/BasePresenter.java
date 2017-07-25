package com.soul.learn.common.mvp;

/**
 * Created by chenjianhua on 2017/6/9.
 */

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter{
    protected final String TAG = this.getClass().getSimpleName();
    protected M mModel;
    protected V mRootView;

    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

        if (mModel != null)
            mModel.onDestroy();
        this.mModel = null;
        this.mRootView = null;
    }
}
