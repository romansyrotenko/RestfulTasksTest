package testconfigs;

import org.junit.Before;

import static resourses.TasksApi.resetBase;

public class BaseTest {

    @Before
    public void resetRestfulBase() {
        resetBase();
    }
}
