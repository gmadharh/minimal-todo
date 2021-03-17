package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class CustomDialogActivity extends AppDefaultActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_dialog;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return CustomDialogFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
