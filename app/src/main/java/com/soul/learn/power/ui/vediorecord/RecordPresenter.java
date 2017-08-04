package com.soul.learn.power.ui.vediorecord;

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

public class RecordPresenter extends BasePresenter<RecordContract.Model,RecordContract.View> {


    public RecordPresenter(RecordContract.Model model , RecordContract.View rootView) {
        super(model, rootView);
    }

    public void loadData() {


    }
}
