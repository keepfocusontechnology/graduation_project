package com.qual1ty.yashi_git.interactor;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Tianci on 16/4/6.
 */
public interface RegisterInteractor {

    interface OnRegisterListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    void register(String name,String psw,Context context,OnRegisterListener listener);

    void back(Activity activity);
}
