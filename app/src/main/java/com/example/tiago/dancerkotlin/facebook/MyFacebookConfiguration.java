package com.example.tiago.dancerkotlin.facebook;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

/**
 * Created by tiago on 13/03/2017.
 */

public class MyFacebookConfiguration {
    Permission[] permissions = new Permission[]{Permission.PUBLIC_PROFILE, Permission.EMAIL, Permission.USER_FRIENDS};
    public static final String APP_ID = "2043023139243177";

    public SimpleFacebookConfiguration getMyConfig(){
        SimpleFacebookConfiguration configs = new SimpleFacebookConfiguration.Builder()
                .setAppId(APP_ID)
                .setNamespace("Gig")
                .setPermissions(permissions)
                .build();
        return configs;
    }
}
