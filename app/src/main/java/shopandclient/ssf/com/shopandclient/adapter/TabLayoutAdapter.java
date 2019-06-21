package shopandclient.ssf.com.shopandclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class TabLayoutAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mList;
    private FragmentManager fm;

    public TabLayoutAdapter(FragmentManager fm, ArrayList<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
