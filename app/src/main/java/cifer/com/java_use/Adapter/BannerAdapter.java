package cifer.com.java_use.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2018/4/16 15:44.
 * no use now
 */

public class BannerAdapter extends PagerAdapter {
    private ViewGroup mviewgroup;

    @Override
    public int getCount() {
        return mviewgroup.getChildCount();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mviewgroup = container;
        return super.instantiateItem(container, position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public View getView(int position){
        return mviewgroup.getChildAt(position);
    }
}
