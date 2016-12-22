package resourses;

import containers.Task;
import containers.TasksContainer;
import core.Configuration;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static core.RestHelpers.authorizedRequest;
import static junit.framework.TestCase.assertEquals;

public class TasksApi {

    public static String uri = Configuration.baseUrl + "todo/api/v1.0/tasks";

    public static List<Task> get() {
        Response response =  authorizedRequest(uri).get();
        assertEquals(200, response.getStatus());
        return response.readEntity(TasksContainer.class).getTasks();
    }

    public static void reset() {
        authorizedRequest(uri + "/reset").get();
    }

    public static Response create(Task task) {
        Response response = authorizedRequest(uri).post(Entity.entity(task, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        return response;
    }

    public static Response delete(int id) {
        Response response = authorizedRequest(uri + "/" + id).delete();
        assertEquals(200, response.getStatus());
        return response;
    }

    public static Response update(Task task) {
        Response response = authorizedRequest(task.getUri()).put(Entity.entity(task, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
        return response;
    }

    public static void assertTasks(Task... tasks) {
        assertEquals(Arrays.asList(tasks), get());
    }
}
