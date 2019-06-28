package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/28.
 */
public class CommentParams {

    /**
     * proID : 1
     * comments : 评论中的评论
     * commentID : 1
     */

    private int proID;
    private String comments;
    private int commentID;

    public CommentParams(int proID, String comments, int commentID) {
        this.proID = proID;
        this.comments = comments;
        this.commentID = commentID;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}
