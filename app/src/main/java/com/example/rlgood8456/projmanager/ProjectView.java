package com.example.managerv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ProjectView extends AppCompatActivity{
    FloatingActionButton fab;

  //      private RecyclerView recyclerView;
   //     private RecyclerView.Adapter mAdapter;
    //    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        Button noteButton = findViewById(R.id.addNoteButton);
        noteButton.setOnClickListener(listener);

        TextView pName = (TextView) findViewById(R.id.project_view_name);
        pName.setText("Project Title: " + MainActivity.selectedProject.getProjectName());

        TextView pDesc = (TextView) findViewById(R.id.project_view_desc);
        pDesc.setText("Project Description: " + MainActivity.selectedProject.getProjectDescription());

        TextView pMem = findViewById(R.id.membersView);
        pMem.setText("Project Members: " + MainActivity.selectedProject.getContacts());

        fab = findViewById(R.id.openNoteButton);

        if(MainActivity.selectedProject.getNote() == "")
            fab.hide();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.show();
                OpenNotesDialog dialog = new OpenNotesDialog();
                dialog.show(getSupportFragmentManager(), "Open Notes Dialog");

            }
        });

        // The adapter knows how to create list item views for each item
        // in the list.
        TaskAdapter taskAdapter = new TaskAdapter(this, MainActivity.selectedProject.getTasks());

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.tasks_listview);
        listView.setAdapter(taskAdapter);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fab.show();
            CreateNotesDialog dialog = new CreateNotesDialog();
            dialog.show(getSupportFragmentManager(), "Create Notes Dialog");

        }
    };


}
