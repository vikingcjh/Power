package com.soul.learn.power.db.model;

import com.soul.learn.power.PowerApplication;
import com.soul.learn.power.busEvent.ShowUserDbEvent;
import com.soul.learn.power.db.UserDao;
import com.soul.learn.power.db.entity.User;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cjh on 2017/7/26.
 */

public class UserDbManager {
    private static final UserDbManager ourInstance = new UserDbManager();

    public static UserDbManager getInstance() {
        return ourInstance;
    }

    private UserDao userDao;

    private UserDbManager() {
        userDao = PowerApplication.getDaoSession().getUserDao();
    }

    public void getAllList() {
        Observable<List<User>> observable = Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<User>> e) throws Exception {

                List<User> result = userDao.loadAll();
                e.onNext(result);
                e.onComplete();
            }
        });
        Observer<List<User>> observer = new Observer<List<User>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<User> users) {
                StringBuffer sb = new StringBuffer();
                for (User user1 : users) {
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
                EventBus.getDefault().post(new ShowUserDbEvent(sb.toString()));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }).subscribe(observer);

    }

}
