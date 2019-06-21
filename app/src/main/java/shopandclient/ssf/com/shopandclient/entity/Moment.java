package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class Moment {
    private String name;
    private int resId;
    private String conent;
    private ArrayList<Comment> mComment;
    private ArrayList<Integer> listResId;

    public Moment(String name, int resId, String conent, ArrayList<Comment> mComment, ArrayList<Integer> listResId) {
        this.name = name;
        this.resId = resId;
        this.conent = conent;
        this.mComment = mComment;
        this.listResId = listResId;
    }

    public ArrayList<Integer> getListResId() {
        return listResId;
    }

    public void setListResId(ArrayList<Integer> listResId) {
        this.listResId = listResId;
    }

    public Moment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public ArrayList<Comment> getmComment() {
        return mComment;
    }

    public void setmComment(ArrayList<Comment> mComment) {
        this.mComment = mComment;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "name='" + name + '\'' +
                ", resId=" + resId +
                ", conent='" + conent + '\'' +
                ", mComment=" + mComment +
                '}';
    }
}
