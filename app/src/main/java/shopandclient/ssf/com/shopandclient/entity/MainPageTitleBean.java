package shopandclient.ssf.com.shopandclient.entity;

import java.util.List;

/**
 * Created by zhg on 2019/4/15.
 */
public class MainPageTitleBean extends BaseBean {
    /**
     * data : {"banners":[{"id":3,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/52124d60aa1cfb56d8a31395c79ffd71.jpg","name":"你的泳衣out了，穿这样在海边绝对是人群中的C位。","position":1,"type":1},{"id":1,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/f525eb06913a64e9c82cf78b77840981.jpg","name":"2018夏季时髦手册","position":1,"type":1}],"tops":[{"id":6,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/f0497701c357628e6967b8a1bf499de0.jpg","name":"时尚界大革新| 没错，今年是PVC元年！","position":2,"type":1}]}
     * meta : {"code":0,"message":null}
     */

    private DataBean data;




    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {

        private List<BannersBean> banners;
        private List<TopsBean> tops;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public List<TopsBean> getTops() {
            return tops;
        }

        public void setTops(List<TopsBean> tops) {
            this.tops = tops;
        }

        public static class BannersBean {
            /**
             * id : 3
             * imageUrl : http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/52124d60aa1cfb56d8a31395c79ffd71.jpg
             * name : 你的泳衣out了，穿这样在海边绝对是人群中的C位。
             * position : 1
             * type : 1
             */

            private int id;
            private String imageUrl;
            private String name;
            private int position;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class TopsBean {
            /**
             * id : 6
             * imageUrl : http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/f0497701c357628e6967b8a1bf499de0.jpg
             * name : 时尚界大革新| 没错，今年是PVC元年！
             * position : 2
             * type : 1
             */

            private int id;
            private String imageUrl;
            private String name;
            private int position;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
