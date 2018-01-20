package com.example.tiago.dancerkotlin.utils;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.webianks.library.scroll_choice.ScrollChoice;


/**
 * Created by tiago on 31/12/2015.
 */
public class EntryCode extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        FacebookSdk.sdkInitialize(this);






    }

}
