package h5demo.hwp.com.lebo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.fragment.ViewPagerFragment;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class VideoActivity extends AppCompatActivity {


    // 播放器的对象
    private KSYMediaPlayer ksyMediaPlayer;
    // 播放SDK提供的监听器
    // 播放器在准备完成，可以开播时会发出onPrepared回调
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    // 播放完成时会发出onCompletion回调
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    // 播放器遇到错误时会发出onError回调
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangeListener;
    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener;
    private SurfaceHolder mSurfaceHolder;

    String mVideoUrl = ""; // mVideoUrl为视频的播放地址
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoactivity_main);
        ButterKnife.bind(this);
        mVideoUrl = getIntent().getStringExtra("url");
        Log.e("url",mVideoUrl);
        initView();
        onActivityCreated();
    }

    private void initView() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.video_fl, new ViewPagerFragment());
        ft.commit();

    }

    public void onActivityCreated() {

        mSurfaceHolder = surfaceView.getHolder();
        SurfaceHolder.Callback mSurfaceCallback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ksyMediaPlayer != null) {
                    ksyMediaPlayer.setDisplay(holder);
                    ksyMediaPlayer.setScreenOnWhilePlaying(true);
                    // 设置视频伸缩模式，此模式为裁剪模式
                    ksyMediaPlayer.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                    // 开始播放视频
                    ksyMediaPlayer.start();
                }

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // 此处非常重要，必须调用!!!
                if (ksyMediaPlayer != null) {
                    ksyMediaPlayer.setDisplay(null);
                }
            }
        };

        mSurfaceHolder.addCallback(mSurfaceCallback);
        ksyMediaPlayer   = new KSYMediaPlayer.Builder(VideoActivity.this).build();
        mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mp) {
                // 播放完成，用户可选择释放播放器
                if(ksyMediaPlayer != null) {
                    ksyMediaPlayer.stop();
                    ksyMediaPlayer.release();
                }
            }
        };

        try {
            ksyMediaPlayer.setDataSource(mVideoUrl);
            ksyMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ksyMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        ksyMediaPlayer.setOnPreparedListener(mOnPreparedListener);
        ksyMediaPlayer.setOnInfoListener(mOnInfoListener);
        ksyMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
        ksyMediaPlayer.setOnErrorListener(mOnErrorListener);
        ksyMediaPlayer.setOnSeekCompleteListener(mOnSeekCompletedListener);
    }
//    @Override
//    public void onPause() {
//        if(ksyMediaPlayer != null)
//            ksyMediaPlayer.pause();
//    }


    @Override
    public void onResume() {
        super.onResume();
        if(ksyMediaPlayer != null)
            ksyMediaPlayer.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(ksyMediaPlayer != null)
            ksyMediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(ksyMediaPlayer != null)
            ksyMediaPlayer.release();
    }

}
