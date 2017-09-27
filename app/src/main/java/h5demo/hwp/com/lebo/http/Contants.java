package h5demo.hwp.com.lebo.http;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class Contants {
//    public static final String COMPAIGN_ID="campaign_id";
//    public static final String WARE="ware";
//    public static final String USER_JSON="user_json";
//    public static final String TOKEN="token";
//    public static final int REQUEST_CODE=0;

    public static class API {
        public static final String BASE_URL="http://192.168.155.101/RealTimePlay/";

        public static final String FAVORITEINFO=BASE_URL+"FavoritePlayInfoServlet";
        public static final String HOT=BASE_URL+"FavoritePlayInfoServlet";

        public static final String URL = "http://121.42.26.175:14444/live/";
        public static final String FAV_URL =URL+ "find.json";//精选
        public static final String HOT_URL = URL+"find.json";//热门
        public static final String LOGIN_URL = URL+"login.json";//登录
        public static final String REGISTER_URL = URL+"register.json";//注册
        public static final String STARTLIVE_URL = URL + "create.json";//开始直播
        public static final String STATELIVE_URL = URL + "status/update.json";//更改直播的状态
        public static final String LIVELIST_URL = URL + "my/list.json";//我的直播列表
        public static final String MINELIVE_URL = URL + "user/update.json";//修改个人资料
    }
}
