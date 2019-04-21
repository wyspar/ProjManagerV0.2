package ung.appdev.projectManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rlgood8456.projmanager.R;

import java.util.ArrayList;

public class UpdateProjectDialog extends DialogFragment{

    private String projectName = "";
    private String projectDesc = "";
    private Project project;
    private TextView projectViewName;
    private TextView projectViewDesc;
    public static ArrayList<Project> projects;
    public MainActivity mainActivity;
    View view;

    public UpdateProjectDialog() {

    }

    public void setProjectName(String pName){
        this.projectName = pName;
    }
    public void setProjectDesc(String pDesc){
        this.projectDesc = pDesc;
    }
    public void setProject(Project project){
        this.project = project;
    }
    public void setProjectViewName(TextView view){
        this.projectViewName = view;
    }
    public void setProjectViewDesc(TextView view){
        this.projectViewDesc = view;
    }

    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder builder=  new  AlertDialog.Builder(getActivity())
                .setTitle(R.string.project_update_name)
                .setPositiveButton(R.string.project_update_name,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                EditText titleText = (EditText)view.findViewById(R.id.projectTitleInput);
                                EditText descText = (EditText)view.findViewById(R.id.projectDescriptionInput);
                                //TODO: save the updated name
                                String title = titleText.getText().toString();
                                String desc = descText.getText().toString();
                                project.setProjectDescription(desc);
                                project.setProjectName(title);

                                projectViewName.setText(title);
                                projectViewDesc.setText(desc);
                                //TODO:update the names on the activity main
//                                projects = mainActivity.getProjects();
//                                ProjectAdapter projAdapter = new ProjectAdapter(mainActivity, projects);
//                                ListView layout = (ListView) getActivity().findViewById(R.id.listview_projects);
//                                layout.invalidate();
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
        //show the project's current name, etc -dr


        view = getActivity().getLayoutInflater().inflate(R.layout.create_project_dialog, null);
        EditText titleText = (EditText)view.findViewById(R.id.projectTitleInput);
        EditText descText = (EditText)view.findViewById(R.id.projectDescriptionInput);
        titleText.setText(projectName);
        descText.setText(projectDesc);
        builder.setView(view);

        return builder.create();
    }//End of onCreateDialog






}


