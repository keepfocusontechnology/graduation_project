package com.qual1ty.yashi_git.presenter;

import android.content.Context;

/**
 * Created by Tianci on 16/4/6.
 */
public interface RegisterPresenter {

    void registerAction(String name,String psw,Context context);

    void destory();

    void backAction();
}
