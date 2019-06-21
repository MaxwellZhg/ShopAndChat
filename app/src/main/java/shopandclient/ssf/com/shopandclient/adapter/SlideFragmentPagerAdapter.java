package shopandclient.ssf.com.shopandclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import shopandclient.ssf.com.shopandclient.ui.fragment.FragmentItem1;
import shopandclient.ssf.com.shopandclient.ui.fragment.FragmentItem2;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class SlideFragmentPagerAdapter  extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    public SlideFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            mCurrentView = (View) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            mCurrentView = fragment.getView();
        }
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab"+position;
    }
}
