package com.example.avjindersinghsekhon.minimaltodo.Settings;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
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
        if (theme.equals(MainFragment.LIGHTTHEME)) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
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
        int option = getSharedPreferences("my_settings", Context.MODE_PRIVATE).getInt("sel_option", 0);
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

    @SuppressLint("NonConstantResourceId")
    public void onThemeButtonClicked(View view) {
        // Check if the button has been checked
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.themeRed:
                if (checked) {
                    //set theme to red

                    //set this button as checked
                    getSharedPreferences("my_settings", Context.MODE_PRIVATE).edit().putInt("sel_option", 0).apply();
                }
                break;
            case R.id.themeYellow:
                if (checked) {
                    //set theme to yellow

                    //set this button as checked
                    getSharedPreferences("my_settings", Context.MODE_PRIVATE).edit().putInt("sel_option", 1).apply();
                }
                break;
            case R.id.themeGreen:
                if (checked) {
                    //set theme to green

                    //set this button as checked
                    getSharedPreferences("my_settings", Context.MODE_PRIVATE).edit().putInt("sel_option", 2).apply();

                }
                break;
            case R.id.themeBlue:
                if (checked) {
                    //set theme to blue

                    //set this button as checked
                    getSharedPreferences("my_settings", Context.MODE_PRIVATE).edit().putInt("sel_option", 3).apply();
                }
                break;
            case R.id.themePink:
                if (checked) {
                    //set theme to pink

                    //set this button as checked
                    getSharedPreferences("my_settings", Context.MODE_PRIVATE).edit().putInt("sel_option", 4).apply();
                }
                break;
            case R.id.themeGrey:
                if (checked) {
                    //set theme to grey

                    //set this button as checked
                    getSharedPreferences("my_settings", Context.MODE_PRIVATE).edit().putInt("sel_option", 5).apply();
                }
                break;
        }
    }
}
