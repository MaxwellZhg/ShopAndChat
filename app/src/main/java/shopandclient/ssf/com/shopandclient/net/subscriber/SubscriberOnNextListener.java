package shopandclient.ssf.com.shopandclient.net.subscriber;

/**
 * Created by zhg on 2019/4/15.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable e);
}
