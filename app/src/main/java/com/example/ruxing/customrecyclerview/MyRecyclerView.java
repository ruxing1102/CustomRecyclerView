package com.example.ruxing.customrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ruxing on 2018/9/14.
 */

public class MyRecyclerView extends RecyclerView {

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;
    private BaseAdapter mAdapter;


    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) mAdapter = new BaseAdapter(adapter);
        super.setAdapter(mAdapter);
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        if (null != mAdapter) mAdapter.notifyItemInserted(0);
    }

    public void setmFooterView(View footerView) {
        this.mFooterView = footerView;
        if (null != mAdapter) mAdapter.notifyItemInserted(mAdapter.getItemCount() - 1);
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        if (null != mAdapter) mAdapter.notifyDataSetChanged();
    }

    private class BaseAdapter extends RecyclerView.Adapter<ViewHolder> {

        private Adapter mDataAdapter;
        private final int ITEM_TYPE_HEADER = 0;
        private final int ITEM_TYPE_FOOTER = 1;
        private final int ITEM_TYPE_EMPTY = 2;
        private final int ITEM_TYPE_DATA = 3;


        public BaseAdapter(Adapter mDataAdapter) {
            this.mDataAdapter = mDataAdapter;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            switch (viewType) {
                case ITEM_TYPE_HEADER:
                    return new ViewHolder(mHeaderView) {
                    };
                case ITEM_TYPE_FOOTER:
                    return new ViewHolder(mFooterView) {
                    };
                case ITEM_TYPE_EMPTY:
                    return new ViewHolder(mEmptyView) {
                    };
                default:
                    return mDataAdapter.onCreateViewHolder(parent, viewType);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type == ITEM_TYPE_HEADER || type == ITEM_TYPE_FOOTER || type == ITEM_TYPE_EMPTY)
                return;
            mDataAdapter.onBindViewHolder(holder, getRealDataPosition(position));
        }

        @Override
        public int getItemCount() {
            int itemCount = mDataAdapter.getItemCount();
            if (null != mEmptyView && mDataAdapter.getItemCount() == 0) itemCount++;
            if (null != mHeaderView) itemCount++;
            if (null != mFooterView) itemCount++;
            return itemCount;
        }

        @Override
        public int getItemViewType(int position) {
            if (null != mHeaderView && position == 0)
                return ITEM_TYPE_HEADER;
            if (null != mFooterView && position == getItemCount() - 1)
                return ITEM_TYPE_FOOTER;
            if (null != mEmptyView && mDataAdapter.getItemCount() == 0)
                return ITEM_TYPE_EMPTY;
            return ITEM_TYPE_DATA;
        }

        private int getRealDataPosition(int position) {
            if (null != mHeaderView) position--;
            return position;
        }
    }

}
