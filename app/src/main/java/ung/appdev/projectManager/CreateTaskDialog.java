package ung.appdev.projectManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rlgood8456.projmanager.R;

public class CreateTaskDialog extends DialogFragment{


    View view;

    public CreateTaskDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {


        AlertDialog.Builder builder=  new  AlertDialog.Builder(getActivity())
                .setTitle(R.string.add_task_dialog_title)
                .setPositiveButton(R.string.create_task_confirm_button_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText titleText = (EditText)view.findViewById(R.id.taskTitleInput);

                                String title = titleText.getText().toString();

                                MainActivity.selectedProject.addTask(title);

                                ListView layout = (ListView) getActivity().findViewById(R.id.tasks_listview);
                                layout.invalidate();
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

        view = getActivity().getLayoutInflater().inflate(R.layout.create_task_dialog, null);

        builder.setView(view);

        return builder.create();

    }

}


