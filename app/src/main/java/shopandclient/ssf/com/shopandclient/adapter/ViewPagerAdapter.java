package shopandclient.ssf.com.shopandclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/9/18
 * Desc:
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList;
    public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> mList ) {
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
