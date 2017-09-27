package h5demo.hwp.com.lebo.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class Gift implements Serializable{
    public long id;
    public String name;
    public int  gift_price;
    public long created_at;
    public long updated_at;
    public int pic;
    public String gift_url;
}
