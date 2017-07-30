package com.harvic.BlogListItemFlyIn;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-7-30 0030.
 */

public class TextShowManager {
    private Context context;
    private static TextShowManager sInstance;
    private WindowManager manager;
    private View rootView;
    private ListView listView;
    private TextAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private LayoutParams layoutParams;

    private TextShowManager(Context context) {
        this.context = context;
        manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
    }

    public synchronized static void init(Context context) {
        if (null == context) throw new RuntimeException("context cannot be null");
        if (null == sInstance) {
            sInstance = new TextShowManager(context);
        }
    }

    public synchronized static TextShowManager getsInstance() {
        if (null == sInstance) {
            throw new RuntimeException("Please init TextShowManager int application,oncreate");
        }
        return sInstance;
    }

    public void showCirleTextLayout() {
        if (null != rootView) {
            manager.removeView(rootView);
        } else {
            rootView = View.inflate(context, R.layout.circle_text_layout, null);
            listView = rootView.findViewById(R.id.list);
            list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            adapter = new TextAdapter(context, list, listView);
            listView.setAdapter(adapter);

            layoutParams = new LayoutParams();
            layoutParams.type = LayoutParams.TYPE_PHONE;
            layoutParams.format = PixelFormat.TRANSPARENT;
            layoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams.gravity = Gravity.RIGHT | Gravity.TOP;
            layoutParams.width = context.getResources().getDimensionPixelOffset(R.dimen.circle_text_width);
            layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.circle_text_heigh);
            layoutParams.x = context.getResources().getDimensionPixelOffset(R.dimen.circle_text_margin);
            layoutParams.y = context.getResources().getDimensionPixelOffset(R.dimen.circle_text_margin);
            rootView.setLayoutParams(layoutParams);
        }
        manager.addView(rootView, layoutParams);
    }

    public void addText(String str) {
        if (list.size() > 10) {
            list = list.subList(list.size() - 3, list.size());
            adapter = new TextAdapter(context, list, listView);
            listView.setAdapter(adapter);
        }
        list.add(str);
        adapter.notifyDataSetChanged();
        listView.smoothScrollToPosition(list.size() - 1);
    }

    public void distoryWindow() {
        manager.removeView(rootView);
        list.clear();
        adapter.notifyDataSetChanged();
        rootView = null;
    }
}
