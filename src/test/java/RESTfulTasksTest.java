import containers.ErrorContainer;
import containers.Task;
import org.junit.Test;
import resourses.TasksApi;
import testconfigs.BaseTest;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static resourses.TasksApi.*;

public class RESTfulTasksTest extends BaseTest {

    Task[] DEFAULT_TASKS = {new Task("This is my first task", false, "First task", 1),
                            new Task("This is my second task", false, "Second task", 2)};


    @Test
    public void testUnauthorizedReadTasks() {
        Response response = requestTo(TasksApi.uri).get();

        assertEquals(403, response.getStatus());
        assertEquals("Unauthorized access", response.readEntity(ErrorContainer.class).getError());
    }

    @Test
    public void testReadTasks() {

        assertTasks(DEFAULT_TASKS);
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task("This is My new Third task", false, "Third task", 3);

        createTask(newTask);

        assertTasks(DEFAULT_TASKS[0], DEFAULT_TASKS[1], newTask);
    }

    @Test
    public void testDelete() {
        deleteTask(1);

        assertTasks(DEFAULT_TASKS[1]);
    }

    @Test
    public void testUpdate() {
        Task updatedTask = new Task("this description was updated", true, "task was updated", 1);

        updateTask(updatedTask);

        assertTasks(updatedTask, DEFAULT_TASKS[1]);
    }

    @Test
    public void testCreateUpdateDelete() {
        Task newTask = new Task("My new super shiny task", false, "Third task", 3);
        Task updatedTask = new Task("My new super shiny updated task", true, "shiny task was updated", 1);

        createTask(newTask);
        updateTask(updatedTask);
        deleteTask(2);

        assertTasks(updatedTask, newTask);
    }

}
