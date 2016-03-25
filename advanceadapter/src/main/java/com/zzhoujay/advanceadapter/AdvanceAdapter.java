package com.zzhoujay.advanceadapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhou on 16-3-24.
 * 拥有Header和Footer的Adapter
 */
public abstract class AdvanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MAX_HEADER_SIZE = 100;
    public static final int MAX_FOOTER_SIZE = 100;
    public static final int TYPE_HEADER_START = Integer.MAX_VALUE - MAX_HEADER_SIZE;
    public static final int TYPE_FOOTER_START = Integer.MIN_VALUE;

    private RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean isStaggered;

    /**
     * 适用于LinearLayoutManager
     * @param childAdapter 被包裹的adapter
     */
    public AdvanceAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter) {
        this(childAdapter, null);
    }

    /**
     * 适用于GridLayoutManager和StaggeredGridLayoutManager
     * @param childAdapter 被包裹的adapter
     * @param layoutManager recyclerView的layoutManager
     */
    public AdvanceAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter, RecyclerView.LayoutManager layoutManager) {
        this.childAdapter = childAdapter;
        this.layoutManager = layoutManager;
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position < headerCount() || position >= headerCount() + childItemCount())
                        return ((GridLayoutManager) AdvanceAdapter.this.layoutManager).getSpanCount();
                    else
                        return 1;
                }
            });
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            isStaggered = true;
        }
    }

    /**
     * Header的数量
     *
     * @return header count
     */
    public abstract int headerCount();

    /**
     * Footer的数量
     *
     * @return footer count
     */
    public abstract int footerCount();

    /**
     * 创建Header的Holder
     *
     * @param parent   parent
     * @param viewType TYPE_HEADER_START~Integer.MAX_VALUE,代表Header顺序
     * @return holder
     */
    public abstract RecyclerView.ViewHolder onHeaderCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 创建Footer的Holder
     *
     * @param parent   parent
     * @param viewType TYPE_FOOTER_START~Integer.MIN_VALUE+MAX_FOOTER_SIZE,代表Footer的顺序
     * @return holder
     */
    public abstract RecyclerView.ViewHolder onFooterCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定Header
     *
     * @param holder   holder
     * @param position position 0~MAX_HEADER_SIZE
     */
    public abstract void onHeaderBindViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 绑定Footer
     *
     * @param holder   holder
     * @param position position 0~MAX_FOOTER_SIZE
     */
    public abstract void onFooterBindViewHolder(RecyclerView.ViewHolder holder, int position);

    protected int childItemCount() {
        return childAdapter == null ? 0 : childAdapter.getItemCount();
    }

    protected RecyclerView.Adapter<RecyclerView.ViewHolder> childAdapter() {
        return childAdapter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType >= TYPE_HEADER_START) {
            holder = onHeaderCreateViewHolder(parent, viewType - TYPE_HEADER_START);
        } else if (viewType <= TYPE_FOOTER_START + MAX_FOOTER_SIZE) {
            holder = onFooterCreateViewHolder(parent, viewType - TYPE_FOOTER_START);
        } else {
            holder = childAdapter.onCreateViewHolder(parent, viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int headerPosition = headerCount();
        if (position < headerPosition) {
            setStaggeredLayoutParams(holder);
            onHeaderBindViewHolder(holder, position);
        } else {
            int childPosition = headerPosition + childItemCount();
            if (position < childPosition) {
                childAdapter.onBindViewHolder(holder, position - headerPosition);
            } else {
                setStaggeredLayoutParams(holder);
                onFooterBindViewHolder(holder, position - childPosition);
            }
        }
    }

    @Override
    public int getItemCount() {
        return headerCount() + childItemCount() + footerCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (position >= headerCount() && position < headerCount() + childItemCount()) {
            childAdapter.onBindViewHolder(holder, position - headerCount(), payloads);
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int headerPosition = headerCount();
        if (position < headerPosition) {
            return TYPE_HEADER_START + position;
        } else {
            int childPosition = headerPosition + childItemCount();
            if (position < childPosition) {
                return childAdapter.getItemViewType(position - headerPosition);
            } else {
                return TYPE_FOOTER_START + (position - childPosition);
            }
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        childAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        if (position >= headerCount() && position < headerCount() + childItemCount()) {
            return childAdapter.getItemId(position - headerCount());
        }
        return super.getItemId(position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        childAdapter.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return childAdapter.onFailedToRecycleView(holder) && super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        childAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        childAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        childAdapter.registerAdapterDataObserver(childObserver);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        childAdapter.unregisterAdapterDataObserver(childObserver);
        super.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        childAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        childAdapter.onDetachedFromRecyclerView(recyclerView);
    }


    private void setStaggeredLayoutParams(RecyclerView.ViewHolder holder) {
        if (isStaggered) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams == null) {
                // 解决header或footer在没有layoutParam时显示不正确的情况
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutManager.generateDefaultLayoutParams();
                lp.setFullSpan(true);
                holder.itemView.setLayoutParams(lp);
                return;
            }
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    private RecyclerView.AdapterDataObserver childObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart + headerCount(), itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            notifyItemRangeChanged(positionStart + headerCount(), itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart + headerCount(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart + headerCount(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            notifyItemRangeChanged(fromPosition + headerCount(), toPosition + itemCount + headerCount());
        }
    };
}
