package web.ru.jira.models;

public class Task {
    //TODO: POJO

    private Integer id;
    private String project;
    private String type;
    private String title;
    private String description;

    public Task(String title, String type, String description, Integer id) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private enum TaskType {

        TASK("задача"),
        BUG("ошибка"),
        HISTORY("история"),
        EPIC("epic");

        private final String type;

        TaskType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
