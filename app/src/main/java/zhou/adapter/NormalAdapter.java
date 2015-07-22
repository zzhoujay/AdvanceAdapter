package zhou.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zzhoujay on 2015/7/22 0022.
 */
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.Holder> {

    private List<String> msg;

    public NormalAdapter(List<String> msg) {
        this.msg = msg;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String m = msg.get(position);
        holder.icon.setImageResource(R.mipmap.ic_launcher);
        holder.text.setText(m);
    }

    @Override
    public int getItemCount() {
        return msg == null ? 0 : msg.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView icon;

        public Holder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_text);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);
        }
    }
}
