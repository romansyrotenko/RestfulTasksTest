package containers;

import resourses.TasksApi;

public class Task {

    String description;
    boolean done;
    String title;
    String uri;

    public Task() {
    }

    public Task (String description, boolean done, String title, int id) {
        this.description = description;
        this.done = done;
        this.title = title;
        this.uri = TasksApi.uri + "/" + id;
    }

    public Task(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", done=" + done +
                ", title='" + title + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (done != task.done) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        return uri != null ? uri.equals(task.uri) : task.uri == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (done ? 1 : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }
}
