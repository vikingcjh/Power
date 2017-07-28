package com.soul.learn.power.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.soul.learn.power.R;
import com.soul.learn.power.bean.NaviInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianhua on 2017/6/12.
 */

public class CstNavibar extends RelativeLayout {
    public static final int NAVI_TYPE_CAT_TEXT = 1;
    public static final int NAVI_TYPE_CAT_ICON = 2;
    public static final int NAVI_TYPE_LOCAL_TEXT = 3;
    public static final int NAVI_TYPE_LOCAL_ICON = 4;

    public List<NaviInfo> mNaviInfos = new ArrayList<>();

    public Context mContext;

    private NaviFocusChangeListener mNaviFocusChangeListener = new NaviFocusChangeListener();

    private NaviTouchListener mNaviTouchListener = new NaviTouchListener();

    private ISetViewpage mISetViewpage;
    private int mFocusId;

    public interface ISetViewpage{
        void showPage(int id);
    }

    public CstNavibar(Context context) {
        super(context);
        init(context);
    }

    public CstNavibar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CstNavibar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context context) {
        mContext = context;
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        setClipChildren(false);
        setClipToPadding(false);
        /*mNaviInfos.add(new NaviInfo(0,NAVI_TYPE_CAT_TEXT,"首页"));
        mNaviInfos.add(new NaviInfo(0,NAVI_TYPE_CAT_TEXT,"导航地图"));
        mNaviInfos.add(new NaviInfo(0,NAVI_TYPE_CAT_TEXT,"音乐播放"));
        mNaviInfos.add(new NaviInfo(0,NAVI_TYPE_CAT_TEXT,"新闻阅读"));

        mNaviInfos.add(new NaviInfo(0,NAVI_TYPE_LOCAL_ICON,"设置"));

        addViews(mNaviInfos);*/
    }

    public void initTabs(List<NaviInfo> infos){
        this.mNaviInfos = infos;
        addViews(mNaviInfos);
    }

    public void addViews(List<NaviInfo> naviInfos){
        if (naviInfos==null || naviInfos.size()==0){
            return;
        }

        int i;
        for(i=0;i<naviInfos.size();i++) {
            if (naviInfos.get(i).type == NAVI_TYPE_CAT_TEXT)
                addItem(i,naviInfos.get(i));
            if (naviInfos.get(i).type == NAVI_TYPE_LOCAL_ICON)
                addLocalView(i,naviInfos.get(i));
        }
    }

    public void addItem(int id,NaviInfo naviInfo){
        RelativeLayout itemContainer = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_navibar, null);
        itemContainer.setId(id);
        itemContainer.setTag(R.id.navi_id, id);
        itemContainer.setTag(R.id.navi_type, NAVI_TYPE_CAT_TEXT);
        itemContainer.setTag(R.id.navi_info, naviInfo);
        LayoutParams layoutParams= new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        layoutParams.width = mContext.getResources().getDimensionPixelOffset(R.dimen.dp_180);
        layoutParams.leftMargin = layoutParams.width * id;
        itemContainer.setLayoutParams(layoutParams);
        TextView tvName = (TextView) itemContainer.findViewById(R.id.tv_name);
        ImageView ivIcon = (ImageView) itemContainer.findViewById(R.id.iv_icon);
        View focus_line = (View) itemContainer.findViewById(R.id.focus_line);
        tvName.setText(naviInfo.name);
        tvName.setVisibility(View.VISIBLE);
        focus_line.setVisibility(View.GONE);
        ivIcon.setVisibility(View.GONE);
        itemContainer.setOnFocusChangeListener(mNaviFocusChangeListener);
        itemContainer.setOnTouchListener(mNaviTouchListener);
        addView(itemContainer);
    }
    public void addLocalView(int id, NaviInfo naviInfo){
        RelativeLayout itemContainer = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_navibar, null);
        itemContainer.setId(id);
        itemContainer.setTag(R.id.navi_id, id);
        itemContainer.setTag(R.id.navi_type, NAVI_TYPE_LOCAL_ICON);
        itemContainer.setTag(R.id.navi_name, naviInfo.name);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        lp.rightMargin=0;
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        itemContainer.setLayoutParams(lp);
        TextView tvName = (TextView) itemContainer.findViewById(R.id.tv_name);
        ImageView ivIcon = (ImageView) itemContainer.findViewById(R.id.iv_icon);
        View focus_line = (View) itemContainer.findViewById(R.id.focus_line);
        tvName.setVisibility(View.GONE);
        focus_line.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.setBackgroundResource(R.drawable.home_set);

        itemContainer.setOnFocusChangeListener(mNaviFocusChangeListener);
        itemContainer.setOnTouchListener(mNaviTouchListener);

        addView(itemContainer);
    }


    public void setInterface(ISetViewpage setViewpage){
        mISetViewpage = setViewpage;
    }

    public boolean setTabId(int id){
        if (id >= 0 && id < mNaviInfos.size()) {
            return findViewById(id).requestFocus();
        }
        return false;
    }

    private class NaviFocusChangeListener implements OnFocusChangeListener {
        @Override
        public void onFocusChange(final View v, final boolean viewIsFocus) {
            int type = (int) v.getTag(R.id.navi_type);
            if (type == 0) {
                return;
            }
            if (viewIsFocus){
                mFocusId = v.getId();
                mISetViewpage.showPage(mFocusId);
            }

            if (type == NAVI_TYPE_CAT_TEXT) {
                TextView tvName = (TextView) v.findViewById(R.id.tv_name);
                View focusLine = v.findViewById(R.id.focus_line);
                if (viewIsFocus) {
                    tvName.setTextColor(mContext.getResources().getColor(R.color.color_17a0f7));
                    focusLine.setVisibility(View.VISIBLE);
                } else {
                    tvName.setTextColor(mContext.getResources().getColor(R.color.color_9aeeeeff));
                    focusLine.setVisibility(View.GONE);
                }
            }
            if (type == NAVI_TYPE_LOCAL_ICON){
                ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_icon);
                if (viewIsFocus) {
                    ivIcon.setBackgroundResource(R.drawable.home_set_pressed);
                } else {
                    ivIcon.setBackgroundResource(R.drawable.home_set);
                }
            }
        }
    }

    private class NaviTouchListener implements OnTouchListener {
        /**
         * Called when a touch event is dispatched to a view. This allows listeners to
         * get a chance to respond before the target view.
         *
         * @param v     The view the touch event has been dispatched to.
         * @param event The MotionEvent object containing full information about
         *              the event.
         * @return True if the listener has consumed the event, false otherwise.
         */
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (MotionEvent.ACTION_DOWN == action) {
                mFocusId = v.getId();

                v.requestFocus();
                mISetViewpage.showPage(mFocusId);
            }
            return false;
        }
    }

    private class NaviKeyListener implements OnKeyListener {

        /**
         * Called when a hardware key is dispatched to a view. This allows listeners to
         * get a chance to respond before the target view.
         * <p>Key presses in software keyboards will generally NOT trigger this method,
         * although some may elect to do so in some situations. Do not assume a
         * software input method has to be key-based; even if it is, it may use key presses
         * in a different way than you expect, so there is no way to reliably catch soft
         * input key presses.
         *
         * @param v       The view the key has been dispatched to.
         * @param keyCode The code for the physical key that was pressed
         * @param event   The KeyEvent object containing full information about
         *                the event.
         * @return True if the listener has consumed the event, false otherwise.
         */
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (event.getAction()){
                case KeyEvent.ACTION_DOWN:

                    break;

                case KeyEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    }
}
