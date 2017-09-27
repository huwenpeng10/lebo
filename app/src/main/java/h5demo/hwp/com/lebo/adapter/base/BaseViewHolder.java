package h5demo.hwp.com.lebo.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.data;


/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> views;
    protected BaseAdapter.OnItemClickListener mOnItemClickListener;

    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.views = new SparseArray<>();
        this.mOnItemClickListener = listener;
    }

    private <V extends View> V retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
    }

    public View getView(int viewId) {
        return retrieveView(viewId);
    }

    public TextView getTextView(int viewId) {
        return retrieveView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return retrieveView(viewId);
    }

    public Button getButton(int viewId) {
        return retrieveView(viewId);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //条目的点击
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }
    }
}
