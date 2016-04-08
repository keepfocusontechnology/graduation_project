package com.qual1ty.yashi_git.presenter;

import com.qual1ty.yashi_git.activity.LoginActivity;
import com.qual1ty.yashi_git.interactor.LoginInteractorImpl;

/**
 * Created by Tianci on 16/4/5.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractorImpl.OnLoginFinishedListener {

    private LoginActivity loginView;
    private LoginInteractorImpl loginInteractorImpl;

    public LoginPresenterImpl(LoginActivity activity) {
        this.loginView = activity;
        this.loginInteractorImpl = new LoginInteractorImpl();
    }

    @Override
    public void loginAction(String username, String psw) {
        if (loginView != null)
            loginView.showProgress();
        loginInteractorImpl.login(username, psw, this);

    }


    @Override
    public void registerAction(Class<?> clazz) {
        loginInteractorImpl.register(loginView, clazz);
    }

    @Override
    public void destory() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.setUserNameError();

        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.setPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.loginSuccessed();
        }
    }
}
