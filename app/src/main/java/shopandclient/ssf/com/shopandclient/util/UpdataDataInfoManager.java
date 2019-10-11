package shopandclient.ssf.com.shopandclient.util;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/11
 * Desc:
 */
public class UpdataDataInfoManager implements UpdataCartSubject<UpCartDataInfo> {
    @Override
    public void registerObserver(UpCartDataInfo obs) {
        list.add(obs);
    }

    @Override
    public void removeObserver(UpCartDataInfo obs) {
          list.remove(obs);
    }

    @Override
    public void notifyAllObservers() {
        for(int i = 0; i<list.size();i++){
            list.get(i).updataCartyInfo();
        }
    }
}
