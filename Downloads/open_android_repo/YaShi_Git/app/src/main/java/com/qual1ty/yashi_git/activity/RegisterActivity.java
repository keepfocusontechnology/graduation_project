package com.qual1ty.yashi_git.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.qual1ty.yashi_git.MainActivity;
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

//    @Bind(R.id.title_bar_back)
//    ImageButton ib_back;

    @OnClick(R.id.title_bar_back)
    public void goback() {
        presenter.backAction();
    }

    @OnClick(R.id.reg_page_bt_clean)
    public void clean() {
        presenter.clean(et_username, et_psw, et_repet_psw, et_nickname);
    }

    @Bind(R.id.reg_page_et_username)
    EditText et_username;
    @Bind(R.id.reg_page_et_password)
    EditText et_psw;
    @Bind(R.id.reg_page_et_repeat_psw)
    EditText et_repet_psw;
    @Bind(R.id.reg_page_et_nickname)
    EditText et_nickname;

    @OnClick(R.id.reg_page_bt_reg)
    public void reg() {
        presenter.registerAction(et_username.getText().toString(),
                et_psw.getText().toString(),
                et_repet_psw.getText().toString(),
                et_nickname.getText().toString(),
                this);
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
        ToastUtil.toastShort(this, "用户名为空或者已存在！");
    }

    @Override
    public void setPasswordError() {
        ToastUtil.toastShort(this, "密码不能为空！");
    }

    @Override
    public void rePswError() {
        ToastUtil.toastShort(this, "两次输入密码不一致！");
    }

    @Override
    public void registerSuccessed() {
        ToastUtil.toastShort(this, "注册成功！");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.jumpMainPageAction(MainActivity.class);
                manager.removeAll();
            }
        },1000);

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
