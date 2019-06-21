package shopandclient.ssf.com.shopandclient.entity;

import java.io.Serializable;

/**
 * Created by zhg on 2019/6/17.
 */
public class ImageItem implements Serializable {

    public String name;       //图片的名字
    public String path;       //图片的路径
    public long size;         //图片的大小
    public int width;         //图片的宽度
    public int height;        //图片的高度
    public String mimeType;   //图片的类型
    public long addTime;      //图片的创建时间

    /** 图片的路径和创建时间相同就认为是同一张图片 */
    @Override
    public boolean equals(Object o) {
        if (o instanceof com.lzy.imagepicker.bean.ImageItem) {
            com.lzy.imagepicker.bean.ImageItem item = (com.lzy.imagepicker.bean.ImageItem) o;

            return this.path.equals(item.path);
        }

        return super.equals(o);
    }
}
