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

/**
 *  This is Fragment class which is used with CustomDialogActivity to display the form for creating a category
 */
public class CustomDialogFragment extends AppDefaultFragment {

    // declare private fields
    private Button categoryCreate;
    private EditText categoryEditText;
    private CategoryItem categoryItem;
    private String categoryTitle;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get the category item from MainFragment from the Intent
        categoryItem = (CategoryItem) getActivity().getIntent().getSerializableExtra("category");

        // Find and create the button and edit text for the form
        categoryCreate = (Button) view.findViewById(R.id.categoryButton);
        categoryEditText = (EditText) view.findViewById(R.id.categoryTitle);

        // listener for the Create Category button
        categoryCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if the user doesn't enter a title, give alert message
                if (categoryEditText.length() <= 0)
                {
                    categoryEditText.setError("Please enter a Category Title");
                }

                // else user entered a title, create the task and finish the activity
                else {
                    makeResult(RESULT_OK);
                    getActivity().finish();
                }

                hideKeyboard(categoryEditText);

            }
        });

        // Listener for the edit text field
        categoryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // Save whatever the user types in the String
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

    /**
     * Creates the Category object and sends the Category object back to MainFragment with Intent
     * @param result - result of the task
     */
    public void makeResult(int result){

        // Declare new intent
        Intent i = new Intent();

        // set the title to whatever the user inputted
        categoryItem.setTitle(categoryTitle);

        // put the Category object into the intent
        i.putExtra("category",categoryItem);

        // finish the activity by setting the result
        getActivity().setResult(result,i);

    }

    /**
     * Method that is simply used for hiding the keyboard when the user is done typing.
     * @param et - the EditText from which to hide the keyboard
     */
    public void hideKeyboard(EditText et) {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
}
