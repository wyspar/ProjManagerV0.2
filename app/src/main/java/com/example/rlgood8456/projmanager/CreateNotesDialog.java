package com.example.managerv2;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class CreateNotesDialog extends DialogFragment {

    View view;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    public CreateNotesDialog(){

    }

    @Override
    public Dialog onCreateDialog(Bundle bundle){

        builder = new  AlertDialog.Builder(getActivity())
                .setTitle("Create New Note")
                .setPositiveButton("Done",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText note = view.findViewById(R.id.noteLongDescription);
                                String noteDesc = note.getText().toString();
                                MainActivity.selectedProject.setNote(noteDesc);

                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        view = getActivity().getLayoutInflater().inflate(R.layout.create_notes_dialog, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return dialog;
    }

}
