package h5demo.hwp.com.lebo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.activity.LivingActivity;
import h5demo.hwp.com.lebo.activity.LoginActivity;
import h5demo.hwp.com.lebo.util.SPUtils;


/**
 * Created by xiaoyuan on 17/3/8.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_fensi)
    TextView fensi;
    @BindView(R.id.mine_guanzhu)
    TextView guanzhu;
    @BindView(R.id.mine_zhibo)
    TextView zhibo;
    @BindView(R.id.mine_xiugai)
    TextView shezhi;
    @BindView(R.id.state)
    Button loginOrRegister;
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_mine,null);
        ButterKnife.bind(this,view);
        init();

        return  view;
    }

    private void init() {
        String state = SPUtils.getState(getActivity());
        if("已登录".equals(state)){
            loginOrRegister.setText("已登录");
        }else{
            loginOrRegister.setText("点击登录");
            loginOrRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MineFragment.this.getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick({R.id.mine_fensi,R.id.mine_guanzhu,R.id.mine_zhibo,R.id.mine_xiugai})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.mine_fensi:
                break;
            case R.id.mine_guanzhu:
                break;
            case R.id.mine_zhibo:
                Intent intent = new Intent(MineFragment.this.getActivity(), LivingActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_xiugai:

                break;
        }
    }
}
