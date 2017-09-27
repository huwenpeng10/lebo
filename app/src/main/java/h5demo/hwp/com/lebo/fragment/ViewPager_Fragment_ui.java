package h5demo.hwp.com.lebo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.adapter.GiftAdapter;
import h5demo.hwp.com.lebo.adapter.GiftAdapter2;
import h5demo.hwp.com.lebo.adapter.GiftAdapter3;
import h5demo.hwp.com.lebo.adapter.ListViewAdapter;
import h5demo.hwp.com.lebo.adapter.MyAdapter;
import h5demo.hwp.com.lebo.adapter.decoration.DividerItemDecoration;
import h5demo.hwp.com.lebo.like.HeartLayout;
import h5demo.hwp.com.lebo.util.PictureUrl;


/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class ViewPager_Fragment_ui extends Fragment implements View.OnClickListener{
    @BindView(R.id.viewpager_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.liaotian)
    TextView liaotian;
    @BindView(R.id.gift)
    TextView gift;
    @BindView(R.id.viewpager_ed)
    EditText viewpager_ed;
    @BindView(R.id.like)
    TextView like;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.viewpager_listview)
    ListView viewpager_listview;
    @BindView(R.id.heart_layout)
    HeartLayout liner;
    @BindView(R.id.sendgift)
    FrameLayout sendgift;
    @BindView(R.id.back)
    RelativeLayout relativeLayout;
    RecyclerView PWRecyclerView1;
    RecyclerView PWRecyclerView2;
    RecyclerView PWRecyclerView3;

    private ViewPager viewPager;

    PopupWindow pw = null;

    private List<String> datas = new ArrayList<>();
    private Timer mTimer = new Timer();

    final ArrayList<View> list = new ArrayList<>();

    public static ViewPager_Fragment_ui newInstance() {
        ViewPager_Fragment_ui fragment = new ViewPager_Fragment_ui();
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.viewpager_item_ui,container,false);
        ButterKnife.bind(this,view);
        View rvView3 = inflater.inflate(R.layout.viewpager_gift_view,null);
        View rvView1 = inflater.inflate(R.layout.viewpager_gift_view,null);
        View rvView2 = inflater.inflate(R.layout.viewpager_gift_view,null);

        PWRecyclerView1 = (RecyclerView) rvView1.findViewById(R.id.gift_rv);
        PWRecyclerView2 = (RecyclerView) rvView2.findViewById(R.id.gift_rv);
        PWRecyclerView3 = (RecyclerView) rvView3.findViewById(R.id.gift_rv);

        final View vs = inflater.inflate(R.layout.gift_viewpager, container,false);
        viewPager = (ViewPager) vs.findViewById(R.id.viewpager_gift);
        viewPager.getBackground().setAlpha(100);
        list.add(rvView1);
        list.add(rvView2);
        list.add(rvView3);

        initView();
        liaotian.setOnClickListener(this);
        send.setOnClickListener(this);
        like.setOnClickListener(this);
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGift(view,vs);
            }
        });
        addLike();
        initListener();

        vs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                pw.dismiss();
            }
        });
        ininRecyView();
        return view;
    }


    private void ininRecyView() {
        PictureUrl pictureUrl = new PictureUrl();
        PWRecyclerView1.setAdapter(new GiftAdapter(getContext(),pictureUrl.addData1()));
        PWRecyclerView1.setLayoutManager(new GridLayoutManager(getContext(), 5));
        PWRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        PWRecyclerView1.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));

        PWRecyclerView2.setAdapter(new GiftAdapter2(getContext(),pictureUrl.addData2()));
        PWRecyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 5));
        PWRecyclerView2.setItemAnimator(new DefaultItemAnimator());
        PWRecyclerView2.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));

        PWRecyclerView3.setAdapter(new GiftAdapter3(getContext(),pictureUrl.addData3()));
        PWRecyclerView3.setLayoutManager(new GridLayoutManager(getContext(), 5));
        PWRecyclerView3.setItemAnimator(new DefaultItemAnimator());
        PWRecyclerView3.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    private void initListener() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendgift.setVisibility(View.GONE);
            }
        });
    }

    //自动的弹出心 喜欢
    private void addLike() {
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                liner.post(new Runnable() {
                    @Override
                    public void run() {
                        liner.addHeart(randomColor());
                    }
                });
            }
        }, 1000, 1000);
    }
    /**
     * 获取随机颜色
     * @return
     */
    private int randomColor() {
        return Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

    private void initView() {
        mRecyclerView.setAdapter(new MyAdapter(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.liaotian:
                viewpager_ed.setVisibility(View.VISIBLE);
                send.setVisibility(View.VISIBLE);
                break;
            case R.id.send:
                sendMessage();
                break;
            case R.id.like:
                liner.addHeart(randomColor());
                break;
        }
    }

    private void sendGift(View view,View vs) {
        sendgift.setVisibility(View.VISIBLE);

        initwindow(view,vs);
        initGiftView();
    }

    private void initwindow(View view,View vs) {
        pw = new PopupWindow(vs, ActionBar.LayoutParams.MATCH_PARENT,500);
        pw.setFocusable(true);
        pw.showAtLocation(view.findViewById(R.id.back), Gravity.BOTTOM, 0, 0);
    }

    private void initGiftView() {
        PagerAdapter pagerAdapter =  new PagerAdapter () {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(View view, int position, Object object)                       //销毁Item
            {
                ((ViewPager) view).removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(View view, int position)                                //实例化Item
            {
                ((ViewPager) view).addView(list.get(position), 0);

                return list.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
    }

    //发送消息
    private void sendMessage() {
        String str = viewpager_ed.getText()+"";
        datas.add(str);
        ListViewAdapter adapter = new ListViewAdapter(getContext(),datas);
        adapter.notifyDataSetChanged();
        viewpager_listview.setAdapter(adapter);
        viewpager_ed.setText("");
        viewpager_listview.setSelection(datas.size()-1);
        viewpager_ed.setVisibility(View.GONE);
        send.setVisibility(View.GONE);
    }
}