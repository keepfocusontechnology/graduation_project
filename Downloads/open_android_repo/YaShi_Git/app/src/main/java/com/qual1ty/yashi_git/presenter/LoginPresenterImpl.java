package com.qual1ty.yashi_git.presenter;

import com.qual1ty.yashi_git.activity.LoginActivity;
import com.qual1ty.yashi_git.interactor.LoginInteractorImpl;
import com.qual1ty.yashi_git.interactor.NavigationCommand;

/**
 * Created by Tianci on 16/4/5.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractorImpl.OnLoginFinishedListener {

    private LoginActivity loginView;
    private LoginInteractorImpl loginInteractorImpl;
    private NavigationCommand navigationCommand;

    public LoginPresenterImpl(LoginActivity activity) {
        this.loginView = activity;
        this.loginInteractorImpl = new LoginInteractorImpl();
        this.navigationCommand = loginInteractorImpl;
    }

    @Override
    public void loginAction(String username, String psw) {
        if (loginView != null) {
            loginView.showProgress();
            loginInteractorImpl.login(username, psw, loginView, this);
        }

    }


    @Override
    public void jumpPageAction(Class<?> clazz) {
        navigationCommand.navigate(loginView, clazz);
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
