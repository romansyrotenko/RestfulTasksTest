package resourses;

import containers.Task;
import containers.TasksContainer;
import core.Configuration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TasksApi {

    public static String uri = Configuration.baseUrl + "todo/api/v1.0/tasks";

    public static Invocation.Builder requestTo(String uri) {
        return ClientBuilder.newClient().target(uri).request();
    }

    public static Invocation.Builder authorized(Invocation.Builder requestBuilder) {
        return requestBuilder.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("miguel:python".getBytes()));
    }

    public static List<Task> get() {
        Response response =  authorized(requestTo(uri)).get();
        assertEquals(200, response.getStatus());
        return response.readEntity(TasksContainer.class).getTasks();
    }

    public static void resetBase() {
        authorized(requestTo(uri + "/reset")).get();
    }

    public static void createTask(Task newTask) {
        Response response = authorized(requestTo(uri)).post(Entity.entity(newTask, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }

    public static Response deleteTask(int id) {
        Response response = authorized(requestTo(uri + "/" + id)).delete();
        assertEquals(200, response.getStatus());
        return response;
    }

    public static void updateTask(Task task) {
        Response response = authorized(requestTo(task.getUri())).put(Entity.entity(task, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }

    public static void assertTasks(Task... tasks) {
        assertEquals(Arrays.asList(tasks), get());
    }

}
