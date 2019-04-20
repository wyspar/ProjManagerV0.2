package com.example.managerv2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class OpenNotesDialog extends DialogFragment {

    View view;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    public OpenNotesDialog(){

    }

    @Override
    public Dialog onCreateDialog(Bundle bundle){

        builder = new  AlertDialog.Builder(getActivity())
                .setTitle("Note")
                .setPositiveButton("Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        view = getActivity().getLayoutInflater().inflate(R.layout.open_note_dialog, null);

        builder.setView(view);
        dialog = builder.create();

        TextView note = view.findViewById(R.id.noteText);
        TextView date = view.findViewById(R.id.dateText);
        note.setText(MainActivity.selectedProject.getNote());

        long currentDate = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(currentDate);
        date.setText("Date Created: " + dateString);

        return dialog;
    }
}
