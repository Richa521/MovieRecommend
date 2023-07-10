package com.alexc.movielistapp.favourites;

import android.content.Context;
import android.content.SharedPreferences;

import com.alexc.movielistapp.R;

public class PRefs {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public PRefs(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public void setFavouritesChange(int value) {
        editor.putInt("isChange", value);
        editor.apply();
    }
    public int getFavouritesChange() {
        return sharedPreferences.getInt("isChange", 0);
    }
}
