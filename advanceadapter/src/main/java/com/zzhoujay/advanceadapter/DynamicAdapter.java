package com.zzhoujay.advanceadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhou on 16-3-24.
 * 动态添加Header和Footer的Adapter
 */
public class DynamicAdapter extends AdvanceAdapter {

    private ArrayList<View> headers, footers;

    /**
     * 适用于LinearLayoutManager
     * @param childAdapter 被包裹的adapter
     */
    public DynamicAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter) {
        super(childAdapter);
        init();
    }

    /**
     * 适用于GridLayoutManager和StaggeredGridLayoutManager
     * @param childAdapter 被包裹的adapter
     * @param layoutManager recyclerView的layoutManager
     */
    public DynamicAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter, RecyclerView.LayoutManager layoutManager) {
        super(childAdapter, layoutManager);
        init();
    }

    private void init() {
        headers = new ArrayList<>();
        footers = new ArrayList<>();
    }

    public void addHeaderView(View header) {
        headers.add(header);
        notifyItemInserted(headerCount() - 1);
    }

    public void addFooterView(View footer) {
        footers.add(footer);
        notifyItemInserted(getItemCount() - 1);
    }

    public boolean removeHeaderView(View header) {
        boolean flag = headers.remove(header);
        notifyItemRemoved(headerCount());
        return flag;
    }

    public boolean removeFooterView(View footer) {
        boolean flag = footers.remove(footer);
        notifyItemRemoved(getItemCount());
        return flag;
    }

    @Override
    public int headerCount() {
        return headers.size();
    }

    @Override
    public int footerCount() {
        return footers.size();
    }

    @Override
    public RecyclerView.ViewHolder onHeaderCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(headers.get(viewType));
    }

    @Override
    public RecyclerView.ViewHolder onFooterCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(footers.get(viewType));
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
