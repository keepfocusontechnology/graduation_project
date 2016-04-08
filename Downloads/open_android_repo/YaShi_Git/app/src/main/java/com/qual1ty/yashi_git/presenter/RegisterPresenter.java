package com.qual1ty.yashi_git.presenter;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by Tianci on 16/4/6.
 */
public interface RegisterPresenter {

    void registerAction(String name, String psw, String repetPsw, String nickname, Context context);

    void destory();

    void backAction();

    void clean(EditText... views);
}
