package com.example.tiago.dancerkotlin.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tiago.dancerkotlin.domain.Auth;
import com.example.tiago.dancerkotlin.domain.ErrorModel;
import com.example.tiago.dancerkotlin.domain.User;
import com.example.tiago.dancerkotlin.facebook.Login;
import com.example.tiago.dancerkotlin.network.DancerProvider;
import com.example.tiago.dancerkotlin.network.DancerService;
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools;
import com.example.tiago.dancerkotlin.utils.ErrorUtils;
import com.example.tiago.dancerkotlin.utils.Util;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by tiago on 28/12/2017.
 */

public class ModelLogin implements MVP.ModelLogin {
    private MVP.PresenterLogin presenter;
    private Login login;
    private Activity activity;
    private Context ctx;
    private DancerService service;


    public ModelLogin(MVP.PresenterLogin presenter) {
        DancerProvider provider = new DancerProvider();
        service = provider.getmTService();
        this.presenter = presenter;
        activity = presenter.getActivity();
        ctx = presenter.getContext();
        login = new Login(activity, this);
    }

    @Override
    public void loginFacebook() {
        login.login();
    }

    @NotNull
    @Override
    public Activity getActivity() {
        return this.activity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NotNull Intent data) {
        login.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loginServer(final Auth auth) {
        Call<Auth> call = service.registerUser(auth);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
                    Auth auth = response.body();
                    User user = auth.getUserData();
                    user.setCreated(true);
                    AppPreferenceTools preferenceTools =  Util.getAppPreference(ctx);
                    preferenceTools.saveUserAuthenticationInfo(user);
                    showToast("Seja Bem Vindo "+user.name);
                    presenter.goMainActivity();
                } else {
                    ErrorModel errorModel = ErrorUtils.parseError(response);
                    Log.i("teste", errorModel.getMessage()+" "+errorModel.getResMessage());

                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

            }
        });
    }

    @Override
    public void showProgressbar(boolean status, @NotNull String message) {
        presenter.showProgressbar(status, message);
    }

    @Override
    public void showToast(@NotNull String message) {
        presenter.showToast(message);
    }
}
