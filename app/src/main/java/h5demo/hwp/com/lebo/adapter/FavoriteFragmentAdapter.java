package h5demo.hwp.com.lebo.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.adapter.base.BaseViewHolder;
import h5demo.hwp.com.lebo.adapter.base.SimpleAdapter;
import h5demo.hwp.com.lebo.entity.Liveing;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class FavoriteFragmentAdapter extends SimpleAdapter<Liveing.ResultBean.ListBean> {
    public FavoriteFragmentAdapter(Context context, List<Liveing.ResultBean.ListBean> datas) {
        super(context, R.layout.item_live_fav, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, Liveing.ResultBean.ListBean item) {
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.live_header_img_sdv);
//        draweeView.setImageURI(Uri.parse(item.getImgUrl()));
//        holder.getTextView(R.id.catogory_wares_title_tv).setText(item.getName());
//        holder.getTextView(R.id.category_wares_price_tv).setText(item.getPrice() + "");

        holder.getTextView(R.id.live_location_tv).setText("布局");
        holder.getTextView(R.id.live_name_tv).setText(item.getUser().getUser_data().getUser_name());
        //Log.e("getStatus",item.getData().getStatus()+"       ");
        if(item.getData().getStatus() == 0){
            holder.getTextView(R.id.status).setText("在线");
        }else{
            holder.getTextView(R.id.status).setText("离线");
        }
        draweeView.setImageURI(Uri.parse(item.getUser().getUser_data().getAvatar()));
        SimpleDraweeView draweeViews = (SimpleDraweeView) holder.getView(R.id.face);
        draweeViews.setImageURI(Uri.parse(item.getData().getPic()));
    }
}
