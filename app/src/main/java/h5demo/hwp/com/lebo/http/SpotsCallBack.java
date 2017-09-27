package h5demo.hwp.com.lebo.http;

import android.content.Context;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public abstract class SpotsCallBack<T> extends BaseCallBack<T> {
    private Context mContext;
    private SpotsDialog mDialog;

    public SpotsCallBack(Context mContext) {
        this.mContext = mContext;
        mDialog = new SpotsDialog(mContext, "拼命加载中...");
    }

    //显示对话框
    public void showDialog() {
        mDialog.show();
    }

    //关闭对话框
    public void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public void setMessage(String message) {
        mDialog.setMessage(message);
    }
    @Override
    public void onRequestBefore(Request request) {
        showDialog();
    }

    @Override
    public void onFailure(Request request, IOException e) {
        //失败的时候关闭对话框
        dismissDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
