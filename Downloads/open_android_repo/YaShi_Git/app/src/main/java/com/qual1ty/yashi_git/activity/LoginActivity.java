package com.qual1ty.yashi_git.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.qual1ty.yashi_git.R;
import com.qual1ty.yashi_git.presenter.LoginPresenter;
import com.qual1ty.yashi_git.presenter.LoginPresenterImpl;
import com.qual1ty.yashi_git.utils.ToastUtil;
import com.qual1ty.yashi_git.view.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tianci on 16/4/5.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter presenter;

    @Bind(R.id.log_page_bt_log)
    Button bt_log;
    @OnClick(R.id.log_page_bt_log)
    public void login(){
        presenter.loginAction(et_username.getText().toString(),et_psw.getText().toString());
    }


    @Bind(R.id.log_page_bt_reg)
    Button bt_reg;
    @OnClick(R.id.log_page_bt_reg)
    public void register(){
        presenter.registerAction(RegisterActivity.class);
    }

    @Bind(R.id.log_page_et_username)
    EditText et_username;
    @Bind(R.id.log_page_et_password)
    EditText et_psw;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showProgress() {
        showTheProgress();
    }

    @Override
    public void hideProgress() {
        hideTheProgress();
    }

    @Override
    protected boolean showToolBar() {
        return false;
    }

    @Override
    public void setUserNameError() {
        ToastUtil.toastShort(this, "用户名错误");
    }

    @Override
    public void setPasswordError() {
        ToastUtil.toastShort(this,"密码错误");
    }

    @Override
    public void loginSuccessed() {
        ToastUtil.toastShort(this, "登陆成功");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
    }
}
