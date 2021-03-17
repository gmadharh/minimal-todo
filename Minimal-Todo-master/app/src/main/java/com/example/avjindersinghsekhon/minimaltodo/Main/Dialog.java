package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.EditText;

import com.example.avjindersinghsekhon.minimaltodo.Utility.CategoryItem;

public class Dialog extends AppCompatDialogFragment {

    private CategoryItem category;
    private EditText editTextBox;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        editTextBox = new EditText(getActivity());
        editTextBox.setHint("Enter Category Title");



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Category")
                .setPositiveButton ("Create Category", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        category = new CategoryItem();

                        if(editTextBox.getText().toString().length() <= 0)
                        {
                            editTextBox.setError("Please enter a Category Title");
                            System.out.println("EMPTY CATEGORY");
                            System.out.println(editTextBox.getText());
                            System.out.println(editTextBox.getText().toString());
                            System.out.println(editTextBox.getText().toString().length());
                        }
                        else {
                            Intent in = new Intent();
                            category.setTitle(editTextBox.getText().toString());
                            System.out.println("NON EMPTY CATEGORY");
                            System.out.println(editTextBox.getText());
                            System.out.println(editTextBox.getText().toString());
                            System.out.println(editTextBox.getText().toString().length());

                            in.putExtra(MainFragment.TODOITEM,category);

                        }
                    }
                });

        builder.setView(editTextBox);

        return builder.create();
    }



}
