package com.soul.learn.common.mvp;

/**
 * Created by chenjianhua on 2017/6/9.
 */

public interface IView {
    /**
     * @todo
     */
    void showLoading();

    void hideLoading();

    void showMessage(String message);
}
