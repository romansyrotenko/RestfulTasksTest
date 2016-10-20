package testconfigs;

import org.junit.Before;

import static resourse.PageResourseRestObject.resetBase;

public class BaseTest {

    @Before
    public void resetRestfulBase() {
        resetBase();
    }
}
