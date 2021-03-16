package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.EditText;

public class Dialog extends AppCompatDialogFragment {

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Category")
                .setPositiveButton ("Create Category", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        EditText editTextBox = new EditText(getActivity());

        editTextBox.setHint("Enter Category Title");


        builder.setView(editTextBox);
        

        return builder.create();
    }
}
