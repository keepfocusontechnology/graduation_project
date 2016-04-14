package com.qual1ty.yashi_git.interactor;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

/**
 * Created by Tianci on 16/4/6.
 */
public interface RegisterInteractor extends NavigationCommand{

    interface OnRegisterListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void onPswNotEquals();
    }

    void register(String name, String psw, String rePws, String nickname, Context context, OnRegisterListener listener);

    void back(Activity activity);

    void cleanViews(EditText... editTexts);
}
