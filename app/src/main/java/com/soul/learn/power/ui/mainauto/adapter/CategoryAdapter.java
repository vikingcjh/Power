package com.soul.learn.power.ui.mainauto.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soul.learn.power.R;
import com.soul.learn.power.bean.AppDetailsModel;
import com.soul.learn.power.log.LogUtil;
import com.soul.learn.power.ui.mainauto.holder.CatgViewHolder;
import com.soul.learn.power.ui.mainauto.holder.FooterCatgViewHolder;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
	public static final String TAG = "CategoryAdapter";

    protected static final int REFRESH_HEADER = Integer.MIN_VALUE;
    protected static final int HEADER = Integer.MIN_VALUE + 1;
    protected static final int FOOTER = Integer.MAX_VALUE - 1;
    protected static final int LOAD_MORE_FOOTER = Integer.MAX_VALUE;

    protected static final int TYPE_ITEM = 1;

	private List<AppDetailsModel> mItems;

	private Context mContext;

    private boolean displayLoadingRow = false;

    public RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            CategoryAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            CategoryAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            CategoryAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            CategoryAdapter.this.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            CategoryAdapter.this.notifyDataSetChanged();
        }
    };

	public CategoryAdapter(Context context,List<AppDetailsModel> items){
		mContext = context;
		mItems = items;

//        registerAdapterDataObserver(mObserver);
	}

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    CategoryAdapter adapter = (CategoryAdapter) recyclerView.getAdapter();
                    if (isFullSpanType(adapter.getItemViewType(position))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;

                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        int type = getItemViewType(position);
        if (isFullSpanType(type)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                lp.setFullSpan(true);
            }
        }
    }

    private boolean isFullSpanType(int type) {
        return type == REFRESH_HEADER || type == HEADER || type == FOOTER || type == LOAD_MORE_FOOTER;
    }

    @Override
    public int getItemViewType(int position) {
        return isLoadingRow(position) ? FOOTER : TYPE_ITEM;
    }

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview_category, parent, false);
            return new FooterCatgViewHolder(footerView);
        } else {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new CatgViewHolder(normalView);
        }
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogUtil.i("onBindViewHolder");
		if(holder instanceof CatgViewHolder) {
			((CatgViewHolder) holder).setData(mItems.get(position), position);
		}
		if (holder instanceof FooterCatgViewHolder){
            //...
        }
	}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        LogUtil.i("in onBindViewHolder2");
        if (payloads.isEmpty()){
            LogUtil.i("payloads empty");
            onBindViewHolder(holder,position);
        }else {
            if(holder instanceof CatgViewHolder) {
                LogUtil.i("payloads not empty");
                ((CatgViewHolder) holder).setData(mItems.get(position), position);
            }
            if (holder instanceof FooterCatgViewHolder){
                //...
            }
        }
    }

    @Override
	public int getItemCount() {
        return displayLoadingRow ? mItems.size() + 1 : mItems.size();
	}

    public boolean isDisplayLoadingRow() {
        return displayLoadingRow;
    }

    public void displayLoadingRow(boolean displayLoadingRow) {
        if (this.displayLoadingRow != displayLoadingRow) {
            this.displayLoadingRow = displayLoadingRow;
//            notifyDataSetChanged();
        }
    }

    public boolean isLoadingRow(int position) {
        return displayLoadingRow && position == getLoadingRowPosition();
    }

    private int getLoadingRowPosition() {
        return displayLoadingRow ? getItemCount() - 1 : -1;
    }
}
