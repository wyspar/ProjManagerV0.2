package com.example.rlgood8456.projmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CreateProjectDialog extends DialogFragment{

    View view;

    public CreateProjectDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {








        AlertDialog.Builder builder=  new  AlertDialog.Builder(getActivity())
                .setTitle(R.string.add_project_dialog_title)
                .setPositiveButton(R.string.create_project_confirm_button_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText titleText = (EditText)view.findViewById(R.id.projectTitleInput);
                                EditText descText = (EditText)view.findViewById(R.id.projectDescriptionInput);

                                String title = titleText.getText().toString();
                                String desc = descText.getText().toString();

                                Project p1 = new Project(title, desc);
                                MainActivity.projects.add(p1);

                                ListView layout = (ListView) getActivity().findViewById(R.id.listview_projects);
                                layout.invalidate();
                        //        TextView tv = new TextView(getContext());
                         //       TextView tv2 = new TextView(getContext());
                          //      tv.setText(p1.getProjectName());
                           //     tv2.setText(p1.getProjectDescription());
                            //    layout.addView(tv);
                             //   layout.addView(tv2);








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

        view = getActivity().getLayoutInflater().inflate(R.layout.create_project_dialog, null);

        builder.setView(view);

        return builder.create();


    }




}


