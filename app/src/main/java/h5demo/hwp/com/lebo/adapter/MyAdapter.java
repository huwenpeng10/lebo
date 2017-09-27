package h5demo.hwp.com.lebo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import h5demo.hwp.com.lebo.R;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> datas = new ArrayList<>();

    public MyAdapter(Context context){
        this.context = context;
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
        datas.add(R.drawable.menu);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewpager_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.img.setImageResource(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.viewpager_rv_img);
        }
    }
}
