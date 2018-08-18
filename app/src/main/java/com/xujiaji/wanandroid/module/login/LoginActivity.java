package com.xujiaji.wanandroid.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.xujiaji.mvvmquick.base.MQActivity;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.databinding.ActivityLoginBinding;
import com.xujiaji.wanandroid.helper.AnimHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.repository.bean.UserBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import static com.xujiaji.wanandroid.helper.CheckHelper.isPasswordValid;

public class LoginActivity extends MQActivity<ActivityLoginBinding, LoginViewModel> implements View.OnFocusChangeListener, TextView.OnEditorActionListener {

    private boolean isLogIn = true;

    public static void launch(@Nullable Context context) {
        if (context == null) return;
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void onBinding(ActivityLoginBinding binding) {
        super.onBinding(binding);

        binding.btnSwitch.setOnClickListener(v -> {
            isLogIn = !isLogIn;
            switchLogInUP(isLogIn);

            AnimHelper.rotateSwitch(binding.btnSwitch, isLogIn);
            AnimHelper.switchUpDown(binding.layoutTitle, true, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    binding.setIsRegister(!isLogIn);
                    AnimHelper.switchUpDown(binding.layoutTitle, false, null);
                }

            });
        });

        binding.btnBack.setOnClickListener((View.OnClickListener) v -> finish());

        binding.password.setOnEditorActionListener(this);
        binding.againPassword.setOnEditorActionListener(this);
        binding.email.setOnFocusChangeListener(this);
        binding.password.setOnFocusChangeListener(this);
        binding.againPassword.setOnFocusChangeListener(this);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL)
                && ((isLogIn && v == binding.password) || (!isLogIn && v == binding.againPassword))
                ) { // 点击了键盘上的回车，如果是登录并且是密码框，则登录。如果是注册，并且是再次输入密码框，则注册
            onClickOk(null);
            return true;
        }

        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) return;
        switch (v.getId()) {
            case R.id.email:
                checkGetEmail();
                break;
            case R.id.password:
                checkGetPassword();
                break;
            case R.id.againPassword:
                checkGetAgainPassword();
                break;
        }
    }

    public void switchLogInUP(boolean isLogIn) {
        if (isLogIn) {
            AnimHelper.showOut(binding.layoutAgainPassword);
        } else {
            AnimHelper.showIn(binding.layoutAgainPassword);
        }
        binding.email.setError(null);
        binding.password.setError(null);
        binding.againPassword.setError(null);
        binding.email.setText("");
        binding.password.setText("");
        binding.againPassword.setText("");
    }

    private String checkGetAgainPassword() {
        final String againPassword = binding.againPassword.getText().toString().trim();
        if (TextUtils.isEmpty(againPassword)) {
            binding.againPassword.setError(getString(R.string.error_field_required));
            return null;
        }

        if (!againPassword.equals(checkGetPassword())) {
            binding.againPassword.setError(getString(R.string.error_incorrect_again_password));
            return null;
        }

        return againPassword;
    }

    private String checkGetPassword() {
        final String password = binding.password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            binding.password.setError(getString(R.string.error_field_required));
            return null;
        }

        if (!isPasswordValid(password)) {
            binding.password.setError(getString(R.string.error_invalid_password));
            return null;
        }
        return password;
    }

    private String checkGetEmail() {
        final String email = binding.email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            binding.email.setError(getString(R.string.error_field_required));
            return null;
        }

//        if (!isEmailValid(email)) {
//            binding.email.setError(getString(R.string.error_invalid_email));
//            return null;
//        }
        return email;
    }

    public void onClickOk(View view) {
        binding.email.setError(null);
        binding.password.setError(null);
        binding.againPassword.setError(null);

        final String email = checkGetEmail();
        if (email == null) return;

        final String password = isLogIn ? checkGetPassword() : checkGetAgainPassword();
        if (password == null) return;

        binding.setIsLoading(true);

        if (isLogIn) {
            viewModel.postObservableLogin(email, password).observeData(this, new DataCallbackImp<UserBean>() {
                @Override
                public void finished() {
                    binding.setIsLoading(false);
                }

                @Override
                public void success(UserBean bean) {
                    handleResult(true, bean);
                }
            });
        } else {
            viewModel.postObservableRegister(email, password).observeData(this, new DataCallbackImp<UserBean>() {
                @Override
                public void finished() {
                    binding.setIsLoading(false);
                }

                @Override
                public void success(UserBean bean) {
                    handleResult(false, bean);
                }

            });
        }
    }

    private void handleResult(boolean isLogIn, UserBean bean) {
        App.Login.in(bean);
        ToastHelper.success(isLogIn ? App.getInstance().getString(R.string.success_login) : App.getInstance().getString(R.string.success_register));
        finish();
    }

}

