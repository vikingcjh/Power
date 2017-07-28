package com.soul.learn.power.ui.main.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.soul.learn.power.R;
import com.soul.learn.power.bean.AppDetailsModel;


public class FooterCatgViewHolder extends RecyclerView.ViewHolder{

    private Context mContext;

	public TextView tvLoadmore;
	public ImageView ivNomore;
	public ProgressBar pbLoading;
    public AppDetailsModel mInfo;

    public FooterCatgViewHolder(final View itemView) {
        super(itemView);
        tvLoadmore = (TextView)itemView.findViewById(R.id.tv_loadmore);
        ivNomore = (ImageView) itemView.findViewById(R.id.iv_nomore);
        pbLoading = (ProgressBar)itemView.findViewById(R.id.pb_loadmore);

        mContext = itemView.getContext();
    }


    public void setData(AppDetailsModel info, int position){
        this.mInfo = info;
//        tvName.setText(info.name);
        if(position==0){

        }

    }
}
