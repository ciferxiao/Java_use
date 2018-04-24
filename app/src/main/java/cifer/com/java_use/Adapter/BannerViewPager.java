package cifer.com.java_use.Adapter;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Handler;

/**
 * Created on 2018/4/16 15:41.
 * 轮播图测试 no use now
 */

public class BannerViewPager extends ViewPager {

    private Context mContext;

    private BannerAdapter mAdapter;

    private final int SCROLL_MSG = 0x01;

    private static final int scrolltime = 3500;

    android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            setCurrentItem(getCurrentItem() + 1);
            startRoll();
        }
    };


    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void startRoll(){
        handler.removeMessages(SCROLL_MSG);
        handler.sendEmptyMessageAtTime(SCROLL_MSG,scrolltime);
    }

    public void setAdapter(BannerAdapter adapter) {
        this.mAdapter = adapter;
        setAdapter(new BannerPagerAdapter());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeMessages(SCROLL_MSG);
        handler = null;

    }

    private class BannerPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // 返回一个很大的值，确保可以无限轮播
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // 这么写就对了，看了源码应该就明白
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View bannerView = mAdapter.getView(position);
            container.addView(bannerView );
            return bannerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁回调的方法  移除页面即可
            container.removeView((View) object);
        }

    }


}
