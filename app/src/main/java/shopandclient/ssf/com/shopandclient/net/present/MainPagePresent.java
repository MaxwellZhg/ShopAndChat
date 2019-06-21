package shopandclient.ssf.com.shopandclient.net.present;

import android.content.Context;
import shopandclient.ssf.com.shopandclient.entity.MainPageListBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.requests.MainPageRequest;
import shopandclient.ssf.com.shopandclient.net.subscriber.ProgressSubscriber;
import shopandclient.ssf.com.shopandclient.net.subscriber.SubscriberOnNextListener;


/**
 * Created by zhg on 2019/4/15.
 */
public class MainPagePresent {
    private final Context mContext;
    private final BaseBiz baseBiz;
    private final MainPageRequest mainPageRequest;

    public MainPagePresent(Context mContext, BaseBiz baseBiz) {
        this.mContext = mContext;
        this.baseBiz = baseBiz;
        mainPageRequest = new MainPageRequest();
    }

    /**
     * 首页主题列表
     */
    public void mainPageList(String language, int type, int pageNum) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<MainPageListBean>() {
            @Override
            public void onNext(MainPageListBean subjects) {
                baseBiz.onSuccess(subjects);
            }

            @Override
            public void onError(Throwable e) {
                baseBiz.onError(e);
            }
        };
        mainPageRequest.mainPageList(new ProgressSubscriber(onNextListener, mContext), language, type, pageNum);
    }

}
