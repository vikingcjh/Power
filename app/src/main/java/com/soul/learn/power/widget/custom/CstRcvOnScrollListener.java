package com.soul.learn.power.widget.custom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by chenjianhua on 2017/6/16.
 */

public class CstRcvOnScrollListener extends RecyclerView.OnScrollListener {
    private Callbacks mCallbacks;

    public interface Callbacks {
        /** Called when next page of data needs to be loaded. */
        void onLoadMore();

        /**
         * Called to check if loading of the next page is currently in progress.
         * @return true if loading is currently in progress, false otherwise.
         */
        boolean isLoading();

        /**
         * Called to check if there is more data (more pages) to load. If there is no more pages to load
         * @return true if all pages has been loaded, false otherwise.
         */
        boolean hasLoadedAllItems();
    }

    public CstRcvOnScrollListener(Callbacks callbacks){
        this.mCallbacks = callbacks;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_IDLE){

            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItemPosition;
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                if (recyclerView.getLayoutManager().getChildCount() > 0) {
                    firstVisibleItemPosition = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPositions(null)[0];
                } else {
                    firstVisibleItemPosition = 0;
                }
            } else {
                throw new IllegalStateException("LayoutManager needs to subclass LinearLayoutManager or StaggeredGridLayoutManager");
            }

            // Check if end of the list is reached (counting threshold) or if there is no items at all
            if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + 10)
                    || totalItemCount == 0) {
                // Call load more only if loading is not currently in progress and if there is more items to load
                if (!mCallbacks.isLoading() && !mCallbacks.hasLoadedAllItems()) {
                    mCallbacks.onLoadMore();
                }
            }
        }
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

    }

}
