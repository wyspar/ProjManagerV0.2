//change package after downloading
package ung.appdev.projectManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Button;

import com.example.rlgood8456.projmanager.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int numProjects;
    public static ArrayList<Project> projects;
    public static Project selectedProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an ArrayList of projects
        projects = new ArrayList<Project>();
        ArrayList<String> people = new ArrayList<>();
        people.add("Person1");
        people.add("Person2");
        people.add("Person3");
        people.add("Add people to a project.");

        //Adds in a sample project, tell user what stuff is -DR
        Project p1 = new Project("Sample Project", "Your first project.",people);
        p1.addTask("Tap the project name to change it");
        p1.addTask("Green means complete");
        p1.addTask("Tap me to change it to complete");
        p1.getTasks().get(1).setComplete(true);
        projects.add(p1);

        Button createProjectButton = findViewById(R.id.addProjectButton);
        createProjectButton.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View view) {
                CreateProjectDialog cpd = new CreateProjectDialog();
                cpd.show(getSupportFragmentManager(), "Create Project Dialog");
            }

        });

        // The adapter knows how to create list item views for each item
        // in the list.
        ProjectAdapter projAdapter = new ProjectAdapter(this, projects);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_projects);
        listView.setAdapter(projAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedProject = projects.get(i);

                Intent viewProject = new Intent(MainActivity.this, ProjectView.class);

                startActivity(viewProject);


            }
        });
    }
}


