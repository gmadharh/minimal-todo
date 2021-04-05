package com.example.avjindersinghsekhon.minimaltodo.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.avjindersinghsekhon.minimaltodo.Analytics.AnalyticsApplication;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Utility.PreferenceKeys;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    AnalyticsApplication app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_layout);
        app = (AnalyticsApplication) getActivity().getApplication();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PreferenceKeys preferenceKeys = new PreferenceKeys(getResources());
        if (key.equals(preferenceKeys.night_mode_pref_key)) {
            SharedPreferences themePreferences = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor themeEditor = themePreferences.edit();

            //We tell our MainLayout to recreate itself because mode has changed
            themeEditor.putBoolean(MainFragment.RECREATE_ACTIVITY, true);

            //find whether or not dark mode is selected
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(preferenceKeys.night_mode_pref_key);

            //get the id of the selected theme radio button
            int themeID = getActivity().getSharedPreferences("theme", Context.MODE_PRIVATE).getInt("sel_option", 0);

            //set the theme based off the radio button selected and the dark mode button
            switch (themeID) {
                case 0: //set to red
                    if (checkBoxPreference.isChecked()) {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKREDTHEME);
                    } else {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTREDTHEME);
                    }
                    break;
                case 1: //set to yellow
                    if (checkBoxPreference.isChecked()) {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKYELLOWTHEME);
                    } else {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTYELLOWTHEME);
                    }
                    break;
                case 2: //set to green
                    if (checkBoxPreference.isChecked()) {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKGREENTHEME);
                    } else {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTGREENTHEME);
                    }
                    break;
                case 3: //set to blue
                    if (checkBoxPreference.isChecked()) {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKBLUETHEME);
                    } else {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTBLUETHEME);
                    }
                    break;
                case 4: //set to pink
                    if (checkBoxPreference.isChecked()) {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKPINKTHEME);
                    } else {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTPINKTHEME);
                    }
                    break;
                case 5: //set to grey
                    if (checkBoxPreference.isChecked()) {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKTHEME);
                    } else {
                        themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
                    }
            }

            //apply the changes
            themeEditor.apply();

            //recreate the activity to update the theme
            getActivity().recreate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
