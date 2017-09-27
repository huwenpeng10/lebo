package h5demo.hwp.com.lebo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.adapter.LivingActivityAdapter;
import h5demo.hwp.com.lebo.adapter.decoration.DividerItemDecoration;
import h5demo.hwp.com.lebo.entity.MineLive;
import h5demo.hwp.com.lebo.http.Contants;
import h5demo.hwp.com.lebo.util.SPUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class LivingActivity extends Activity {

    @BindView(R.id.living_rl)
    RecyclerView mRecyclerView;
    LivingActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livingactivity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String id = SPUtils.getId(this);
        OkHttpUtils
                .post()
                .url(Contants.API.LIVELIST_URL)
                .addParams("uid",id)
                .addParams("page","0")
                .build()
                .execute(new UserCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse( final MineLive response, int id) {

                        mAdapter = new LivingActivityAdapter(LivingActivity.this,response.getResult().getList());
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(LivingActivity.this));
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(LivingActivity.this,
                                DividerItemDecoration.VERTICAL_LIST));
                    }
                });
    }
    public abstract class UserCallBack extends Callback<MineLive>{

        @Override
        public MineLive parseNetworkResponse(Response response, int id) throws Exception {
            String result = response.body().string();
            Gson gson = new Gson();
            MineLive liveing = gson.fromJson(result, MineLive.class);
            return liveing;
        }
    }
}
