package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class CategoryView extends AppDefaultActivity {

    private Toolbar toolbar;

    /**
     * onCreate method
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //set the page's theme
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
        setContentView(R.layout.activity_category);

        //set the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add a back button to the toolbar
        final Drawable backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if (backArrow != null) {
            backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }

        //set up the back button to navigate to the previous page
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * Get the design for the form from the res/XML
     * @return
     */
    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_category;
    }

    /**
     * @return Fragment
     */
    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return CategoryViewFragment.newInstance();
    }

    /**
     * onResume method
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

}
