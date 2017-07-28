package com.soul.learn.power.ui.main.contract;

import com.soul.learn.common.mvp.IModel;
import com.soul.learn.common.mvp.IView;
import com.soul.learn.power.bean.AppDetailsModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chenjianhua on 2017/5/19.
 */

public interface CategoryContract {

    interface View extends IView {
        void showData(String str);
        void notifyCatgData(List<AppDetailsModel> df);
        void notifyLoadMoreData(List<AppDetailsModel> df);

        void showLoadMore();
        void hideLoadMore();
    }

    interface Model extends IModel {
        Observable<List<AppDetailsModel>> getAppList(int size, int page);
    }

}
