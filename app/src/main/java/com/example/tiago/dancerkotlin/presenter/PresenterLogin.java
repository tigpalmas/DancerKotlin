package com.example.tiago.dancerkotlin.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.tiago.dancerkotlin.model.MVP;
import com.example.tiago.dancerkotlin.model.ModelLogin;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tiago on 28/12/2017.
 */

public class PresenterLogin implements MVP.PresenterLogin {
    private MVP.ModelLogin model;
    private MVP.ViewLogin view;
    private Activity activity;
    private Context ctx;

    public PresenterLogin(Activity activity, Context ctx) {
        this.activity = activity;
        this.ctx = ctx;
        this.model = new ModelLogin(this);
    }

    @Override
    public void loginFacebook() {
        model.loginFacebook();
    }

    @NotNull
    @Override
    public Context getContext() {
        return this.ctx;
    }

    @NotNull
    @Override
    public Activity getActivity() {
        return this.activity;
    }

    @Override
    public void setView(@NotNull MVP.ViewLogin view) {
        this.view = view;
    }

    @Override
    public void saveUserPref() {

    }

    @Override
    public boolean checkUserAuthorizad() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NotNull Intent data) {
        model.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showProgressbar(boolean status, @NotNull String message) {
        view.showProgressbar(status, message);
    }

    @Override
    public void showToast(@NotNull String message) {
        view.showToast(message);
    }

    @Override
    public void goMainActivity() {
        view.goMainActivity();
    }
}
