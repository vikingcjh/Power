package com.soul.learn.power.ui.videolive;

import com.soul.learn.common.mvp.BasePresenter;

/**
 * Created by chenjianhua on 2017/5/19.
 */

public class VideoLivePresenter extends BasePresenter<VideoLiveContract.Model,VideoLiveContract.View> {


    public VideoLivePresenter(VideoLiveContract.Model model , VideoLiveContract.View rootView) {
        super(model, rootView);
    }

    public void loadData() {


    }
}
