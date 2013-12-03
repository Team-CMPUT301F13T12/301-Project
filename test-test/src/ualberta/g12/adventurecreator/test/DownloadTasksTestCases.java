
package ualberta.g12.adventurecreator.test;

import android.content.Context;
import android.os.AsyncTask.Status;
import android.test.ActivityInstrumentationTestCase2;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.tasks.CacheStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadTitleAuthorsTask;
import ualberta.g12.adventurecreator.views.OnlineStoryViewActivity;

import java.util.List;

/**
 * These test cases test the tasks of the online to offline and vice versa
 * implements. There are to ensure that tasks that publish or download stories
 * work correctly as well as viewing the stories after they have been
 * downloaded/published. We test if the fields CacheStoryTask,
 * DownloadTitleAuthorsTask, DownloadStoryTask, PublishStoryTask and
 * TryPublishStoryTask behave correctly
 **/
public class DownloadTasksTestCases extends
        ActivityInstrumentationTestCase2<OnlineStoryViewActivity> {

    private DownloadStoryTask downloadStory;
    private DownloadTitleAuthorsTask downloadTitleAuthors;
    private CacheStoryTask cacheStory;
    private Context context;

    public DownloadTasksTestCases() {
        super(OnlineStoryViewActivity.class);
    }

    public void setUp() {
        this.context = getActivity();
        this.downloadStory = new DownloadStoryTask(this.context);
        this.downloadTitleAuthors = new DownloadTitleAuthorsTask(context, getActivity());
        this.cacheStory = new CacheStoryTask(context);
    }

    public void testNotNulls() {
        assertNotNull(this.downloadStory);
        assertNotNull(this.downloadTitleAuthors);
        assertNotNull(this.cacheStory);
    }

    public void testDownloadStory() throws Throwable {
        /*
         * This is assuming that no one has cleared the list of online stories
         */
        List<Story> ls = AdventureCreator.getStoryList().getAllStories();
        final int oldSize = ls.size();
        // This story has already been published
        final Story s = new Story("A", "A");
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                downloadStory.execute(new Story[] {
                        s
                });
            }
        });

        // Wait until we're done
        downloadStory.get();

        while (downloadStory.getStatus() == Status.FINISHED)
            ;
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                StoryList sl = AdventureCreator.getStoryList();
                assertTrue("We didn't add a story: " + oldSize + " " + sl.getAllStories().size(),
                        oldSize < sl.getAllStories().size());
            }
        });
    }
}
