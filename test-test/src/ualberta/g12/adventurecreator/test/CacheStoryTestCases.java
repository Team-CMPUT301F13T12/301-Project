
package ualberta.g12.adventurecreator.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.tasks.CacheStoryTask;
import ualberta.g12.adventurecreator.views.FragmentViewActivity;

import java.util.concurrent.ExecutionException;

public class CacheStoryTestCases extends ActivityInstrumentationTestCase2<FragmentViewActivity> {

    private CacheStoryTask cacheTask;
    private Context context;

    public CacheStoryTestCases() {
        super(FragmentViewActivity.class);
    }

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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // On Post execute won't end til we end this activity
            }
        });

    }
}
