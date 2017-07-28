package com.soul.learn.power.widget.custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.soul.learn.power.R;


/**
 * Created by chenjianhua on 2017/6/15.
 */

public class CstItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount;
    private Context mContext;

    public CstItemDecoration(Context context,int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (space<0){
//            space= (parent.getWidth() - view.getLayoutParams().width*spanCount)/(spanCount-1);

            space= (parent.getWidth() - mContext.getResources().getDimensionPixelOffset(R.dimen.dp_216)*spanCount)/(spanCount-1);

        }

        if (false) {
            outRect.left = space - column * space / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * space / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = space;
            }
            outRect.bottom = space; // item bottom
        } else {
            outRect.left = column * space / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = space - (column + 1) * space / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
//                outRect.top = space; // item top
                outRect.top = space+50; // item top
                outRect.top = 83;
            }
        }

        /*Log.i("NIODATA","space="+space);
        Log.i("NIODATA","OUTRECT.top="+outRect.top);
        Log.i("NIODATA","outRect.bottom="+outRect.bottom);
        Log.i("NIODATA","OUTRECT.left="+outRect.left);
        Log.i("NIODATA","OUTRECT.right="+outRect.right);*/
    }

}
