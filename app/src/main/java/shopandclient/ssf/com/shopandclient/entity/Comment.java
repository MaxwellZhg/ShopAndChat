package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/14.
 */
public class Comment {
    public String mContent; // 评论内容
    public CommentUser mCommentator; // 评论者
    public CommentUser mReceiver; // 接收者（即回复谁）

    public Comment(CommentUser mCommentator, String mContent, CommentUser mReceiver) {
        this.mCommentator = mCommentator;
        this.mContent = mContent;
        this.mReceiver = mReceiver;
    }
}
