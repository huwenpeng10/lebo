package h5demo.hwp.com.lebo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class MD5Utils {
    public static String ecoder(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bytes = digest.digest(password.getBytes());

            StringBuffer buffer = new StringBuffer();
            for (byte b : bytes) {
                int number = b & 0xfff;//加盐
                String nuberStr = Integer.toHexString(number);
                if (nuberStr.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(nuberStr);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
