package h5demo.hwp.com.lebo.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class BaseBean implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
