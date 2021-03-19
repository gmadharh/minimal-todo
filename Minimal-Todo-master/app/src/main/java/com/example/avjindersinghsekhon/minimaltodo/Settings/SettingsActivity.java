package com.example.avjindersinghsekhon.minimaltodo.Settings;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.avjindersinghsekhon.minimaltodo.Analytics.AnalyticsApplication;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    AnalyticsApplication app;

    @Override
    protected void onResume() {
        super.onResume();
        app.send(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app = (AnalyticsApplication) getApplication();
        String theme = getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);

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

        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if (backArrow != null) {
            backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.mycontent, new SettingsFragment()).commit();


        /******* Radio buttons for Theme Change *******/
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.ThemeButtons);

        //Add all radio buttons to a list
        List<RadioButton> themeButtons = new ArrayList();
        themeButtons.add((RadioButton) rGroup.findViewById(R.id.themeRed));
        themeButtons.add((RadioButton) rGroup.findViewById(R.id.themeYellow));
        themeButtons.add((RadioButton) rGroup.findViewById(R.id.themeGreen));
        themeButtons.add((RadioButton) rGroup.findViewById(R.id.themeBlue));
        themeButtons.add((RadioButton) rGroup.findViewById(R.id.themePink));
        themeButtons.add((RadioButton) rGroup.findViewById(R.id.themeGrey));

        //Find the one that is clicked
        int option = getSharedPreferences("theme", Context.MODE_PRIVATE).getInt("sel_option", 0);
        if(option < 0 || option > 5) { //If the user has not clicked any yet, it will check grey which is the default colour
            option = 5;
        }

        //Set checked for the specific button
        themeButtons.get(option).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onThemeButtonClicked(View view) {
        // Check if the button has been checked
        boolean checked = ((RadioButton) view).isChecked();
        SharedPreferences themePreferences = getSharedPreferences(MainFragment.THEME_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor themeEditor = themePreferences.edit();
        String currentTheme = getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
        boolean lightMode = false;
        String theme = "";

        if(currentTheme.equals(MainFragment.LIGHTTHEME) || currentTheme.equals(MainFragment.LIGHTREDTHEME) || currentTheme.equals(MainFragment.LIGHTYELLOWTHEME) || currentTheme.equals(MainFragment.LIGHTGREENTHEME) || currentTheme.equals(MainFragment.LIGHTBLUETHEME) || currentTheme.equals(MainFragment.LIGHTPINKTHEME)) {
            lightMode = true;
        }  else {
            lightMode = false;
        }

        if(checked) {
            switch(view.getId()) {
                case R.id.themeRed:
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("sel_option", 0).apply();
                    if(lightMode) {
                        theme = MainFragment.LIGHTREDTHEME;
                    } else {
                        theme = MainFragment.DARKREDTHEME;
                    }
                    break;
                case R.id.themeYellow:
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("sel_option", 1).apply();
                    if(lightMode) {
                        theme = MainFragment.LIGHTYELLOWTHEME;
                    } else {
                        theme = MainFragment.DARKYELLOWTHEME;
                    }
                    break;
                case R.id.themeGreen:
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("sel_option", 2).apply();
                    if(lightMode) {
                        theme = MainFragment.LIGHTGREENTHEME;
                    } else {
                        theme = MainFragment.DARKGREENTHEME;
                    }
                    break;
                case R.id.themeBlue:
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("sel_option", 3).apply();
                    if(lightMode) {
                        theme = MainFragment.LIGHTBLUETHEME;
                    } else {
                        theme = MainFragment.DARKBLUETHEME;
                    }
                    break;
                case R.id.themePink:
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("sel_option", 4).apply();
                    if(lightMode) {
                        theme = MainFragment.LIGHTPINKTHEME;
                    } else {
                        theme = MainFragment.DARKPINKTHEME;
                    }
                    break;
                case R.id.themeGrey:
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("sel_option", 5).apply();
                    if(lightMode) {
                        theme = MainFragment.LIGHTTHEME;
                    } else {
                        theme = MainFragment.DARKTHEME;
                    }
                    break;
            }
            themeEditor.putBoolean(MainFragment.RECREATE_ACTIVITY, true);
            themeEditor.putString(MainFragment.THEME_SAVED, theme);
            themeEditor.apply();
            //We tell our MainLayout to recreate itself because mode has changed
            recreate();
        }
    }
}
