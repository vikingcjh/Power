package com.soul.learn.power.ui.mainauto.model;


import com.soul.learn.common.mvp.BaseModel;
import com.soul.learn.power.bean.AppDetailsModel;
import com.soul.learn.power.bean.BaseResult;
import com.soul.learn.power.net.Api;
import com.soul.learn.power.net.ApiConstants;
import com.soul.learn.power.net.HostType;
import com.soul.learn.power.ui.mainauto.MainContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by chenjianhua on 2017/6/9.
 */

public class MainModel extends BaseModel implements MainContract.Model {


    @Override
    public Observable<List<AppDetailsModel>> getAppList(int size, int page) {

        Observable<BaseResult<List<AppDetailsModel>>> observable = Api.getDefault(HostType.STORE_API).getAppList(ApiConstants.STORE_CODE, size, page);
        return observable.map(new Function<BaseResult<List<AppDetailsModel>>, List<AppDetailsModel>>() {
            @Override
            public List<AppDetailsModel> apply(BaseResult<List<AppDetailsModel>> result) {
                return result.entity;
            }
        });
    }
}
