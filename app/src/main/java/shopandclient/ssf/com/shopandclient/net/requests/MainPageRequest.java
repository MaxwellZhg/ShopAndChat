package shopandclient.ssf.com.shopandclient.net.requests;

import rx.Observable;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.SubscribeHandler;
import shopandclient.ssf.com.shopandclient.net.services.MainPageService;
import shopandclient.ssf.com.shopandclient.net.subscriber.ProgressSubscriber;


/**
 * Created by zhg on 2019/4/15.
 */
public class MainPageRequest extends SubscribeHandler {
    /**
     * 首页主题列表
     *
     * @param subscriber
     */
    public void mainPageList(ProgressSubscriber subscriber, String language, int type, int pageNum) {
        Observable observable = RetrofitHandle.getInstance().retrofit.create(MainPageService.class)
                .mainPageList(type, pageNum);
        toSubscribe(observable, subscriber);

    }
}
