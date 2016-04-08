package com.qual1ty.yashi_git;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.qual1ty.yashi_git.activity.BaseActivity;
import com.qual1ty.yashi_git.activity.LoginActivity;

import java.lang.ref.WeakReference;

/**
 * Created by Tianci on 16/4/5.
 */
public class SplashActivity extends BaseActivity {

    private static final int TOLOGINPAGE = 1;

    class MyHandler extends Handler {
        private WeakReference<Activity> reference;

        public MyHandler(Activity context) {
            reference = new WeakReference<Activity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Activity activity = reference.get();
            if (msg.what == TOLOGINPAGE) {
                if (activity != null) {
                    startActivity(new Intent(activity, LoginActivity.class));
                    activity.finish();
                }
            }


        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        MyHandler handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(TOLOGINPAGE, 500);

    }

    @Override
    protected boolean showToolBar() {
        return false;
    }
}
