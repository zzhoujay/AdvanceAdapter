package com.zzhoujay.advanceadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhou on 16-3-24.
 * 只含有单个Header和Footer的Adapter
 */
public class SingleAdapter extends AdvanceAdapter {

    private View header, footer;

    public SingleAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter) {
        super(childAdapter);
    }

    public SingleAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter, RecyclerView.LayoutManager layoutManager) {
        super(childAdapter, layoutManager);
    }

    public View getHeader() {
        return header;
    }

    public void setHeader(View header) {
        this.header = header;
        notifyItemChanged(0);
    }

    public View getFooter() {
        return footer;
    }

    public void setFooter(View footer) {
        this.footer = footer;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public int headerCount() {
        return 1;
    }

    @Override
    public int footerCount() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onHeaderCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(header);
    }

    @Override
    public RecyclerView.ViewHolder onFooterCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(footer);
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
