package shopandclient.ssf.com.shopandclient.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/11
 * Desc:
 */
public interface UpdataCartSubject<T> {
    // 存储订阅者
    List<UpCartDataInfo> list = new ArrayList<>();

    // 注册订阅者
    void registerObserver(T obs);

    // 移除订阅者
    void removeObserver(T obs);

    //通知所有的观察者更新状态
    void notifyAllObservers();
}
