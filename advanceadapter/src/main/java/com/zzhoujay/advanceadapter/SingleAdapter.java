package com.zzhoujay.advanceadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhou on 16-3-24.
 * 只含有单个Header和Footer的Adapter
 */
public class SingleAdapter extends AdvanceAdapter {

    private View headerView, footerView;

    /**
     * 适用于LinearLayoutManager
     * @param childAdapter 被包裹的adapter
     */
    public SingleAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter) {
        super(childAdapter);
    }

    /**
     * 适用于GridLayoutManager和StaggeredGridLayoutManager
     * @param childAdapter 被包裹的adapter
     * @param layoutManager recyclerView的layoutManager
     */
    public SingleAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter, RecyclerView.LayoutManager layoutManager) {
        super(childAdapter, layoutManager);
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        if (this.headerView != null) {
            this.headerView = headerView;
            if (headerView == null) {
                notifyItemRemoved(0);
            } else {
                notifyItemChanged(0);
            }
        } else {
            this.headerView = headerView;
            notifyItemInserted(0);
        }
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        if (this.footerView != null) {
            this.footerView = footerView;
            if (footerView == null) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemChanged(getItemCount() - 1);
            }
        } else {
            this.footerView = footerView;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    @Override
    public int headerCount() {
        return headerView == null ? 0 : 1;
    }

    @Override
    public int footerCount() {
        return footerView == null ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onHeaderCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(headerView);
    }

    @Override
    public RecyclerView.ViewHolder onFooterCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(footerView);
    }

    @Override
    public void onHeaderBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onFooterBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
