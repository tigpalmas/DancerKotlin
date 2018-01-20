package com.example.tiago.dancerkotlin.facebook;

import android.content.Context;
import android.content.Intent;

import com.example.tiago.dancerkotlin.domain.Auth;
import com.example.tiago.dancerkotlin.domain.User;
import com.example.tiago.dancerkotlin.model.MVP;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import java.util.List;


/**
 * Created by tiago on 13/03/2017.
 */

public class Login {
    private Context ctx;
    private SimpleFacebook fb;

    private MVP.ModelLogin model;


    Profile.Properties properties = new Profile.Properties.Builder()
            .add(Profile.Properties.ID)
            .add(Profile.Properties.FIRST_NAME)
            .add(Profile.Properties.LAST_NAME)
            .add(Profile.Properties.GENDER)
            .add(Profile.Properties.EMAIL)
            .add(Profile.Properties.PICTURE)
            .build();

    public Login(Context ctx, MVP.ModelLogin model) {
        this.ctx = ctx;
        SimpleFacebook.setConfiguration(new MyFacebookConfiguration().getMyConfig());
        this.model = model;
        fb = SimpleFacebook.getInstance(model.getActivity());


    }

    public void login() {
        model.showProgressbar(true, "Carregando, aguarde...");
        fb.login(loginListener);
    }

    OnLoginListener loginListener = new OnLoginListener() {
        @Override
        public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
            fb.getProfile(properties, onProfileListener);
        }

        @Override
        public void onCancel() {
             model.showProgressbar(false, "Cancelado");
            model.showToast("Cancelado");

        }

        @Override
        public void onException(Throwable throwable) {
            model.showProgressbar(false, "");
            model.showToast("Ops, algo deu errado, "+ throwable.getMessage()+ ", tente novamente");

        }

        @Override
        public void onFail(String reason) {
            model.showProgressbar(false, "");
            model.showToast("Ops, algo deu errado, "+ reason+ ", tente novamente");

        }
    };


    OnProfileListener onProfileListener = new OnProfileListener() {
        @Override
        public void onComplete(final Profile profile) {

            if(profile!=null) {
               Auth auth = new Auth();
                if(profile.getFirstName()!=null){
                    auth.setName(profile.getFirstName());
                }
                if(profile.getLastName()!=null){
                   auth.setLastName(profile.getLastName());
                }
                if(profile.getEmail()!=null){
                    auth.setEmail(profile.getEmail());
                }

                if(profile.getId()!=null){
                   auth.setFaceId(profile.getId());
                    auth.setPassword(profile.getId());
                    if(profile.getPicture()!=null){
                       auth.setImageURL("https://graph.facebook.com/" + profile.getId() + "/picture?type=large");
                    }
                }
                if(profile.getGender()!=null){
                    auth.setGender(profile.getGender());
                }
                model.loginServer(auth);
            }
        }
    };


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        fb.onActivityResult(requestCode, resultCode, data);
    }





}
