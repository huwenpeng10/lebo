package h5demo.hwp.com.lebo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class SharedPreferenceUtils {
    private static final String PREFERENCE_NAME = "shop_common";

    public static boolean putString(Context context, String key, String value) {
        //私有模式，就是内部访问
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    public static String getString(Context context, String key, String defalutValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defalutValue);
    }
}
