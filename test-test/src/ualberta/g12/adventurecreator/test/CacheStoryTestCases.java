
package ualberta.g12.adventurecreator.test;

import android.test.ActivityInstrumentationTestCase2;

import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.tasks.CacheStoryTask;
import ualberta.g12.adventurecreator.views.FragmentViewActivity;

import java.util.concurrent.ExecutionException;

/**
 * This class of test cases tests the caching functionality, use case 9.
 */
public class CacheStoryTestCases extends ActivityInstrumentationTestCase2<FragmentViewActivity> {

    private CacheStoryTask cacheTask;

    public CacheStoryTestCases() {
        super(FragmentViewActivity.class);
    }

    /**
     * Performs the caching task and ensures that nothing has crashed during
     * this activity start.
     */
    public void testEnterActivity() throws Throwable {
        this.cacheTask = new CacheStoryTask(getInstrumentation().getContext());
        assertNotNull(cacheTask);

        final Story s = new Story("A", "A");

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                cacheTask.execute(new Story[] {
                        s
                });

                try {
                    if (cacheTask.get().contains("Error viewing")) {
                        // Couldn't open this activity
                        fail("Couldn't cache the story");
                    }
                } catch (InterruptedException e) {
                    fail("Interrupted exception while caching story");
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    fail("ExecutionException while caching story");
                    e.printStackTrace();
                }

                /*
                 * On Post execute won't end til we end this activity and
                 * activity will end after this is done probably
                 */
            }
        });
    }
}
