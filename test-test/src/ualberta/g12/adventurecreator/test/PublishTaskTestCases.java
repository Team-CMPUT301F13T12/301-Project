
package ualberta.g12.adventurecreator.test;

import android.content.Context;
import android.os.AsyncTask.Status;
import android.test.ActivityInstrumentationTestCase2;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.tasks.DownloadStoryTask;
import ualberta.g12.adventurecreator.tasks.PublishStoryTask;
import ualberta.g12.adventurecreator.tasks.TryPublishStoryTask;
import ualberta.g12.adventurecreator.views.MainActivity;

import java.util.List;

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
    private DownloadStoryTask downloadTask;
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

        if (this.publishTask.get()) {
            // Upload worked

        } else {
            // Upload failed
            fail("Publish failed");
        }
    }

    public void testPublishDownload() throws Throwable {
        this.publishTask = new PublishStoryTask(this.context);
        this.downloadTask = new DownloadStoryTask(this.context);

        final Story s = new Story("AB", "AB");

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                publishTask.execute(new Story[] {
                        s
                });
            }
        });
        if (!this.publishTask.get()) {
            // Upload failed
            fail("publish failed");
        }

        while (publishTask.getStatus() != Status.FINISHED) {
            if (publishTask.isCancelled()) {
                fail("Publish task was cancelled");
            }
        }

        List<Story> list = AdventureCreator.getStoryList().getAllStories();
        int count = 0;
        for (Story d : list) {
            if (d.getTitle().equals("AB") && d.getAuthor().equals("AB")) {
                count++;
            }
        }

        final int finalCount = count;

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                downloadTask.execute(new Story[] {
                        s
                });
            }
        });

        // Wait till we'ere done
        if (downloadTask.get().contains("Download failed")) {
            // Download Failed
            fail("Download failed");
        }

        // Wait til onPostExecute is done
        while (downloadTask.getStatus() != Status.FINISHED) {
            if (downloadTask.isCancelled()) {
                fail("We were cancelled");
            }
        }

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                List<Story> sl = AdventureCreator.getStoryList().getAllStories();
                int otherCount = 0;
                for (Story r : sl) {
                    if (r.getAuthor().equals("AB") && r.getTitle().equals("AB")) {
                        otherCount++;
                    }
                }
                assertTrue("There wasn't an additional AB story after downloading it",
                        otherCount > finalCount);
            }
        });
    }
}
