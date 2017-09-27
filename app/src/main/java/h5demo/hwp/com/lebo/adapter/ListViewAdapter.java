package h5demo.hwp.com.lebo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import h5demo.hwp.com.lebo.R;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class ListViewAdapter extends BaseAdapter{
    private Context context;
    private List<String> datas = new ArrayList<>();
    public ListViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder ;
        if(convertView == null){
            holder=new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.viewpager_listview_item,null);
            holder.lv_tv= (TextView) convertView.findViewById(R.id.listview_item_tv);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.lv_tv.setText(datas.get(position));
        return convertView;
    }

    class Holder{
        TextView lv_tv;
    }
}
