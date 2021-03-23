package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class CategoryView extends AppDefaultActivity {

    /**
     * onCreate method
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
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
