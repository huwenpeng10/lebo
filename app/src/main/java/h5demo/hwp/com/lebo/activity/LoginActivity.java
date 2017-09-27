package h5demo.hwp.com.lebo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.application.MPermission;
import h5demo.hwp.com.lebo.entity.Login;
import h5demo.hwp.com.lebo.http.Contants;
import h5demo.hwp.com.lebo.util.SPUtils;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginActivity extends Activity {
    @BindView(R.id.username_phone)
    EditText username_phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.gotoregister)
    TextView register;
    @BindView(R.id.login)
    Button login;
    Login entity = new Login();

    private final int BASIC_PERMISSION_REQUEST_CODE = 110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.loginactivity_main);
        ButterKnife.bind(this);
        requestBasicPermission();
    }
    @OnClick({R.id.login ,R.id.gotoregister})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.gotoregister:
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.login:
                Log.e("TAG","登录按钮点击了");
                verify();
                break;
        }
    }

    private void verify() {
        //TextUtils.isEmpty()
        if(TextUtils.isEmpty(username_phone.getText().toString())){
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }

            loginuser();
    }

    private void loginuser() {
        Log.e("TAG","loginuser");
        Log.e("TAG","username_phone"+username_phone.getText().toString());
        Log.e("TAG","password"+password.getText().toString());

        OkHttpUtils
                .post()
                .url(Contants.API.LOGIN_URL)
                .addParams("phone",username_phone.getText().toString())
                .addParams("password",password.getText().toString())
                .build()
                .execute(new UserCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Login response, int id) {
                        Log.e("TAG","response"+ response.getResult().getUser_data().getPhone());
                        Log.e("TAG","response"+ response.getResult().getUser_data().getUser_name());
                        Log.e("TAGID","response"+ response.getResult().getId());
                        if(response != null ){
                            SPUtils.getUser(LoginActivity.this,response.getResult().getUser_data().getPhone()
                                    ,response.getResult().getUser_data().getUser_name(),
                                    response.getResult().getId());
                            SPUtils.getBack(LoginActivity.this,"已登录");
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
    }

    public abstract class UserCallBack extends Callback<Login> {

        @Override
        public Login parseNetworkResponse(Response response, int id) throws Exception {
            String result = response.body().string();
            Gson gson = new Gson();
            Login liveing = gson.fromJson(result, Login.class);
            return liveing;
        }
    }

    /**
     * 请求权限
     * 因为是在6.0开发，这些权限需要手动去请求
     */
    private void requestBasicPermission() {
        MPermission.with(LoginActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                )
                .request();
    }
}
