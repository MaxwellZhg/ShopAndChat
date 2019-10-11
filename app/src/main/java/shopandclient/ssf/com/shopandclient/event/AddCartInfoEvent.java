package shopandclient.ssf.com.shopandclient.event;

import shopandclient.ssf.com.shopandclient.entity.ShopCartBean;

import java.util.ArrayList;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/11
 * Desc:
 */
public class AddCartInfoEvent {
    private int type;
    private ShopCartBean.DataBean.ListProBean listbenas;

    public AddCartInfoEvent(int type, ShopCartBean.DataBean.ListProBean listbenas) {
        this.type = type;
        this.listbenas = listbenas;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ShopCartBean.DataBean.ListProBean getListbenas() {
        return listbenas;
    }

    public void setListbenas(ShopCartBean.DataBean.ListProBean listbenas) {
        this.listbenas = listbenas;
    }
}
