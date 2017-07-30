package com.harvic.BlogListItemFlyIn;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017-7-29 0029.
 */

public class TextRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;
    private RecyclerView mListView;
    private Animation animation;

    public TextRecycleAdapter(Context context, List<String> list, RecyclerView mListView) {
        this.context = context;
        this.list = list;
        this.mListView = mListView;
        animation = AnimationUtils.loadAnimation(context, R.anim.bottom_in_anim);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.text_item_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //清除当前显示区域中所有item的动画
        for (int i=0;i<mListView.getChildCount();i++){
            View view = mListView.getChildAt(i);
            view.clearAnimation();
        }
        //然后给当前item添加上动画
        if (position == list.size() - 1) {
            holder.itemView.startAnimation(animation);
        }

        ((ViewHolder)holder).tv_show.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_show;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_show = itemView.findViewById(R.id.tv_show);
        }
    }
}
