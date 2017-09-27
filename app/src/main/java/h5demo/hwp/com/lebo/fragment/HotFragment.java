package h5demo.hwp.com.lebo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.activity.VideoActivity;
import h5demo.hwp.com.lebo.adapter.FavoriteFragmentAdapter;
import h5demo.hwp.com.lebo.adapter.base.BaseAdapter;
import h5demo.hwp.com.lebo.adapter.decoration.DividerGridItemDecoration;
import h5demo.hwp.com.lebo.adapter.decoration.DividerItemDecoration;
import h5demo.hwp.com.lebo.entity.Liveing;
import h5demo.hwp.com.lebo.http.Contants;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xiaoyuan on 17/3/8.
 */

public class HotFragment extends BaseFragment {
    private static final int STATE_NORMAL = 0;//正常状态
    private static final int STATE_REFRESH = 1;//刷新
    private static final int STATE_MORE = 2;//加载更多
    private int state = STATE_NORMAL;//默认状态是正常状态

    private int currPage = 0;
    private int totalPage = 1;
    private int pageSize = 10;
    private String page = "0";
    @BindView(R.id.hot_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.mrefresh)
    SwipeRefreshLayout mRefreshLayout;

    private FavoriteFragmentAdapter mAdapter;

    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_hot, null);
        ButterKnife.bind(this, view);

        initData();
        initRefreshLayout();
        return view;
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("TAG","开始刷新");
                refreshData();
                mRefreshLayout.setRefreshing(false);
                Log.e("TAG","结束刷新");
            }
        });

//        boolean boo = isSlideToBottom(mRecyclerView);
//        Log.e("TAG","boo ==="+ boo);
//        if(!boo) {
//
//            mRecyclerView.addOnScrollListener(
//                    new EndlessRecyclerOnScrollListener(
//                            new LinearLayoutManager(getContext())) {
//                        @Override
//                        public void onLoadMore(int currentPage) {
//                            loadMoreData();
//                        }
//                    });
//        }else {
//            Toast.makeText(getContext(),
//                    "已经没有更多数据", Toast.LENGTH_SHORT).show();
//            Log.e("TAG","已经没有更多数据");
//        }
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    private void refreshData() {
        page = "1";
        state = STATE_REFRESH;
        requestDatas(page);
    }

    private void loadMoreData() {
        page += 1;
        state = STATE_MORE;
        requestDatas(page);
    }

    private void requestDatas(String page){
        OkHttpUtils
                .post()
                .url(Contants.API.FAV_URL)
                .addParams("type", "0")
                .addParams("page", "0")
                .build()
                .execute(new HotFragment.UserCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Liveing response, int id) {
                        Log.e("TAG","第二次加载数据");

                        showData(response.getResult().getList());
                    }
                });
    }

    private void showData(List<Liveing.ResultBean.ListBean> datas) {
        switch (state) {
            case STATE_NORMAL:
                if (mAdapter == null) {
                    mAdapter = new FavoriteFragmentAdapter(getContext(),datas);
                    mAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            //Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),VideoActivity.class);
                            startActivity(intent);
                        }
                    });

                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));
                } else {
                    mAdapter.clearData();
                    mAdapter.addData(datas);
                }
                break;
            case STATE_REFRESH:
                mAdapter.clearData();
                mAdapter.addData(datas);
                mRecyclerView.scrollToPosition(0);
                break;
            case STATE_MORE:
                mAdapter.addData(mAdapter.getItemCount(), datas);
                mRecyclerView.scrollToPosition(mAdapter.getItemCount());
                break;
        }
    }



    private void initData() {
        OkHttpUtils
                .post()
                .url(Contants.API.HOT_URL)
                .addParams("type", "1")
                .addParams("page", "1")
                .build()
                .execute(new UserCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Liveing response, int id) {
                        Log.e("TAG","第一次加载数据"+ response
                                .getResult()
                                .getList()
                                .get(0)
                                .getUser()
                                .getUser_data()
                                .getUser_name());
                        mAdapter = new FavoriteFragmentAdapter(getContext(),response.getResult().getList());
                        mAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getActivity(),VideoActivity.class);
                                startActivity(intent);
                            }
                        });
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                                DividerItemDecoration.VERTICAL_LIST));
                    }
                });
    }

    public abstract class UserCallBack extends Callback<Liveing> {

        @Override
        public Liveing parseNetworkResponse(Response response, int id) throws Exception {
            String result = response.body().string();
            Gson gson = new Gson();
            Liveing liveing = gson.fromJson(result, Liveing.class);
            return liveing;
        }
    }
}
