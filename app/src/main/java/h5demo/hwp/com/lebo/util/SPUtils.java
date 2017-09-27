package h5demo.hwp.com.lebo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class SPUtils {

    public static String getId(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String id = sp.getString("id", "id号码");
       return id;
    }

    public static void getUser(Context context, String phone, String user_name, long id) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("phone", phone);
        editor.putString("name", user_name);
        editor.putString("id", id+"");
        editor.commit();
    }

    public static void getBack(Context context, String str) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("state", str);
        editor.commit();
    }

    public static String getState(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String state = sp.getString("state", "是否登录");
        return state;
    }
}
