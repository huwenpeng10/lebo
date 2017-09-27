package h5demo.hwp.com.lebo.util;

import android.content.Context;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h5demo.hwp.com.lebo.entity.PageResult;
import h5demo.hwp.com.lebo.http.OkHttpHelper;
import h5demo.hwp.com.lebo.http.SpotsCallBack;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class PageUtils {
    private static Builder builder;
    private OnPageListener onPageListener;

    private static final int STATE_NORMAL = 0;//正常状态
    private static final int STATE_REFRESH = 1;//刷新
    private static final int STATE_MORE = 2;//加载更多

    private int state = STATE_NORMAL;//默认状态是正常状态
    private OkHttpHelper okHttpHelper;
    public PageUtils() {
        initRefreshLayout();
        okHttpHelper = OkHttpHelper.getInstance();
    }

    public static Builder newBuilder() {
        builder = new Builder();
        return builder;
    }

    public void request(){
        requestData();
    }
    public void putParam(String key,Object value){
        builder.putParam(key, value);
    }
    private void initRefreshLayout() {
        builder.refreshLayout.setLoadMore(builder.canLoadMore);//开始加载
        builder.refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (builder.pageIndex <= builder.totalPage) {
                    loadMoreData();
                } else {
                    Toast.makeText(builder.context,
                            "已经没有更多数据", Toast.LENGTH_SHORT).show();
                    builder.refreshLayout.finishRefreshLoadMore();
                }
            }
        });
    }

    private void refreshData() {
        builder.pageIndex = 1;
        state = STATE_REFRESH;
        requestData();
    }

    private void loadMoreData() {
        builder.pageIndex += 1;
        state = STATE_MORE;
        requestData();
    }

    private void requestData() {
//        okHttpHelper.get(buildUrl(),
//                new RequestCallBack(builder.context));
        okHttpHelper.post(builder.url,buildParams(),new RequestCallBack(builder.context));

    }

    private String buildUrl() {
        return builder.url + "?" + buildUrlParams();
    }

    private String buildUrlParams() {
        HashMap<String, Object> map = builder.params;
        map.put("curPage", builder.pageIndex);
        map.put("pageSize", builder.pageSize);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    private HashMap<String,Object> buildParams() {
        HashMap<String, Object> map = builder.params;
        map.put("type", 0);
        map.put("page", 1);

        return map;
    }

    private <T> void showData(List<T> datas, int totalPage, int totalCount) {
        switch (state) {
            case STATE_NORMAL:
                if (this.onPageListener != null) {
                    this.onPageListener.load(datas, totalPage, totalCount);
                }
                break;
            case STATE_REFRESH:
                if (this.onPageListener != null) {
                    this.onPageListener.refresh(datas, totalPage, totalCount);
                }
                builder.refreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                if (this.onPageListener != null) {
                    this.onPageListener.loadMore(datas, totalPage, totalCount);
                }
                builder.refreshLayout.finishRefreshLoadMore();
                break;
        }
    }

    public interface OnPageListener<T> {
        void load(List<T> datas, int totalPage, int totalCount);

        void refresh(List<T> datas, int totalPage, int totalCount);

        void loadMore(List<T> datas, int totalPage, int totalCount);
    }

    public static class Builder {
        private Context context;
        private Type type;
        private String url;
        private MaterialRefreshLayout refreshLayout;
        private boolean canLoadMore;
        private OnPageListener onPageListener;

        private int totalPage = 1;
        private int pageIndex = 1;
        private int pageSize = 10;

        private HashMap<String, Object> params = new HashMap<>(5);

        public Builder setOnPageListener(OnPageListener onPageListener) {
            this.onPageListener = onPageListener;
            return builder;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return builder;
        }

        public Builder setRefreshLayout(MaterialRefreshLayout refreshLayout) {
            this.refreshLayout = refreshLayout;
            return builder;
        }

        public Builder setLoadMore(boolean loadMore) {
            this.canLoadMore = loadMore;
            return builder;
        }

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return builder;
        }

        public Builder putParam(String key, Object value) {
            params.put(key, value);
            return builder;
        }

        public PageUtils build(Context context, Type type) {
            this.type = type;
            this.context = context;
            validate();
            PageUtils pageUtils=new PageUtils();
            pageUtils.onPageListener=this.onPageListener;
            return pageUtils;
        }

        private void validate() {
            if (context == null) {
                throw new RuntimeException("Context can't be null ");
            }
            if (this.url == null || "".equals(this.url)) {
                throw new RuntimeException("URL can't be null ");
            }
            if (this.refreshLayout == null) {
                throw new RuntimeException("MaterialRefreshLayout can't be null ");
            }
        }
    }

    class RequestCallBack<T> extends SpotsCallBack<PageResult<T>> {

        public RequestCallBack(Context mContext) {
            super(mContext);
            super.mType = builder.type;
        }

        @Override
        public void onSuccess(Response response, PageResult<T> pageResult) {
            builder.pageIndex = pageResult.getCurrentPage();
            builder.totalPage = pageResult.getTotalPage();
            showData(pageResult.getList(), builder.totalPage,
                    pageResult.getTotalCount());
        }

        @Override
        public void onError(Response response, int code, Exception e) {

        }
    }
}
