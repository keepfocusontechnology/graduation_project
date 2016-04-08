package com.qual1ty.yashi_git.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qual1ty.yashi_git.utils.ActivityManager;

/**
 * Created by Tianci on 16/4/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    public ActivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!showToolBar())
            getSupportActionBar().hide();
        setContentView(getLayoutId());
        createProgressDialog();
        initViews(savedInstanceState);
        manager = ActivityManager.getInstance();
        manager.add(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(Bundle savedInstanceState);

    private void createProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("加载中...");
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度
    }

    public void showTheProgress() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hideTheProgress() {
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.remove(this);
        dialog.dismiss();
        dialog = null;
    }

    protected abstract boolean showToolBar();

}

