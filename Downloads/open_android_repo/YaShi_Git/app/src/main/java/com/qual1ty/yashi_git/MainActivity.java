package com.qual1ty.yashi_git;

import android.os.Bundle;

import com.qual1ty.yashi_git.activity.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected boolean showToolBar() {
        return false;
    }
}
