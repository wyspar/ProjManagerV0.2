package com.example.rlgood8456.projmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

public class ProjectView extends AppCompatActivity{



  //      private RecyclerView recyclerView;
   //     private RecyclerView.Adapter mAdapter;
    //    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        TextView pName = (TextView) findViewById(R.id.project_view_name);
        pName.setText(MainActivity.selectedProject.getProjectName());

        TextView pDesc = (TextView) findViewById(R.id.project_view_desc);
        pDesc.setText(MainActivity.selectedProject.getProjectDescription());

        // The adapter knows how to create list item views for each item
        // in the list.
        TaskAdapter taskAdapter = new TaskAdapter(this, MainActivity.selectedProject.getTasks());

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.tasks_listview);
        listView.setAdapter(taskAdapter);


    }

}
