package com.example.rlgood8456.projmanager;

import java.util.ArrayList;

public class Project {

    private String projectName;
    private String projectDescription;

    // ArrayList of Tasks
    private ArrayList<Task> tasks;


    public Project() {
        projectName = "Project";
        projectDescription = "Summary";
        tasks = new ArrayList<Task>();
    }

    public Project(String pName, String pDesc)

    {
        projectName = pName;
        projectDescription = pDesc;
        tasks = new ArrayList<Task>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void addTask(String taskName){
        tasks.add(new Task(taskName));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}


