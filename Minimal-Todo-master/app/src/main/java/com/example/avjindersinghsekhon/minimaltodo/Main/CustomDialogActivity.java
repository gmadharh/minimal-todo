package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

/**
 * Activity class for CustomDialogFragment
 * Acts as a wrapper for the fragment
 * This is used to open the form for collecting and creating a Category object
 */
public class CustomDialogActivity extends AppDefaultActivity {

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Get the design for the form from the res/XML
     * @return
     */
    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_dialog;
    }

    /**
   * @return Fragment
     */
    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return CustomDialogFragment.newInstance();
    }

    /**
     * onResume method
     */
    @Override
    protected void onResume() {
        super.onResume();
    }
}
