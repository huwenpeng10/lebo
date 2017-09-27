package h5demo.hwp.com.lebo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.util.SPUtils;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        Timer timer  = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String state = SPUtils.getState(WelcomeActivity.this);
                if("已登录".equals(state)){
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        timer.schedule(task,3000);
    }
}
