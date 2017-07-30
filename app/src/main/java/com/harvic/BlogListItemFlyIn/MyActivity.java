package com.harvic.BlogListItemFlyIn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MyActivity extends Activity implements View.OnClickListener {
    private Random random = new Random();
    private TextView tv_test;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TextShowManager.getsInstance().addText("你好" + random.nextInt(1000));
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextShowManager.init(getApplicationContext());
        tv_test = findViewById(R.id.tv_test);
        tv_test.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextShowManager.getsInstance().showCirleTextLayout();
        handler.sendEmptyMessageAtTime(0, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TextShowManager.getsInstance().distoryWindow();
        handler.removeMessages(0);
    }

    @Override
    public void onClick(View view) {
        if (view == tv_test) {
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
        }
    }
}
