package cifer.com.java_use.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cifer.com.java_use.Adapter.GlideImageLoader;
import cifer.com.java_use.Adapter.WaterFallAdapter;
import cifer.com.java_use.R;
import cifer.com.java_use.bean.Bookbean;
import cifer.com.java_use.bean.OkhttpManager;
import cifer.com.java_use.bean.Url;

/**
 * Created on 2018/4/16 11:46
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.listview)
    RecyclerView recyclerView;

    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<Bookbean> bookbeans = new ArrayList<>();

    private OkhttpManager okhttpManager;

    private String jsonString ;
    private List<String> titles  =new ArrayList<>();
    private WaterFallAdapter adapter;

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new WaterFallAdapter(mActivity);
        recyclerView.setAdapter(adapter);
        loadMessage(0);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        if(savedInstanceState == null){
            ButterKnife.bind(this,view);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    private void loadMessage(int position){
        if(okhttpManager == null){
            okhttpManager = OkhttpManager.getInstance();
        }
        if(TextUtils.isEmpty(jsonString)){

            okhttpManager.asyncJsonStringByURL(Url.lastlisturl, new OkhttpManager.Fun1() {
                @Override
                public void onResponse(String result) {
                    jsonString = result ;
                    analizeJson();
                }
            },null);
        }else{
            analizeJson();
        }
    }


    private void analizeJson(){
        try {
            JSONObject object=new JSONObject(jsonString);
            int statusCode=object.optInt("statusCode");
            if(statusCode==200){
                JSONObject pageBean=object.optJSONObject("pageBean");
                JSONArray list=pageBean.getJSONArray("list");
                if(list != null){
                    for(int i = 0; i< list.length(); i++){
                        JSONObject objectList=list.getJSONObject(i);
                        Bookbean book = new Bookbean();
                        book.setBookid(objectList.getString("bookId"));
                        book.setBookname(objectList.getString("bookTitle"));
                        book.setBookpicture(objectList.getString("bookPicture"));
                        bookbeans.add(book);
                    }
                    adapter.getList().addAll(bookbeans);
                    adapter.getRandomHeight(bookbeans);
                    adapter.notifyDataSetChanged();
                }
            }
            loadBitmaps();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadBitmaps(){
        int bannersize = bookbeans.size()>=5 ? 5 : bookbeans.size();
        for(int i = 0 ; i<bannersize ; i++ ){
            String url = bookbeans.get(i).getBookpicture();
            images.add(url);
            titles.add("");//不设置title 图片不显示
        }
        setBanner();
    }

    private void setBanner(){
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader(){
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(path).into(imageView);
            }
        });
        //设置图片集合
        Log.d("xiao","images =="  + images);
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        if(banner != null){
            banner.stopAutoPlay();
        }
        bookbeans = new ArrayList<>();
        titles =new ArrayList<>();
        images = new ArrayList<>();
    }
}
