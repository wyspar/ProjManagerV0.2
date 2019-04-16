package com.example.rlgood8456.projmanager;

public class Task {

    private String taskName;
    private boolean complete;

    public Task(String taskName) {
        this.taskName = taskName;
        complete = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
