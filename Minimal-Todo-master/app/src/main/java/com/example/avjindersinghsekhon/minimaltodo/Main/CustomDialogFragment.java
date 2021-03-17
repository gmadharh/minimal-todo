package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Utility.CategoryItem;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.app.Activity.RESULT_OK;

public class CustomDialogFragment extends AppDefaultFragment {

    private Button categoryCreate;
    private EditText categoryEditText;
    private CategoryItem categoryItem;
    private String categoryTitle;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryItem = (CategoryItem) getActivity().getIntent().getSerializableExtra("category");

        categoryCreate = (Button) view.findViewById(R.id.categoryButton);
        categoryEditText = (EditText) view.findViewById(R.id.categoryTitle);

        categoryCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (categoryEditText.length() <= 0)
                {
                    categoryEditText.setError("Please enter a Category Title");
                }
                else {
                    makeResult(RESULT_OK);
                    getActivity().finish();
                }

                hideKeyboard(categoryEditText);

            }
        });

        categoryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                categoryTitle = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public static Fragment newInstance() {
        return new CustomDialogFragment();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_dialog;
    }

    public void makeResult(int result){
        Intent i = new Intent();

        categoryItem.setTitle(categoryTitle);
        i.putExtra("category",categoryItem);
        getActivity().setResult(result,i);

    }

    public void hideKeyboard(EditText et) {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
}
