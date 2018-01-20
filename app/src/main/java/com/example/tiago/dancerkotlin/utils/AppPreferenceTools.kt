package com.example.tiago.dancerkotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.User


/**
 * Created by tiago on 14/01/2017.
 */

class AppPreferenceTools(private val mContext: Context) {
    private val mPreference: SharedPreferences

    init {
        this.mPreference = this.mContext.getSharedPreferences("app_preference", Context.MODE_PRIVATE)
    }

    companion object {
        private val PREF = "PREFERENCES"
        val STRING_PREF_UNAVAILABLE = "string preference unavailable"
    }

    fun saveUserAuthenticationInfo(user: User) {
        mPreference.edit()
                .putString(this.mContext.getString(R.string.pref_user_id), user._id)
                .putString(this.mContext.getString(R.string.pref_user_name), user.name)
                .putString(this.mContext.getString(R.string.pref_user_email), user.email)
                .putString(this.mContext.getString(R.string.pref_user_url_image), user.imageURL)
                .putString(this.mContext.getString(R.string.pref_user_city), user.city)
                .putBoolean("user_isdancer", user.isDancer)
                .putBoolean(this.mContext.getString(R.string.pref_user_accept_terms), user.acceptTerms)
                .putBoolean(this.mContext.getString(R.string.pref_user_created), user.created)
                .apply();
    }

    fun getUser(): User {
        val user = User()
        user._id = mPreference.getString(this.mContext.getString(R.string.pref_user_id), "")
        user.name = mPreference.getString(this.mContext.getString(R.string.pref_user_name), "")
        user.email = mPreference.getString(this.mContext.getString(R.string.pref_user_email), "")
        user.imageURL = mPreference.getString(this.mContext.getString(R.string.pref_user_url_image), "")
        user.city = mPreference.getString(this.mContext.getString(R.string.pref_user_city), "Curitiba")
        user.acceptTerms = mPreference.getBoolean(this.mContext.getString(R.string.pref_user_accept_terms), false)
        user.created = mPreference.getBoolean(this.mContext.getString(R.string.pref_user_created), false)

        return user
    }

    fun saveDancer(dancer: Dancer) {
        mPreference.edit()
                .putString(this.mContext.getString(R.string.pref_dancer_id), dancer._id)
                .putString(this.mContext.getString(R.string.pref_dancer_description), dancer.description)
                .putString(this.mContext.getString(R.string.pref_dancer_background), dancer.imagePath)
                .putString(this.mContext.getString(R.string.pref_dancer_level), dancer.level)
                .putString(this.mContext.getString(R.string.pref_dancer_value_hour), dancer.priceHour)
                .putString(this.mContext.getString(R.string.pref_dancer_school), dancer.school)
                .putString(this.mContext.getString(R.string.pref_dancer_phone), dancer.phone)
                .apply();
    }

    fun getDancer():Dancer{
        val dancer = Dancer()
        dancer.description = mPreference.getString(this.mContext.getString(R.string.pref_dancer_description),"")
        dancer.imagePath = mPreference.getString(this.mContext.getString(R.string.pref_dancer_background),"")
        dancer._id = mPreference.getString(this.mContext.getString(R.string.pref_dancer_id),"")
        dancer.level = mPreference.getString(this.mContext.getString(R.string.pref_dancer_level),"")
        dancer.priceHour = mPreference.getString(this.mContext.getString(R.string.pref_dancer_value_hour),"")
        dancer.school = mPreference.getString(this.mContext.getString(R.string.pref_dancer_school),"")
        dancer.phone = mPreference.getString(this.mContext.getString(R.string.pref_dancer_phone),"")
        return dancer;
    }

    fun updateIntroStatus(status: Boolean){
        mPreference
                .edit().putBoolean("status", status).apply()
    }

    fun isIntroActivityShown(){
        mPreference.getBoolean("status", false)
    }

    //Save the user authentication model to pref at sing up|| sign in


}
