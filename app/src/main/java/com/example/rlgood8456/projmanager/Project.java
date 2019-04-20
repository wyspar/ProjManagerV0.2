public class Project {

    private String projectName;
    private String projectDescription;
    private String note = "";
    private ArrayList<String> projectMembers;

    // ArrayList of Tasks
    private ArrayList<Task> tasks;


    public Project() {
        projectName = "Project";
        projectDescription = "Summary";
        projectMembers = new ArrayList<>();
        tasks = new ArrayList<Task>();
    }

    public Project(String pName, String pDesc, List<String> contacts)

    {
        projectName = pName;
        projectDescription = pDesc;
        tasks = new ArrayList<Task>();
        projectMembers = new ArrayList<String>();

        for(int i = 0; i < contacts.size();){

            projectMembers.add(contacts.get(i));
            i++;
        }
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

    public String getContacts()
    {
        String members = "";

        for(int i = 0; i < projectMembers.size();){
            members += projectMembers.get(i) + ", ";
            i++;
        }

        return members;
    }

    public void setNote(String text){
        note = text;
    }

    public String getNote(){
        return note;
    }
}



