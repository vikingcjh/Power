package com.soul.learn.power.ui.mainauto;

import com.soul.learn.common.mvp.IModel;
import com.soul.learn.common.mvp.IView;
import com.soul.learn.power.bean.AppDetailsModel;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by chenjianhua on 2017/5/19.
 */

public interface MainContract {

    interface View extends IView {
        void showData(String str);
    }

    interface Model extends IModel {
        Observable<List<AppDetailsModel>> getAppList(int size, int page);
    }

}
