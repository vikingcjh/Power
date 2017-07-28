package com.soul.learn.power.ui.main.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soul.learn.power.R;
import com.soul.learn.power.bean.AppDetailsModel;
import com.soul.learn.power.constants.UiConstants;
import com.soul.learn.power.util.ImageLoaderUtils;


import java.util.HashMap;
import java.util.Map;


public class CatgViewHolder extends RecyclerView.ViewHolder{

    private Context mContext;

	public TextView tvName;
	public ImageView ivIcon;
    public ImageView ivPic;
	public TextView tvDownload;
	public ProgressBar pbDownload;
	public TextView tvPercent;
    public RelativeLayout rlDownload;
    public RelativeLayout rlProgress;

    public AppDetailsModel mInfo;


    public CatgViewHolder(final View itemView) {
        super(itemView);
        tvName = (TextView)itemView.findViewById(R.id.tv_name);
        ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
        tvDownload = (TextView)itemView.findViewById(R.id.tv_download);
        pbDownload = (ProgressBar)itemView.findViewById(R.id.pb_download);
        tvPercent = (TextView)itemView.findViewById(R.id.tv_percent);
        rlDownload = (RelativeLayout)itemView.findViewById(R.id.rl_download);
        rlProgress = (RelativeLayout)itemView.findViewById(R.id.rl_progress);

        mContext = itemView.getContext();

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    v.requestFocus();

                    //详情页

                }
                return false;
            }
        });
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    tvName.setText("focus");
                }else{
                    tvName.setText(mInfo.name);
                }
            }
        });
    }


    public void setData(AppDetailsModel info, final int position){
        this.mInfo = info;

        Log.i("NIODATA",info.name);
        tvName.setText(info.name);

        ImageLoaderUtils.displayRound(mContext,ivIcon,info.iconUrl);
        ImageLoaderUtils.display(mContext,ivPic,info.iconUrl);

        setButtonStatus(info, position);

        rlDownload.setTag(info);
        rlDownload.setTag(R.id.key_catg_position,position);

    }
    public void setPartData(AppDetailsModel info, final int position){

//        tvName.setText(info.name);

//        ImageLoaderUtils.displayRound(mContext,ivIcon,info.iconUrl);
//        ImageLoaderUtils.display(mContext,ivPic,info.iconUrl);

        setButtonStatus(info, position);

        rlDownload.setTag(info);
        rlDownload.setTag(R.id.key_catg_position,position);

    }

    private void updateDownloadingUi(long totalSize, long currentSize) {
        if (totalSize >= 0) {
            pbDownload.setMax((int) totalSize);
            tvPercent.setText(""+currentSize*100/totalSize+"%");
        } else {
            pbDownload.setMax(0);
        }
        pbDownload.setProgress((int) currentSize);
    }
    private void setButtonStatus(AppDetailsModel info, int pos) {
        long totalSize = info.totalSize;
        long currentSize = info.currentSize;
        int status = info.downloadStaus;

        switch (status) {
            case UiConstants.BUTTON_STATUS_NOT_INSTALL:
                tvDownload.setText(mContext.getResources().getString(R.string.dl_download));
                rlDownload.setEnabled(true);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);

                break;
            case UiConstants.BUTTON_STATUS_DOWNLOADING:
                tvDownload.setText(mContext.getResources().getString(R.string.downloading));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.GONE);

                if (!rlProgress.isShown()) {
                    if (rlProgress.getVisibility() != View.VISIBLE) {
                        rlProgress.setVisibility(View.VISIBLE);
                    }
                }
                updateDownloadingUi(totalSize,currentSize);
                break;
            case UiConstants.BUTTON_STATUS_DOWNLOAD_PENDDING:
                tvDownload.setText(mContext.getResources().getString(R.string.waitting));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);

                break;
            case UiConstants.BUTTON_STATUS_INSTALLED:
                tvDownload.setText(mContext.getResources().getString(R.string.detail_app_open));
                rlDownload.setEnabled(true);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);
                break;
            case UiConstants.BUTTON_STATUS_UPDATING:
                rlDownload.setVisibility(View.GONE);
                rlProgress.setVisibility(View.VISIBLE);
                updateDownloadingUi(totalSize,currentSize);
                break;
            case UiConstants.BUTTON_STATUS_NEED_UPDATE:
                rlDownload.setVisibility(View.VISIBLE);
                rlDownload.setEnabled(true);
                tvDownload.setText(mContext.getResources().getString(R.string.update));

                rlProgress.setVisibility(View.GONE);
                break;
            case UiConstants.BUTTON_STATUS_UPDATE_PENDDING:
                tvDownload.setText(mContext.getResources().getString(R.string.waitting));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);
                break;
            case UiConstants.BUTTON_STATUS_UPDATE_INSTALLING:
                tvDownload.setText(mContext.getResources().getString(R.string.installing));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);
                break;
            case UiConstants.BUTTON_STATUS_DOWNLOAD_INSTALLING:
                tvDownload.setText(mContext.getResources().getString(R.string.installing));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);
                break;
            case UiConstants.BUTTON_STATUS_DOWNLOAD_PAUSE:
                tvDownload.setText(mContext.getResources().getString(R.string.retring));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);
                break;
            case UiConstants.BUTTON_STATUS_UPDATE_PAUSE:
                tvDownload.setText(mContext.getResources().getString(R.string.retring));
                rlDownload.setEnabled(false);
                rlDownload.setVisibility(View.VISIBLE);
                rlProgress.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

}
