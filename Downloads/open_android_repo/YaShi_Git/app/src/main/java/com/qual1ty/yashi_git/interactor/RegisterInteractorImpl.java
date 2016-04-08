package com.qual1ty.yashi_git.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.EditText;

import com.qual1ty.yashi_git.bean.User;
import com.qual1ty.yashi_git.database.DataBaseHelper;
import com.qual1ty.yashi_git.database.UserDao;

import java.util.List;

/**
 * Created by Tianci on 16/4/6.
 */
public class RegisterInteractorImpl implements RegisterInteractor {


    @Override
    public void register(final String name, final String psw, final String rePsw, final String nickname, final Context context, final OnRegisterListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.username = name;
                user.psw = psw;
                user.nickName = nickname;

                UserDao userDao;

                if (!TextUtils.isEmpty(name)) {
                    if (TextUtils.isEmpty(psw)) {
                        listener.onPasswordError();
                    } else if (!psw.equals(rePsw)) {
                        listener.onPswNotEquals();
                    } else {
                        DataBaseHelper helper = new DataBaseHelper(context);//参数是上下文
                        userDao = new UserDao(context);
                        List<User> users = userDao.query();
                        boolean nameIsExist = uesrIsExist(users, name);
                        if (nameIsExist)
                            listener.onUsernameError();
                        else {
                            userDao.insert(user);
                            listener.onSuccess();
                        }
                    }
                } else {
                    listener.onUsernameError();
                }
            }
        }, 1000);

    }

    /**
     * 该用户名是否存在
     *
     * @param users
     * @param username
     * @return true代表存在，false代表不存在
     */
    public boolean uesrIsExist(List<User> users, String username) {

        for (User user : users) {
            if (user.username.equals(username))
                return true;
        }

        return false;
    }

    @Override
    public void back(Activity activity) {
        if (activity != null)
            activity.finish();
    }

    @Override
    public void cleanViews(EditText... editTexts) {
        for (EditText view :
                editTexts) {
            view.setText(null);
        }
    }

    @Override
    public void jump2mainPage(Activity activity,Class<?> clazz) {
        activity.startActivity(new Intent(activity,clazz));
    }
}
