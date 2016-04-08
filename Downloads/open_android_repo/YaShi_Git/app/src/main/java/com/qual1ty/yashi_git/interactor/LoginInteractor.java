package com.qual1ty.yashi_git.interactor;

import android.app.Activity;

/**
 * Created by Tianci on 16/4/5.
 */
public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    void login(String username,String password,OnLoginFinishedListener listener);
    void register(Activity srcActivity,Class<?> clazz);
}
