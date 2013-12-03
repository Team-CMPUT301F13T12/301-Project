
package ualberta.g12.adventurecreator.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.tasks.PublishStoryTask;
import ualberta.g12.adventurecreator.tasks.TryPublishStoryTask;
import ualberta.g12.adventurecreator.views.MainActivity;

/**
 * These test cases test the tasks of the online to offline and vice versa
 * implements. There are to ensure that tasks that publish or download stories
 * work correctly as well as viewing the stories after they have been
 * downloaded/published. We test if the fields CacheStoryTask,
 * DownloadTitleAuthorsTask, DownloadStoryTask, PublishStoryTask and
 * TryPublishStoryTask behave correctly
 **/
public class PublishTaskTestCases extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private PublishStoryTask publishTask;
    private TryPublishStoryTask tryPublishTask;
    private Context context;

    public PublishTaskTestCases() {
        super(MainActivity.class);
    }

    public void setUp() {
        this.context = getActivity();
    }

    public void testNotNulls() {
        this.publishTask = new PublishStoryTask(this.context);
        this.tryPublishTask = new TryPublishStoryTask(this.context);
        assertNotNull(this.publishTask);
        assertNotNull(this.tryPublishTask);
    }

    public void testCheckPublish() throws Throwable {
        tryPublishTask = new TryPublishStoryTask(this.context);

        final Story s = new Story("A", "A");

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                tryPublishTask.execute(new Story[] {
                        s
                });
            }
        });

        if (tryPublishTask.get()) {
            // Story exists we must update it
        } else {
            // Story doesn't exist oh no
            fail("Assuming story already existed, this should have tried to overwrite it");
        }
    }

    public void testPublish() throws Throwable {
        this.publishTask = new PublishStoryTask(this.context);

        final Story s = new Story("A", "A");

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                publishTask.execute(new Story[] {
                    s
                });
            }
        });
        
        if(this.publishTask.get()){
            // Upload worked
            
        } else {
            // Upload failed
            fail("Publish failed");
        }
    }
}
