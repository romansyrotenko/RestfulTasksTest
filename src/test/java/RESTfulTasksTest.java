import containers.ErrorContainer;
import containers.Task;
import org.junit.Before;
import org.junit.Test;
import resourses.TasksApi;

import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

import static core.RestHelpers.requestTo;
import static junit.framework.TestCase.assertEquals;
import static resourses.TasksApi.*;

public class RESTfulTasksTest {

    Task[] DEFAULT_TASKS = {new Task("This is my first task", false, "First task", 1, TasksApi.uri),
                            new Task("This is my second task", false, "Second task", 2, TasksApi.uri)};

    @Before
    public void resetRestfulBase() {
        reset();
    }

    @Test
    public void testUnauthorizedReadTasks() {
        Response response = requestTo(TasksApi.uri).get();

        assertEquals(403, response.getStatus());
        assertEquals("Unauthorized access", response.readEntity(ErrorContainer.class).getError());
    }

    @Test
    public void testReadTasks() {
        List<Task> tasks = get();

        assertEquals(tasks, Arrays.asList(DEFAULT_TASKS));
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task("This is My new Third task", false, "Third task", 3, TasksApi.uri);

        create(newTask);
        assertTasks(DEFAULT_TASKS[0], DEFAULT_TASKS[1], newTask);
    }

    @Test
    public void testCreateNotFilledTask() {
        Task newTask = new Task("Not filled Task");

        create(newTask);

        Task thirdTask = new Task(null, false, "Not filled Task", 3, TasksApi.uri);

        assertTasks(DEFAULT_TASKS[0], DEFAULT_TASKS[1], thirdTask);
    }

    @Test
    public void testDeleteTask() {
        delete(1);

        assertTasks(DEFAULT_TASKS[1]);
    }

    @Test
    public void testUpdateTask() {
        Task updatedTask = new Task("this description was updated", true, "task was updated", 1, TasksApi.uri);

        update(updatedTask);

        assertTasks(updatedTask, DEFAULT_TASKS[1]);
    }

    @Test
    public void testCreateUpdateDelete() {
        Task newTask = new Task("My new super shiny task", false, "Third task", 3, TasksApi.uri);

        create(newTask);
        assertTasks(DEFAULT_TASKS[0], DEFAULT_TASKS[1], newTask);

        Task updatedTask = new Task("My new super shiny updated task", true, "shiny task was updated", 1, TasksApi.uri);

        update(updatedTask);
        assertTasks(updatedTask, DEFAULT_TASKS[1], newTask);

        delete(2);
        assertTasks(updatedTask, newTask);
    }

}
