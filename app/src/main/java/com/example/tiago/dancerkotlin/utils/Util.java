package com.example.tiago.dancerkotlin.utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tiago on 22/01/2017.
 */

public class Util {





    @TargetApi((Build.VERSION_CODES.M))
    public static boolean isSystemAlertPermissionGranted(Context ctx){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(ctx);
    }


    public static void showProgressDialog(Context ctx, ProgressDialog mProgressDialog, boolean active, String message){
        if(active){
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }else{
            mProgressDialog.dismiss();
        }
    }

    public static void showToast(Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static AppPreferenceTools getAppPreference(Context ctx ){
         AppPreferenceTools appPreferenceTools =   new AppPreferenceTools(ctx);
         return  appPreferenceTools;
    }
    public static void showSnackBar(Context ctx, View view, String text){

    }

    public static String obterDataPorExtenso(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM ", BRAZIL);
        return dfmt.format(dataAtual);
    }

    public static String obterMonth(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("MMM ", BRAZIL);
        return dfmt.format(dataAtual);
    }

    public static String obterDay(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("dd ", BRAZIL);
        return dfmt.format(dataAtual);
    }

}
