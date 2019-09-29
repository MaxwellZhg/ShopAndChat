package shopandclient.ssf.com.shopandclient.util;

import android.os.Handler;
import android.os.Looper;

import java.util.Timer;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/9/29
 * Desc:
 */
public class TokenManager implements Subject<Observer>{
    private int recLen = 5;//跳过倒计时提示5秒
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private volatile static TokenManager tokemanager;
    @Override
    public void registerObserver(Observer obs) {
       list.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
      list.remove(obs);
    }

    @Override
    public void notifyAllObservers() {
       for(int i = 0; i<list.size();i++){
           list.get(i).update(this);
       }
    }
    public static TokenManager newInstance() {
                     if (tokemanager == null) {
                             synchronized (TokenManager.class) {
                                    if (tokemanager == null) {
                                        tokemanager = new TokenManager();
                                         }
                                }
                       }
                    return tokemanager;
               }

    public void TimeSchecher(){
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
               notifyAllObservers();
            }
        }, 1000*60*30);//延迟5S后发送handler信息
    }
}

