package juliangarcia.twoadelivery;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends InstrumentationTestCase {
    public ApplicationTest() {

    }

    public void testSimplePass() throws Exception {

        assertEquals(true, true);
    }

    public void testSimpleFail() throws Exception {
        final int expected = 1,
                reality = 5;
        assertEquals(expected, reality);
    }
}