package h5demo.hwp.com.lebo.entity;

/**
 * Created by Administrator on 2017/3/16 0016.
 * //开始直播
 */

public class LiveUser {


    /**
     * result : {"created_at":1489664071609,"updated_at":1489664071609,"id":1168071235535066,"data":{"status":1,"live_type":0,"pic":"直播封面","live_name":"直播名字"},"uid":1166463374262351}
     * error_code : 0
     */

    private ResultBean result;
    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * created_at : 1489664071609
         * updated_at : 1489664071609
         * id : 1168071235535066
         * data : {"status":1,"live_type":0,"pic":"直播封面","live_name":"直播名字"}
         * uid : 1166463374262351
         */

        private long created_at;
        private long updated_at;
        private long id;
        private DataBean data;
        private long uid;

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public static class DataBean {
            /**
             * status : 1
             * live_type : 0
             * pic : 直播封面
             * live_name : 直播名字
             */

            private int status;//状态
            private int live_type;//精选或热门
            private String pic;
            private String live_name;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getLive_type() {
                return live_type;
            }

            public void setLive_type(int live_type) {
                this.live_type = live_type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLive_name() {
                return live_name;
            }

            public void setLive_name(String live_name) {
                this.live_name = live_name;
            }
        }
    }
}
