package shopandclient.ssf.com.shopandclient.entity;

import java.util.Comparator;

/**
 * Created by zhg on 2019/6/21.
 */
public class PinyinComparator implements Comparator<FriendListBean.DataBean.MyFriendBean> {

    public int compare(FriendListBean.DataBean.MyFriendBean o1, FriendListBean.DataBean.MyFriendBean o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}