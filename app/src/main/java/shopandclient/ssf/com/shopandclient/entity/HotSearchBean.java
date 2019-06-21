package shopandclient.ssf.com.shopandclient.entity;

import java.util.List;

/**
 * Created by zhg on 2019/4/15.
 */
public class HotSearchBean extends BaseRespond {

    /**
     * serviceId : d44ba459-6704-4f17-a397-3624af461d69
     * data : ["母亲节","包","日式","新品上市","单鞋","夏凉被","墨镜","520元礼包抢先领","女款","秋","被","lv"]
     */

    private String serviceId;
    private List<String> data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HotSearchBean{" +
                "serviceId='" + serviceId + '\'' +
                ", data=" + data +
                '}';
    }
}
