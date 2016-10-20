package containers;

import resourse.PageResourseRestObject;

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
        this.uri = PageResourseRestObject.URI + "/" + id;
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
}
