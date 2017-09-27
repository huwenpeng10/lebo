package h5demo.hwp.com.lebo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class ViewPagerFragment extends Fragment {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();

    private FragmentPagerAdapter mAdapter;

    public static ViewPagerFragment newInstance(){
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        return viewPagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_main,container,false);
        ButterKnife.bind(this,view);

        list.add(ViewPager_Fragment.newInstance());
        list.add(ViewPager_Fragment_ui.newInstance());
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return list.get(arg0);
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(1);
    }
}
