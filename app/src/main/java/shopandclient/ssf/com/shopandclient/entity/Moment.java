package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class Moment {
    private String name;
    private int resId;
    private String conent;
    private String time;
    private ArrayList<Comment> mComment;
    private ArrayList<Integer> listResId;
    private int commentID;
    private int proId;

    public Moment(String name, int resId, String conent, String time, ArrayList<Comment> mComment, ArrayList<Integer> listResId, int commentID, int proId) {
        this.name = name;
        this.resId = resId;
        this.conent = conent;
        this.time = time;
        this.mComment = mComment;
        this.listResId = listResId;
        this.commentID = commentID;
        this.proId = proId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Comment> getmComment() {
        return mComment;
    }

    public void setmComment(ArrayList<Comment> mComment) {
        this.mComment = mComment;
    }

    public ArrayList<Integer> getListResId() {
        return listResId;
    }

    public void setListResId(ArrayList<Integer> listResId) {
        this.listResId = listResId;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "name='" + name + '\'' +
                ", resId=" + resId +
                ", conent='" + conent + '\'' +
                ", time='" + time + '\'' +
                ", mComment=" + mComment +
                ", listResId=" + listResId +
                ", commentID=" + commentID +
                ", proId=" + proId +
                '}';
    }
}
