package shopandclient.ssf.com.shopandclient.net;

/**
 * Created by zhg on 2019/4/15.
 */
public class ResultCodeException extends RuntimeException{
    //请求参数错误
    public static final int PARAMETER_ERROR = 400;

    //服务器内部错误
    public static final int SERVER_ERROR = 500;

    //session过期
    private static final int SESSION_ERROR = 20;

    public ResultCodeException(int resultCode) {
        this(getResultCodeException(resultCode));
    }

    public ResultCodeException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getResultCodeException(int code){
        String message = "";
        switch (code) {
            case PARAMETER_ERROR:
                message = "请求参数错误";
                break;
            case SERVER_ERROR:
                message = "服务器内部错误";
                break;
            case SESSION_ERROR:
                message = "请重新登录";
                break;
            default:
                message = "网络错误，请重试";

        }
        return message;
    }
}
