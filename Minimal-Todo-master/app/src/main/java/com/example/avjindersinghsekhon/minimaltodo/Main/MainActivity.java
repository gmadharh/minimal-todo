package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.avjindersinghsekhon.minimaltodo.About.AboutActivity;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Settings.SettingsActivity;

public class MainActivity extends AppDefaultActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        String theme = getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
        if(theme.equals(MainFragment.LIGHTTHEME)) {
            setTheme(R.style.CustomStyle_LightTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryGrey));
        } else if (theme.equals(MainFragment.DARKTHEME)) {
            setTheme(R.style.CustomStyle_DarkTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary_darkGrey));
        } else if (theme.equals(MainFragment.LIGHTREDTHEME)) {
            setTheme(R.style.CustomStyle_RedLightTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryRed));
        } else if (theme.equals(MainFragment.DARKREDTHEME)) {
            setTheme(R.style.CustomStyle_RedDarkTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary_darkRed));
        } else if (theme.equals(MainFragment.LIGHTYELLOWTHEME)) {
            setTheme(R.style.CustomStyle_YellowLightTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryYellow));
        } else if (theme.equals(MainFragment.DARKYELLOWTHEME)) {
            setTheme(R.style.CustomStyle_YellowDarkTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary_darkYellow));
        } else if (theme.equals(MainFragment.LIGHTGREENTHEME)) {
            setTheme(R.style.CustomStyle_GreenLightTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryGreen));
        } else if (theme.equals(MainFragment.DARKGREENTHEME)) {
            setTheme(R.style.CustomStyle_GreenDarkTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary_darkGreen));
        } else if (theme.equals(MainFragment.LIGHTBLUETHEME)) {
            setTheme(R.style.CustomStyle_BlueLightTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryBlue));
        } else if (theme.equals(MainFragment.DARKBLUETHEME)) {
            setTheme(R.style.CustomStyle_BlueDarkTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary_darkBlue));
        } else if (theme.equals(MainFragment.LIGHTPINKTHEME)) {
            setTheme(R.style.CustomStyle_PinkLightTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryPink));
        } else if (theme.equals(MainFragment.DARKPINKTHEME)) {
            setTheme(R.style.CustomStyle_PinkDarkTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary_darkPink));
        }

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return MainFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutMeMenuItem:
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return true;
//            case R.id.switch_themes:
//                if(mTheme == R.style.CustomStyle_DarkTheme){
//                    addThemeToSharedPreferences(LIGHTTHEME);
//                }
//                else{
//                    addThemeToSharedPreferences(DARKTHEME);
//                }
//
////                if(mTheme == R.style.CustomStyle_DarkTheme){
////                    mTheme = R.style.CustomStyle_LightTheme;
////                }
////                else{
////                    mTheme = R.style.CustomStyle_DarkTheme;
////                }
//                this.recreate();
//                return true;
            case R.id.preferences:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


