package com.zzhoujay.advanceadaptersimple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zzhoujay on 2015/7/22 0022.
 */
public class NormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int size;

    private ArrayList<Integer> nums;

    public NormalAdapter(int size) {
        this.size = size;
        nums = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            nums.add(i);
        }
    }

    private Callback callback;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Holder holder = new Holder(view);
        holder.setCallback(new Callback() {
            @Override
            public void call(int index) {
                if (callback != null) {
                    callback.call(index);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
//            ViewGroup.LayoutParams layoutParams = ((Holder) holder).view.getLayoutParams();
//            layoutParams.height = (position % 5) * 30 + 200;
//            if(position>=getItemCount()){
//                System.out.println("gg");
//            }
            ((Holder) holder).view.setText("" + nums.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public TextView view;

        private Callback callback;

        public Holder(View itemView) {
            super(itemView);

            view = (TextView) itemView.findViewById(R.id.view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.call(getAdapterPosition());
                    }
                }
            });
        }

        public void setCallback(Callback callback) {
            this.callback = callback;
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setNum(int index) {
//        nums.set(index, nums.get(index) + 1);
        nums.remove(index);
        notifyItemRemoved(index);
    }

    public interface Callback {
        void call(int index);
    }
}
