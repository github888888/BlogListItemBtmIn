package com.harvic.BlogListItemFlyIn;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017-7-29 0029.
 */

public class TextAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private ListView mListView;
    private Animation animation;

    public TextAdapter(Context context, List<String> list, ListView mListView) {
        this.list = list;
        this.context = context;
        this.mListView = mListView;
        animation = AnimationUtils.loadAnimation(context, R.anim.bottom_in_anim);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = View.inflate(context, R.layout.text_item_layout, null);
            holder = new ViewHolder();
            holder.tv_show = (TextView) convertView.findViewById(R.id.tv_show);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //清除当前显示区域中所有item的动画
        for (int i=0;i<mListView.getChildCount();i++){
            View view = mListView.getChildAt(i);
            view.clearAnimation();
        }
        final View temp = convertView;
        //然后给当前item添加上动画
        if (position == list.size() - 1) {
            temp.post(new Runnable() {
                @Override
                public void run() {
                    temp.startAnimation(animation);
                }
            });
        }

        holder.tv_show.setText(list.get(position));
        return convertView;
    }

    private class ViewHolder{
        public TextView tv_show;
    }
}
