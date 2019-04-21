package ung.appdev.projectManager;

public class Task {

    private String taskName;
    private int taskNumber;
    private boolean complete;

    public Task(String taskName, int taskNumber) {
        //MainActivity.selectedProject.getTasks();
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        complete = false;
    }

    public int getTaskNumber(){
        return taskNumber;
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
