package h5demo.hwp.com.lebo.zhibo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ksyun.media.streamer.encoder.VideoEncodeFormat;
import com.ksyun.media.streamer.framework.AVConst;
import com.ksyun.media.streamer.kit.StreamerConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;

public class DemoActivity extends Activity
        implements OnClickListener, RadioGroup.OnCheckedChangeListener{
    private static final String TAG = DemoActivity.class.getSimpleName();
    private Button mConnectButton;
    private Button demo_update;

    @BindView(R.id.rtmpTxt)
    TextView rtmpTxt;
    @BindView(R.id.rtmpUrl)
    EditText rtmpUrl;

    @BindView(R.id.frameRatePicker)
    EditText frameRatePicker;
    @BindView(R.id.videoBitratePicker)
    EditText videoBitratePicker;
    @BindView(R.id.audioBitratePicker)
    EditText audioBitratePicker;
    @BindView(R.id.radiobutton1)
    RadioButton radiobutton1;
    @BindView(R.id.radiobutton2)
    RadioButton radiobutton2;
    @BindView(R.id.radiobutton3)
    RadioButton radiobutton3;
    @BindView(R.id.radiobutton4)
    RadioButton radiobutton4;

    @BindView(R.id.orientationbutton1)
    RadioButton orientationbutton1;
    @BindView(R.id.orientationbutton2)
    RadioButton orientationbutton2;

    @BindView(R.id.encode_group)
    RadioGroup encode_group;
    @BindView(R.id.encode_sw)
    RadioButton encode_sw;
    @BindView(R.id.encode_hw)
    RadioButton encode_hw;
    @BindView(R.id.encode_sw1)
    RadioButton encode_sw1;

    @BindView(R.id.encode_type)
    RadioGroup encode_type;
    @BindView(R.id.encode_h264)
    RadioButton encode_h264;
    @BindView(R.id.encode_h265)
    RadioButton encode_h265;

    @BindView(R.id.encode_scene)
    RadioGroup encode_scene;
    @BindView(R.id.encode_scene_default)
    RadioButton encode_scene_default;
    @BindView(R.id.encode_scene_show_self)
    RadioButton encode_scene_show_self;
    @BindView(R.id.encode_profile)
    RadioGroup encode_profile;
    @BindView(R.id.encode_profile_low_power)
    RadioButton encode_profile_low_power;
    @BindView(R.id.encode_profile_balance)
    RadioButton encode_profile_balance;
    @BindView(R.id.encode_profile_high_perf)
    RadioButton encode_profile_high_perf;

    @BindView(R.id.autoStart)
    CheckBox autoStart;
    @BindView(R.id.print_debug_info)
    CheckBox print_debug_info;
    //
    @BindView(R.id.audioBitrate)
    LinearLayout audioBitrate;
    @BindView(R.id.videoBitrate)
    LinearLayout videoBitrate;
    @BindView(R.id.frameRate)
    LinearLayout frameRates;
    @BindView(R.id.init_group)
    LinearLayout init_group;
    @BindView(R.id.orientation_group)
    RadioGroup orientation_group;
    @BindView(R.id.resolution_group)
    RadioGroup resolution_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        ButterKnife.bind(this);
        mConnectButton = (Button) findViewById(R.id.connectBT);
        demo_update = (Button) findViewById(R.id.demo_update);
        demo_update.setOnClickListener(this);
        mConnectButton.setOnClickListener(this);

        rtmpUrl = (EditText) findViewById(R.id.rtmpUrl);

//        String id = SPUtils.getId(this);
//        Log.e("ID","id===="+ id);
//        rtmpUrl.setText("rtmp://cncpublish.bingdou.tv/live/"+id);

        updateUI();
        encode_type.setOnCheckedChangeListener(this);
        encode_group.setOnCheckedChangeListener(this);
    }

    private void setEnableRadioGroup(RadioGroup radioGroup, boolean enable) {
        for (int i=0; i<radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(enable);
        }
    }

    private void updateUI() {
        if (encode_hw.isChecked() || encode_h265.isChecked()) {
            setEnableRadioGroup(encode_scene, false);
            setEnableRadioGroup(encode_profile, false);
        } else {
            setEnableRadioGroup(encode_scene, true);
            setEnableRadioGroup(encode_profile, true);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        updateUI();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connectBT:
                int frameRate = 0;
                int videoBitRate = 0;
                int audioBitRate = 0;
                int videoResolution;
                int encodeType;
                int encodeMethod;
                int encodeScene;
                int encodeProfile;
                int orientation;
                boolean startAuto;
                boolean showDebugInfo;
//                if (!TextUtils.isEmpty(rtmpUrl.getText())
//					&& rtmpUrl.getText().toString().startsWith("rtmp")) {
                    if (!TextUtils.isEmpty(frameRatePicker.getText().toString())) {
                        frameRate = Integer.parseInt(frameRatePicker.getText()
                                .toString());
                    }

                    if (!TextUtils.isEmpty(videoBitratePicker.getText().toString())) {
                        videoBitRate = Integer.parseInt(videoBitratePicker.getText()
                                .toString());
                    }

                    if (!TextUtils.isEmpty(audioBitratePicker.getText().toString())) {
                        audioBitRate = Integer.parseInt(audioBitratePicker.getText()
                                .toString());
                    }

                    if (radiobutton1.isChecked()) {
                        videoResolution = StreamerConstants.VIDEO_RESOLUTION_360P;
                    } else if (radiobutton2.isChecked()) {
                        videoResolution = StreamerConstants.VIDEO_RESOLUTION_480P;
                    } else if (radiobutton3.isChecked()) {
                        videoResolution = StreamerConstants.VIDEO_RESOLUTION_540P;
                    } else {
                        videoResolution = StreamerConstants.VIDEO_RESOLUTION_720P;
                    }

                    if (encode_h265.isChecked()) {
                        encodeType = AVConst.CODEC_ID_HEVC;
                    } else {
                        encodeType = AVConst.CODEC_ID_AVC;
                    }

                    if (encode_hw.isChecked()) {
                        encodeMethod = StreamerConstants.ENCODE_METHOD_HARDWARE;
                        encode_scene.setClickable(false);
                    } else if (encode_sw.isChecked()) {
                        encode_scene.setClickable(true);
                        encodeMethod = StreamerConstants.ENCODE_METHOD_SOFTWARE;
                    } else {
                        encode_scene.setClickable(true);
                        encodeMethod = StreamerConstants.ENCODE_METHOD_SOFTWARE_COMPAT;
                    }

                    //TODO
                    if (encode_scene_default.isChecked()) {
                        encodeScene = VideoEncodeFormat.ENCODE_SCENE_DEFAULT;
                    } else {
                        encodeScene = VideoEncodeFormat.ENCODE_SCENE_SHOWSELF;
                    }

                    if (encode_profile_low_power.isChecked()) {
                        encodeProfile = VideoEncodeFormat.ENCODE_PROFILE_LOW_POWER;
                    } else if (encode_profile_balance.isChecked()) {
                        encodeProfile = VideoEncodeFormat.ENCODE_PROFILE_BALANCE;
                    } else {
                        encodeProfile = VideoEncodeFormat.ENCODE_PROFILE_HIGH_PERFORMANCE;
                    }

                    if (orientationbutton1.isChecked()) {
                        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    } else if (orientationbutton2.isChecked()) {
                        orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    } else {
                        orientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR;
                    }

                    startAuto = autoStart.isChecked();
                    showDebugInfo = print_debug_info.isChecked();

//                    Log.e(TAG, "onClick: rtmpUrl.getText().toString()%%%%%"+rtmpUrl.getText().toString());
                    CameraActivity.startActivity(getApplicationContext(), 0,
                             frameRate, videoBitRate,
                            audioBitRate, videoResolution, orientation, encodeType,  encodeMethod,
                            encodeScene, encodeProfile, startAuto, showDebugInfo);
//                }
                finish();
                break;
            case R.id.demo_update:
                rtmpTxt.setVisibility(View.VISIBLE);
                rtmpUrl.setVisibility(View.VISIBLE);
                resolution_group.setVisibility(View.VISIBLE);
                orientation_group.setVisibility(View.VISIBLE);
                encode_type.setVisibility(View.VISIBLE);
                encode_group.setVisibility(View.VISIBLE);
                encode_scene.setVisibility(View.VISIBLE);
                encode_profile.setVisibility(View.VISIBLE);
                init_group.setVisibility(View.VISIBLE);
                frameRates.setVisibility(View.VISIBLE);
                videoBitrate.setVisibility(View.VISIBLE);
                audioBitrate.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
