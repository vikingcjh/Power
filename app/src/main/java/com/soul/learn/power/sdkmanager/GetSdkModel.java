package com.soul.learn.power.sdkmanager;


import android.widget.Toast;

import com.soul.learn.power.PowerApplication;
import com.soul.learn.power.bean.SdkUpdateBean;
import com.soul.learn.power.busevent.ShowUserDbEvent;
import com.soul.learn.power.net.Api;
import com.soul.learn.power.net.HostType;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenjianhua on 2017/6/9.
 */

public class GetSdkModel  {


    public void getUpdateInfo() {

        Observable<SdkUpdateBean> observable = Api.getDefault(HostType.SDK_API)
                .getSdkUpdateInfo("229700","","","","","","","","0","com.leplay.sdk.plugin");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SdkUpdateBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SdkUpdateBean result) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(result.toString());
                            /*sb.append("id:");
                            sb.append(result.toString());
                            sb.append("\r\n");
                            sb.append("name:");
                            sb.append(user1.getName());
                            sb.append("\r\n");
                            sb.append("age:");
                            sb.append(user1.getAge());
                            sb.append("\r\n");*/
                        EventBus.getDefault().post(new ShowUserDbEvent(sb.toString()));
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
