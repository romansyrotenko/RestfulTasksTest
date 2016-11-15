package resourses;

import containers.ErrorContainer;
import containers.Task;
import containers.TaskContainer;
import containers.TasksContainer;
import core.Configuration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;

public class TasksRest {

    public static String uri = Configuration.baseUrl + "todo/api/v1.0/tasks";
    private static Response response;

    public static Invocation.Builder requestTo(String uri) {
        return ClientBuilder.newClient().target(uri).request();
    }

    public static Invocation.Builder authorized(Invocation.Builder requestBuilder) {
        return requestBuilder.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("miguel:python".getBytes()));
    }

    public static void getUnauthorizedResponseAccess() {
        response =  requestTo(uri).get();
    }

    public static void getAuthorizedResponceAccess() {
        response =  authorized(requestTo(uri)).get();
    }

    public static void resetBase() {
        authorized(requestTo(uri + "/reset")).get();
    }

    public static String getResponseAnswer() {
        return response.readEntity(ErrorContainer.class).getError();
    }

    public static int getResponseStatus() {
        return response.getStatus();
    }

    public static Task getResponseTask() {
        return response.readEntity(TaskContainer.class).getTask();
    }

    public static List<Task> getTasks() {
        getAuthorizedResponceAccess();
        return response.readEntity(TasksContainer.class).getTasks();
    }

    public static int getTasksSize() {
        return getTasks().size();
    }

    public static List<Task> get() {
        return getTasks();
    }

    public static Task getTask(int id) {
        return getTasks().get(id);
    }

    public static void createTask(Task newTask) {
        response = authorized(requestTo(uri)).post(Entity.entity(newTask, MediaType.APPLICATION_JSON));
    }

    public static void deleteTask(int id) {
        response = authorized(requestTo(uri + "/" + id)).delete();
    }

    public static void updateTask(Task task) {
        response = authorized(requestTo(task.getUri())).put(Entity.entity(task, MediaType.APPLICATION_JSON));
    }

}
