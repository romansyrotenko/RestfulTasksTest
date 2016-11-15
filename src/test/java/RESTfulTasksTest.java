import containers.ErrorContainer;
import containers.Task;
import org.junit.Test;
import resourses.TasksRest;
import testconfigs.BaseTest;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static resourses.TasksRest.*;

public class RESTfulTasksTest extends BaseTest {

    String[] DEFAULT_TASKS = {"Task{description='This is my first task', done=false, title='First task', uri='http://localhost:5000/todo/api/v1.0/tasks/1'}",
                              "Task{description='This is my second task', done=false, title='Second task', uri='http://localhost:5000/todo/api/v1.0/tasks/2'}"};

    @Test
    public void testUnauthorizedReadTasks() {
        Response response = requestTo(TasksRest.uri).get();

        assertEquals("Unauthorized access", response.readEntity(ErrorContainer.class).getError());
        assertEquals(403, getResponseStatus());
    }

    @Test
    public void testReadTasks() {
        Response response =  authorized(requestTo(uri)).get();

        assertEquals(200, response.getStatus());

        List<Task> actualTasks = TasksRest.get();
        assertEquals(Arrays.asList(DEFAULT_TASKS), actualTasks);
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task("My third task");
        createTask(newTask);

        assertEquals(201, getResponseStatus());
        assertEquals("My third task", getResponseTask().getTitle());
        assertEquals(3, getTasksSize());
    }

    @Test
    public void testDelete() {
        deleteTask(1);

        assertEquals(200, getResponseStatus());
        assertEquals(1, getTasksSize());
        assertEquals("Second task", getTask(0).getTitle());
    }

    @Test
    public void testUpdate() {
        int id = 1;
        Task task = new Task("this description was updated", true, "task was updated", id);
        updateTask(task);
        assertEquals(200, getResponseStatus());

        Task receivedTask = getResponseTask();

        assertEquals("this description was updated", receivedTask.getDescription());
        assertTrue(receivedTask.isDone());
        assertEquals("task was updated", receivedTask.getTitle());
        assertEquals(TasksRest.uri + "/" + id, receivedTask.getUri());
    }

    @Test
    public void testCreateUpdateDelete() {
        Task task = new Task("My new super shiny task");
        createTask(task);
        assertEquals(201, getResponseStatus());

        task = new Task("My new super shiny updated task", true, "shiny task was updated", 3);;
        updateTask(task);
        assertEquals(200, getResponseStatus());

        deleteTask(2);
        assertEquals(200, getResponseStatus());

        assertEquals(2, getTasksSize());
        assertEquals("First task", getTask(0).getTitle());
        assertEquals("shiny task was updated", getTask(1).getTitle());
    }

}
