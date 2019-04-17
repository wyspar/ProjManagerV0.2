package com.example.rlgood8456.projmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
        final TaskAdapter taskAdapter = new TaskAdapter(this, MainActivity.selectedProject.getTasks());

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) findViewById(R.id.tasks_listview);
        listView.setAdapter(taskAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Task t = MainActivity.selectedProject.getTasks().get(i);

                t.setComplete(!t.isComplete());

                taskAdapter.notifyDataSetInvalidated();






            }
        });



        Button createTaskButton = findViewById(R.id.add_task_button);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View view) {
                CreateTaskDialog ctd = new CreateTaskDialog();
                ctd.show(getSupportFragmentManager(), "Create Task Dialog");
            }

        });





    }

}
