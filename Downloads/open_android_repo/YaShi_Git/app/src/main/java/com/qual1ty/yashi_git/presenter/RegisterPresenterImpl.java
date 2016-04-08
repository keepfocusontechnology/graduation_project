package com.qual1ty.yashi_git.presenter;

import android.content.Context;

import com.qual1ty.yashi_git.activity.RegisterActivity;
import com.qual1ty.yashi_git.interactor.RegisterInteractor;
import com.qual1ty.yashi_git.interactor.RegisterInteractorImpl;

/**
 * Created by Tianci on 16/4/6.
 */
public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnRegisterListener {

    private RegisterActivity view;
    private RegisterInteractorImpl registerInteractor;

    public RegisterPresenterImpl(RegisterActivity view) {
        this.view = view;
        registerInteractor = new RegisterInteractorImpl();
    }


    @Override
    public void registerAction(String name, String psw, Context context) {

        if (view != null) {
            view.showProgress();
            registerInteractor.register(name, psw, context, this);
        }


    }

    @Override
    public void destory() {
        view = null;
    }

    @Override
    public void backAction() {
        registerInteractor.back(view);
    }

    @Override
    public void onUsernameError() {
        if (view != null) {
            view.hideProgress();
            view.setUserNameError();
        }

    }

    @Override
    public void onPasswordError() {
        if (view != null) {
            view.hideProgress();
            view.setPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.registerSuccessed();
        }
    }
}
