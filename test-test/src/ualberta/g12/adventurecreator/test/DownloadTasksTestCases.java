
package ualberta.g12.adventurecreator.test;

import android.content.Context;
import android.os.AsyncTask.Status;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.tasks.DownloadStoryTask;
import ualberta.g12.adventurecreator.tasks.DownloadTitleAuthorsTask;
import ualberta.g12.adventurecreator.views.OView;
import ualberta.g12.adventurecreator.views.OnlineStoryViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * These test cases test the functionality of the Download AysncTasks, the
 * DownloadStoryTask and the DownloadTitleAuthorTask.<br>
 * They work by downloading the object from the server, assuming that it exists
 * and then performing some checks on the downloaded object. This tests use
 * cases 12
 * <p>
 * You may notice that the tests may create a possible while loop, as we wait
 * for their status to be Status.FINISHED. This is because they running on a
 * different thread than the rest of the test, and we have no idea of when the
 * download will be completed. If the tasks are somehow cancelled the test will
 * fail out.
 **/
public class DownloadTasksTestCases extends
        ActivityInstrumentationTestCase2<OnlineStoryViewActivity> {

    private DownloadStoryTask downloadStory;
    private DownloadTitleAuthorsTask downloadTitleAuthors;
    private Context context;

    private static final String TAG = "DownloadTasksTestCases";

    public DownloadTasksTestCases() {
        super(OnlineStoryViewActivity.class);
    }

    public void setUp() {
        this.context = getActivity();
        this.downloadStory = new DownloadStoryTask(this.context);
        this.downloadTitleAuthors = new DownloadTitleAuthorsTask(context, getActivity());
    }

    /**
     * Basic sanity test ensuring that all of the tasks can be created properly
     * from their constructors.
     */
    public void testNotNulls() {
        assertNotNull(this.downloadStory);
        assertNotNull(this.downloadTitleAuthors);
    }

    /**
     * This test tries to download as story, and if that is successful, checks
     * that the total number of stories in the list of stories has increased.
     */
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
        // Wait until we're done
        if (downloadStory.get().contains("Download failed")) {
            // Download failed
            fail("Download failed");
        }
        // Wait til onPostExecute is done
        while (downloadStory.getStatus() != Status.FINISHED) {
            if (downloadStory.isCancelled())
                fail("We were cancelled");
        }
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                StoryList sl = AdventureCreator.getStoryList();
                assertTrue("We didn't add a story: " + oldSize + " " + sl.getAllStories().size(),
                        oldSize < sl.getAllStories().size());
            }
        });
    }

    /**
     * This test tries to download a story and checks to see that the story
     * downloaded was the one it wanted. This is done by getting the count of
     * all of the stories with the title and author of the story in question
     * before and after the download and comparing their values after.
     */
    public void testDownloadCheckStory() throws Throwable {
        /*
         * Lets assume that no one deleted this story
         */

        List<Story> list = AdventureCreator.getStoryList().getAllStories();
        int count = 0;
        for (Story s : list) {
            if (s.getTitle().equals("A") && s.getAuthor().equals("A")) {
                count++;
            }
        }

        final Story s = new Story("A", "A");
        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                downloadStory.execute(new Story[] {
                        s
                });
            }
        });

        final int finalcount = count;

        // Wait until we're done
        if (downloadStory.get().contains("Download failed")) {
            // Download failed
            fail("Download failed");
        }

        // Wait til onPostExecute is done
        while (downloadStory.getStatus() != Status.FINISHED) {
            if (downloadStory.isCancelled())
                fail("We were cancelled");
        }

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                StoryList sl = AdventureCreator.getStoryList();
                int otherCount = 0;
                for (Story s : sl.getAllStories()) {
                    if (s.getAuthor().equals("A") && s.getTitle().equals("A")) {
                        otherCount++;
                    }
                }

                assertTrue("There wasn't an additional \"A\" story after downloading it.",
                        otherCount > finalcount);
            }
        });
    }

    private class SampleOViewClass implements OView<List<Story>> {

        public List<Story> titleAuthors;
        public boolean updated = false;

        public SampleOViewClass() {
            this.titleAuthors = new ArrayList<Story>();
        }

        @Override
        public void update(List<Story> model) {
            this.titleAuthors.clear();
            titleAuthors.addAll(model);
            updated = true;
        }

    }

    /**
     * This test downloads the list of title authors from the repo of stories.
     * This data would be displayed in the online mode's list of stories to
     * download or cache.
     */
    public void testDownloadTitleAuthors() throws Throwable {
        final SampleOViewClass sampleClass = new SampleOViewClass();
        this.downloadTitleAuthors = new DownloadTitleAuthorsTask(context, sampleClass);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                downloadTitleAuthors.execute(new String[] {});
            }
        });

        if (!downloadTitleAuthors.get()) {
            // Download failed
            fail("Download failed");
        }

        // Wait til onPostExecute finishes and we get updated
        while (downloadTitleAuthors.getStatus() != Status.FINISHED && !sampleClass.updated) {
            if (downloadTitleAuthors.isCancelled())
                fail("We were cancelled");
        }
        assertNotNull(sampleClass.titleAuthors);
        assertTrue("There are no stories in the sampleClass",
                sampleClass.titleAuthors.size() > 0);
    }

    /**
     * This test downloads the list of title authors from the repo of stories
     * that match the search term provided.
     */
    public void testDownloadSearchTest() throws Throwable {
        final SampleOViewClass sampleClass = new SampleOViewClass();
        this.downloadTitleAuthors = new DownloadTitleAuthorsTask(context, sampleClass);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                downloadTitleAuthors.execute(new String[] {
                        "title"
                });
            }
        });

        if (!downloadTitleAuthors.get()) {
            // Download failed
            fail("Download Failed");
        }

        // Wait til download is complete
        while (downloadTitleAuthors.getStatus() != Status.FINISHED && !sampleClass.updated) {
            if (downloadTitleAuthors.isCancelled())
                fail("We were cancelled");
        }

        // We got the stuff
        for (Story s : sampleClass.titleAuthors) {
            Log.d(TAG, String.format("Title: %s Author: %s", s.getTitle(), s.getAuthor()));
            assertTrue("Story did not contain search term",
                    s.getAuthor().toLowerCase().contains("title")
                            || s.getTitle().toLowerCase().contains("title"));
        }
    }
}
