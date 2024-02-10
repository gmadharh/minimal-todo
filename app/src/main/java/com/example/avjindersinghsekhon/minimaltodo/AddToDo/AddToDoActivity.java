package com.example.avjindersinghsekhon.minimaltodo.AddToDo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class AddToDoActivity extends AppDefaultActivity {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the theme
        String theme = getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);

        //set the theme
        if(theme.equals(MainFragment.LIGHTTHEME)) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else if (theme.equals(MainFragment.DARKTHEME)) {
            setTheme(R.style.CustomStyle_DarkTheme);
        } else if (theme.equals(MainFragment.LIGHTREDTHEME)) {
            setTheme(R.style.CustomStyle_RedLightTheme);
        } else if (theme.equals(MainFragment.DARKREDTHEME)) {
            setTheme(R.style.CustomStyle_RedDarkTheme);
        } else if (theme.equals(MainFragment.LIGHTYELLOWTHEME)) {
            setTheme(R.style.CustomStyle_YellowLightTheme);
        } else if (theme.equals(MainFragment.DARKYELLOWTHEME)) {
            setTheme(R.style.CustomStyle_YellowDarkTheme);
        } else if (theme.equals(MainFragment.LIGHTGREENTHEME)) {
            setTheme(R.style.CustomStyle_GreenLightTheme);
        } else if (theme.equals(MainFragment.DARKGREENTHEME)) {
            setTheme(R.style.CustomStyle_GreenDarkTheme);
        } else if (theme.equals(MainFragment.LIGHTBLUETHEME)) {
            setTheme(R.style.CustomStyle_BlueLightTheme);
        } else if (theme.equals(MainFragment.DARKBLUETHEME)) {
            setTheme(R.style.CustomStyle_BlueDarkTheme);
        } else if (theme.equals(MainFragment.LIGHTPINKTHEME)) {
            setTheme(R.style.CustomStyle_PinkLightTheme);
        } else if (theme.equals(MainFragment.DARKPINKTHEME)) {
            setTheme(R.style.CustomStyle_PinkDarkTheme);
        }
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_add_to_do;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return AddToDoFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

