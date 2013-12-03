
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
 * These test cases test the implementation of publishing a story online, Use
 * Case 3, 10.<br>
 * They work by using the two AsyncTasks PublishStoryTask and
 * TryPublishStoryTask.
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

    /**
     * Sanity test to ensure tasks are created properly from their constructors
     */
    public void testNotNulls() {
        this.publishTask = new PublishStoryTask(this.context);
        this.tryPublishTask = new TryPublishStoryTask(this.context);
        assertNotNull(this.publishTask);
        assertNotNull(this.tryPublishTask);
    }

    /**
     * Tests the checking of the story ID. If there already exists a story with
     * that ID, then we will ask the user if they want to update the story as it
     * will overwrite it.
     */
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

    /**
     * Tests the publish functionality. This tests to see if the
     * PublishStoryTask functions properly.
     */
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

    /**
     * The main PublishStory Test. This test case creates a new story, publishes
     * it, and then tries to download it. It then checks to ensure that an
     * additional story was created whch means that the story was published and
     * then downloaded properly.
     */
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
