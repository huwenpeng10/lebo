package h5demo.hwp.com.lebo.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.adapter.base.BaseViewHolder;
import h5demo.hwp.com.lebo.adapter.base.SimpleAdapter;
import h5demo.hwp.com.lebo.entity.MineLive;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class LivingActivityAdapter extends SimpleAdapter<MineLive.ResultBean.ListBean> {

    public LivingActivityAdapter(Context context, List<MineLive.ResultBean.ListBean> datas) {
        super(context, R.layout.livingactivity_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, MineLive.ResultBean.ListBean item) {
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.living_header_img_sdv);
//        draweeView.setImageURI(Uri.parse(item.getImgUrl()));
//        holder.getTextView(R.id.catogory_wares_title_tv).setText(item.getName());
//        holder.getTextView(R.id.category_wares_price_tv).setText(item.getPrice() + "");

        holder.getTextView(R.id.living_location_tv).setText("布局");
        holder.getTextView(R.id.living_name_tv).setText(item.getUser().getUser_data().getUser_name());
        //Log.e("getStatus",item.getData().getStatus()+"       ");
        if(item.getData().getStatus() == 0){
            holder.getTextView(R.id.living_status).setText("在线");
        }else{
            holder.getTextView(R.id.living_status).setText("离线");
        }
        draweeView.setImageURI(Uri.parse(item.getUser().getUser_data().getAvatar()));
        SimpleDraweeView draweeViews = (SimpleDraweeView) holder.getView(R.id.living_face);
        draweeViews.setImageURI(Uri.parse(item.getData().getPic()));
    }
}
