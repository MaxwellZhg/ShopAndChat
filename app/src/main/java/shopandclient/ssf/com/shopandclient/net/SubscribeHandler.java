package shopandclient.ssf.com.shopandclient.net;

import android.text.TextUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.util.SHA1;
import shopandclient.ssf.com.shopandclient.util.SpConfig;


import java.security.DigestException;
import java.util.Map;

/**
 * Created by zhg on 2019/4/15.
 */
public class SubscribeHandler {
    /**
     * @param o
     * @param s
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 添加共同参数 SHA1加密
     * @param fields
     */
    public void handleFields(Map<String, Object> fields) {
        fields.put("appKey", "00001");
        if(!fields.containsKey("v")){
            fields.put("v", "1.0");
        }

        String sessionId = SpConfig.getInstance().getString(Constants.SESSIONID_STRING);
        if(!TextUtils.isEmpty(sessionId))
            fields.put(Constants.SESSIONID_STRING, sessionId);

        String sha1 = null;
        try {
            sha1 = SHA1.SHA1(fields);
        } catch (DigestException e) {
            e.printStackTrace();
        }
        fields.put("sign", sha1);
    }
}
