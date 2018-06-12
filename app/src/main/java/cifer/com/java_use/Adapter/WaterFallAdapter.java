package cifer.com.java_use.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

import cifer.com.java_use.R;
import cifer.com.java_use.bean.Bookbean;

/**
 * Created by Administrator on 2016/5/31 0031.
 *
 *
 */
public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {

    private Context mContext;
    private List<Bookbean> mList = new ArrayList<>();
    private List<Integer> mHeights;
    private Bookbean bean;

    public WaterFallAdapter(Context context){
        this.mContext = context;
    }
    //目前用不到
    public void getRandomHeight(List<Bookbean> mList){
        mHeights = new ArrayList<>();
        for(int i=0; i < mList.size();i++){
            //随机的获取一个范围为100-300直接的高度
            mHeights.add(280);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_homelist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImageView = view.findViewById(R.id.imageview);
        viewHolder.mtext = view.findViewById(R.id.text_view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        bean = mList.get(position);
        holder.mtext.setText(bean.getBookname());
        holder.mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(bean.getBookpicture()).into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data",bean.getBookname());
                intent.putExtra("dataint",position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageview)
        ImageView mImageView ;

        @BindView(R.id.text_view)
        TextView mtext;
        ViewHolder(View view){
            //需要设置super
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public List<Bookbean> getList() {
        return mList;
    }


}
