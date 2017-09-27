package h5demo.hwp.com.lebo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import h5demo.hwp.com.lebo.R;
import h5demo.hwp.com.lebo.entity.Register;
import h5demo.hwp.com.lebo.http.Contants;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class RegisterActivity extends Activity {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.regi)
    TextView register;
    Register entity = new Register();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity_main);
        ButterKnife.bind(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","注册按钮点击了");
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"手机号不能为空",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }else if(phone.length() != 11 && pwd.length() != 0){
                    Toast.makeText(RegisterActivity.this,"手机号输入有误，请重新输入",Toast.LENGTH_LONG).show();
                }else if(phone.length() == 11 && pwd.length() != 0){
                    register();
                }

            }
        });
    }

    private void register() {
        OkHttpUtils
                .post()
                .url(Contants.API.REGISTER_URL)
                .addParams("phone",phone.getText().toString())
                .addParams("user_name","注册名字")
                .addParams("avatar", String.valueOf(R.drawable.menu))
                .addParams("sign","什米都没有啊")
                .addParams("password",pwd.getText().toString())
                .build()
                .execute(new UserCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Register response, int id) {
                        if(response != null && response.isResult()){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
    }

    public abstract class UserCallBack extends Callback<Register> {

        @Override
        public Register parseNetworkResponse(Response response, int id) throws Exception {
            String result = response.body().string();
            Gson gson = new Gson();
            Register liveing = gson.fromJson(result, Register.class);
            return liveing;
        }
    }
}
