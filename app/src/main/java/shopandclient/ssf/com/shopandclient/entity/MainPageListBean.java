package shopandclient.ssf.com.shopandclient.entity;

import java.util.List;

/**
 * Created by zhg on 2019/4/15.
 */
public class MainPageListBean extends BaseBean {

    @Override
    public String toString() {
        return "MainPageListBean{" +
                "data=" + data +
                '}';
    }

    /**
     * data : {"list":[{"products":[{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":2100,"minMarketPrice":0,"minPrice":2100,"orderNum":0,"price":"¥2100.0~¥2100.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/a7e03b25936d95a16dc4aa3197181ae2.jpg","productId":1,"productName":"coach T恤","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":2100,"minMarketPrice":0,"minPrice":2100,"orderNum":1,"price":"¥2100.0~¥2100.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/867c88e160bca1aadd7604ea03d6ee0e.jpg","productId":2,"productName":"coach T恤","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":3000,"minMarketPrice":0,"minPrice":3000,"orderNum":2,"price":"¥3000.0~¥3000.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/3bc728f864dd1c4ad86aad9bc7a876ec.jpg","productId":4,"productName":"Burberry 轻盈丝质 呈现涂鸦格纹褶饰口袋结合宽角领 衬衫","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":3200,"minMarketPrice":0,"minPrice":3200,"orderNum":3,"price":"¥3200.0~¥3200.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/ba1806c5a5d86ce769420984079328a1.jpg","productId":5,"productName":"Burberry 彩色格纹棉质衬衫","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":5400,"minMarketPrice":0,"minPrice":5400,"orderNum":4,"price":"¥5400.0~¥5400.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/2148520fe922562ce759e00e7c89b9cd.jpg","productId":6,"productName":"Burberry 半身裙","tenantId":"161253X66lb6","topicId":5}],"topic":{"id":5,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/246afffe58ebf2297382adc996ff38fd.jpg","name":"紫薯精的孤独有谁能懂？","position":4,"type":1}},{"topic":{"id":2,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/2270b843449fbc876ca78260bf549d31.jpg","name":"Met Gala 2018时尚奥斯卡，是时候展现真正的技术了！","position":3,"type":1}}],"pageNum":1,"pageSize":10,"pages":1,"size":3}
     */


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "pageNum=" + pageNum +
                    ", pageSize=" + pageSize +
                    ", pages=" + pages +
                    ", size=" + size +
                    ", list=" + list +
                    '}';
        }

        /**
         * list : [{"products":[{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":2100,"minMarketPrice":0,"minPrice":2100,"orderNum":0,"price":"¥2100.0~¥2100.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/a7e03b25936d95a16dc4aa3197181ae2.jpg","productId":1,"productName":"coach T恤","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":2100,"minMarketPrice":0,"minPrice":2100,"orderNum":1,"price":"¥2100.0~¥2100.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/867c88e160bca1aadd7604ea03d6ee0e.jpg","productId":2,"productName":"coach T恤","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":3000,"minMarketPrice":0,"minPrice":3000,"orderNum":2,"price":"¥3000.0~¥3000.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/3bc728f864dd1c4ad86aad9bc7a876ec.jpg","productId":4,"productName":"Burberry 轻盈丝质 呈现涂鸦格纹褶饰口袋结合宽角领 衬衫","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":3200,"minMarketPrice":0,"minPrice":3200,"orderNum":3,"price":"¥3200.0~¥3200.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/ba1806c5a5d86ce769420984079328a1.jpg","productId":5,"productName":"Burberry 彩色格纹棉质衬衫","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":5400,"minMarketPrice":0,"minPrice":5400,"orderNum":4,"price":"¥5400.0~¥5400.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/2148520fe922562ce759e00e7c89b9cd.jpg","productId":6,"productName":"Burberry 半身裙","tenantId":"161253X66lb6","topicId":5}],"topic":{"id":5,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/246afffe58ebf2297382adc996ff38fd.jpg","name":"紫薯精的孤独有谁能懂？","position":4,"type":1}},{"topic":{"id":2,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/2270b843449fbc876ca78260bf549d31.jpg","name":"Met Gala 2018时尚奥斯卡，是时候展现真正的技术了！","position":3,"type":1}}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 3
         */


        private int pageNum;
        private int pageSize;
        private int pages;
        private int size;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * products : [{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":2100,"minMarketPrice":0,"minPrice":2100,"orderNum":0,"price":"¥2100.0~¥2100.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/a7e03b25936d95a16dc4aa3197181ae2.jpg","productId":1,"productName":"coach T恤","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":2100,"minMarketPrice":0,"minPrice":2100,"orderNum":1,"price":"¥2100.0~¥2100.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/867c88e160bca1aadd7604ea03d6ee0e.jpg","productId":2,"productName":"coach T恤","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":3000,"minMarketPrice":0,"minPrice":3000,"orderNum":2,"price":"¥3000.0~¥3000.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/3bc728f864dd1c4ad86aad9bc7a876ec.jpg","productId":4,"productName":"Burberry 轻盈丝质 呈现涂鸦格纹褶饰口袋结合宽角领 衬衫","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":3200,"minMarketPrice":0,"minPrice":3200,"orderNum":3,"price":"¥3200.0~¥3200.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/ba1806c5a5d86ce769420984079328a1.jpg","productId":5,"productName":"Burberry 彩色格纹棉质衬衫","tenantId":"161253X66lb6","topicId":5},{"currencySymbol":"¥","itemId":5,"maxMarketPrice":0,"maxPrice":5400,"minMarketPrice":0,"minPrice":5400,"orderNum":4,"price":"¥5400.0~¥5400.0","primaryPicUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/2148520fe922562ce759e00e7c89b9cd.jpg","productId":6,"productName":"Burberry 半身裙","tenantId":"161253X66lb6","topicId":5}]
             * topic : {"id":5,"imageUrl":"http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/246afffe58ebf2297382adc996ff38fd.jpg","name":"紫薯精的孤独有谁能懂？","position":4,"type":1}
             */

            private TopicBean topic;
            private String name;
            private String title;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private List<ProductsBean> products;

            public TopicBean getTopic() {
                return topic;
            }

            public void setTopic(TopicBean topic) {
                this.topic = topic;
            }

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class TopicBean {
                @Override
                public String toString() {
                    return "TopicBean{" +
                            "id=" + id +
                            ", imageUrl='" + imageUrl + '\'' +
                            ", name='" + name + '\'' +
                            ", title='" + title + '\'' +
                            ", position=" + position +
                            ", type=" + type +
                            '}';
                }

                /**
                 * id : 5
                 * imageUrl : http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/topic/246afffe58ebf2297382adc996ff38fd.jpg
                 * name : 紫薯精的孤独有谁能懂？
                 * position : 4
                 * type : 1
                 */


                private int id;
                private String imageUrl;
                private String name;
                private String title;

                private int position;

                private int type;
                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

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

            public static class ProductsBean {
                /**
                 * currencySymbol : ¥
                 * itemId : 5
                 * maxMarketPrice : 0.0
                 * maxPrice : 2100.0
                 * minMarketPrice : 0.0
                 * minPrice : 2100.0
                 * orderNum : 0
                 * price : ¥2100.0~¥2100.0
                 * primaryPicUrl : http://allsale-dev-1256326697.cos.ap-guangzhou.myqcloud.com/app-goods/a7e03b25936d95a16dc4aa3197181ae2.jpg
                 * productId : 1
                 * productName : coach T恤
                 * tenantId : 161253X66lb6
                 * topicId : 5
                 */


                private String currencySymbol;
                private int itemId;
                private String maxMarketPrice;
                private String maxPrice;
                private String minMarketPrice;
                private String minPrice;
                private int orderNum;
                private String price;
                private String primaryPicUrl;
                private int productId;
                private String productName;
                private String tenantId;
                private int topicId;

                public String getCurrencySymbol() {
                    return currencySymbol;
                }

                public void setCurrencySymbol(String currencySymbol) {
                    this.currencySymbol = currencySymbol;
                }

                public int getItemId() {
                    return itemId;
                }

                public void setItemId(int itemId) {
                    this.itemId = itemId;
                }

                public String getMaxMarketPrice() {
                    return maxMarketPrice;
                }

                public void setMaxMarketPrice(String maxMarketPrice) {
                    this.maxMarketPrice = maxMarketPrice;
                }

                public String getMaxPrice() {
                    return maxPrice;
                }

                public void setMaxPrice(String maxPrice) {
                    this.maxPrice = maxPrice;
                }

                public String getMinMarketPrice() {
                    return minMarketPrice;
                }

                public void setMinMarketPrice(String minMarketPrice) {
                    this.minMarketPrice = minMarketPrice;
                }

                public String getMinPrice() {
                    return minPrice;
                }

                public void setMinPrice(String minPrice) {
                    this.minPrice = minPrice;
                }

                public int getOrderNum() {
                    return orderNum;
                }

                public void setOrderNum(int orderNum) {
                    this.orderNum = orderNum;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPrimaryPicUrl() {
                    return primaryPicUrl;
                }

                public void setPrimaryPicUrl(String primaryPicUrl) {
                    this.primaryPicUrl = primaryPicUrl;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getTenantId() {
                    return tenantId;
                }

                public void setTenantId(String tenantId) {
                    this.tenantId = tenantId;
                }

                public int getTopicId() {
                    return topicId;
                }

                public void setTopicId(int topicId) {
                    this.topicId = topicId;
                }

                }
        }
    }
}
