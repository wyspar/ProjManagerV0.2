package ung.appdev.projectManager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectView extends AppCompatActivity{
    FloatingActionButton fab;

  //      private RecyclerView recyclerView;
   //     private RecyclerView.Adapter mAdapter;
    //    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.task_options, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.task_option_delete:
                Toast.makeText(this,"Task Deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.task_option_edit:

                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        Button noteButton = findViewById(R.id.addNoteButton);
        noteButton.setOnClickListener(listener);

        final TextView pName = (TextView) findViewById(R.id.project_view_name);
        pName.setText("Project Title: " + MainActivity.selectedProject.getProjectName());

        final TextView pDesc = (TextView) findViewById(R.id.project_view_desc);
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
        final TaskAdapter taskAdapter = new TaskAdapter(this, MainActivity.selectedProject.getTasks());

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.tasks_listview);
        listView.setAdapter(taskAdapter);
        registerForContextMenu(listView);
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

        //change the name of the project -dr
        pName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProjectDialog dialog = new UpdateProjectDialog();
                dialog.setProject(MainActivity.selectedProject);
                dialog.setProjectName(MainActivity.selectedProject.getProjectName());
                dialog.setProjectDesc(MainActivity.selectedProject.getProjectDescription());
                dialog.setProjectViewName(pName);
                dialog.setProjectViewDesc(pDesc);
                dialog.show(getSupportFragmentManager(), "Update Task Dialog");
            }
        });
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
