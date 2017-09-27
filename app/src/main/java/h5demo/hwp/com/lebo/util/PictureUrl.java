package h5demo.hwp.com.lebo.util;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import h5demo.hwp.com.lebo.R;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class PictureUrl extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static List<Integer> addData1() {
        List<Integer> data1 = new ArrayList<>();
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);
        data1.add(R.drawable.menu);

        return data1;
    }

    public static List<Integer> addData2() {
        List<Integer> data2 = new ArrayList<>();
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);
        data2.add(R.drawable.menu);

        return data2;
    }

    public static List<Integer> addData3() {
        List<Integer> data3 = new ArrayList<>();
        data3.add(R.drawable.menu);
        data3.add(R.drawable.menu);
        data3.add(R.drawable.menu);
        data3.add(R.drawable.menu);
        data3.add(R.drawable.menu);
        data3.add(R.drawable.menu);
        data3.add(R.drawable.menu);
        return data3;
    }
}
