package com.example.avjindersinghsekhon.minimaltodo.About;

import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.avjindersinghsekhon.minimaltodo.Analytics.AnalyticsApplication;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.content.Context.MODE_PRIVATE;

public class AboutActivity extends AppDefaultActivity {

    private TextView mVersionTextView;
    private String appVersion = "0.1";
    private Toolbar toolbar;
    private TextView contactMe;
    String theme;
    //    private UUID mId;
    private AnalyticsApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        theme = getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);

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
//        mId = (UUID)i.getSerializableExtra(TodoNotificationService.TODOUUID);

        final Drawable backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if (backArrow != null) {
            backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.about_layout;
    }

    @NonNull
    protected Fragment createInitialFragment() {
        return AboutFragment.newInstance();
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
}
