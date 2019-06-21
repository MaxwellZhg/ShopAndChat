package shopandclient.ssf.com.shopandclient.net.inter;

/**
 * Created by zhg on 2019/4/15.
 */
public interface BaseBiz {
    void onSuccess(Object object);
    void onError(Throwable e);
}
