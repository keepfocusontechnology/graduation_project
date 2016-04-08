package com.qual1ty.yashi_git.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.qual1ty.yashi_git.R;
import com.qual1ty.yashi_git.presenter.RegisterPresenter;
import com.qual1ty.yashi_git.presenter.RegisterPresenterImpl;
import com.qual1ty.yashi_git.utils.ToastUtil;
import com.qual1ty.yashi_git.view.RegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tianci on 16/4/5.
 */
public class RegisterActivity extends BaseActivity implements RegisterView {

    @Bind(R.id.title_bar_back)
    ImageButton ib_back;
    @OnClick(R.id.title_bar_back)
    public void goback(){
        presenter.backAction();
    }

    @Bind(R.id.reg_page_et_username)
    EditText et_username;
    @Bind(R.id.reg_page_et_password)
    EditText et_psw;

    @OnClick(R.id.reg_page_bt_reg)
    public void reg(){
        presenter.registerAction(et_username.getText().toString(),et_psw.getText().toString(),this);
    }

    private RegisterPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this);
    }

    @Override
    public void setUserNameError() {
        ToastUtil.toastShort(this,"用户名为空或者已存在！");
    }

    @Override
    public void setPasswordError() {
        ToastUtil.toastShort(this,"密码不能为空！");
    }

    @Override
    public void registerSuccessed() {
        ToastUtil.toastShort(this,"注册成功！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
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
}
