package testconfigs;

import org.junit.Before;

import static resourses.TasksRest.resetBase;

public class BaseTest {

    @Before
    public void resetRestfulBase() {
        resetBase();
    }
}
