
package ualberta.g12.adventurecreator.test;

/* Covers test cases for use cases:
 * 2, 5, 16 , 9
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 * Actually modified from:
 * http://www.vogella.com/articles/AndroidTesting/article.html#tutorial_unittestactivity
 * Referenced Nov. 7, 2013
 * 
 */

import android.test.ActivityInstrumentationTestCase2;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.PopupMenu;

import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.MainActivity;

public class MainActivityTestCases extends
        ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity activity;
    /*private TextView list_story_title;
    private TextView list_story_author;
    private ListView main_activity_listview;*/
    private PopupMenu storyListPopupMenu;
    //private List<Story> stories;
    private StoryList sl;
    private ListView availableStoryList;

    public MainActivityTestCases() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    /* Test Cases - all start with "test" */

    // Use Case 2, test 1/3
    public void testBrowseStoryList() {
        // add in multiple stories into the list
        for (int i = 0; i < 15; i++) {
            Story UserStory = new Story();
            sl.addStory(UserStory);
            /*
             * Button button = (Button) findViewByid(R.id.menu_add_story);
             * button.performClick(); Button button2 = (Button)
             * findViewByid(R.id.editTextSave); button2.performClick();
             */
        }

        // starts the activity
        activity = this.getActivity();
        ListView view = (ListView) activity
                .findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);

        // scroll textview
        view.smoothScrollToPosition(100);

        // check that user can scroll through the list
        // Assert.assertEquals(100,
        // Robolectric.shadowOf(view).getSmoothScrolledPosition());
         assertTrue("testBrowseStoryList is not yet implemented", false); //
        // Test doesn't work yet
    }

    // Use Case 2, test 2/3
    public void testBrowseNoScrollList() {
        // add a single story into the list
        Story UserStory = new Story();
        sl.addStory(UserStory);
        /*
         * Button button = (Button) activity.findViewById(R.id.menu_add_story);
         * button.performClick(); Button button2 = (Button)
         * activity.findViewById(R.id.editTextSave); button2.performClick();
         */

        ListView view = (ListView) activity
                .findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);

        // scroll textview
        view.smoothScrollToPosition(100);

        // check if the list doesn't scroll with a single entry
        // Assert.assertEquals(0,
        // Robolectric.shadowOf(view).getSmoothScrolledPosition());
        assertTrue("testBrowseNoScrollList is not yet implemented", false);
        // // Test does not work yet

    }

    // Use Case 2, test 3/3
    public void testBrowseEmptyStoryList() {
        // no notes added

        // starts the activity
        /*ListView view = (ListView) activity
                .findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);*/

        assertNull(sl);
        // assertTrue("testBrowseEmptyStoryList is not yet implemented", false);
    }

    // Use case 5, test 1/3
    public void testEditExistingStory() {
        activity = this.getActivity();
        // Story s = getStoryFromClick();
        // editStory(s);
        // assert (s != null);

        assertTrue("testEditExistingStory is not yet implemented", false);
    }

    // Use Case 16, test 1/1
    // Use Cause 9 , test 1/3
    public void testDownloadOtherUserStory() {
        Story otherUserStory = new Story();
        otherUserStory.setAuthor("OtherUser");

        // uploads/publishes story
        // addStory(otherUserStory);

        // starts the activity (which will automatically find all
        // available stories and load them into the availableStoryList)
        activity = this.getActivity();
        availableStoryList = (ListView) activity
                .findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);
        /*
         * storyListPopupMenu = (PopupMenu) activity
         * .findViewById(ualberta.g12.adventurecreator
         * .MainActivity.R.id.storyListPoupMenuID);
         */

        // selects first (and only) story
        availableStoryList.setSelection(0);

        // causes popupMenu to open for selection
        availableStoryList.performLongClick();

        // gets download option if available
        MenuItem downloadOption = storyListPopupMenu.getMenu().getItem(2);

        // asserts user can Download the story
        assertTrue(downloadOption.getTitle().equals("Download"));

        // check that story is written by another user
        // assertFalse(someStory.getAuthor().equals(getCurrentUserName()));
        assertTrue("testDownloadOtherUserStory is not yet implemented", false);

    }

    // Use Case 9 , test 2/3
    public void testDowloadStoryNoInternet() {
        Story otherUserStory = new Story();
        otherUserStory.setAuthor("OtherUser");

        // uploads/publishes story
        // addStory(otherUserStory);

        // starts the activity (which will automatically find all
        // available stories and load them into the availableStoryList)
        activity = this.getActivity();
        availableStoryList = (ListView) activity
                .findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);
        /*
         * storyListPopupMenu = (PopupMenu) activity
         * .findViewById(activity.R.id.storyListPoupMenuID);
         */

        // turn off the internet
        // turnOffInternet();

        // selects first (and only) story
        availableStoryList.setSelection(0);

        // causes popupMenu to open for selection
        availableStoryList.performLongClick();

        // gets download option if available ( not available)
        //MenuItem downloadOption = storyListPopupMenu.getMenu().getItem(2);

        // assertTrue(downloadOption.equals(null));
        assertTrue("testDownloadStoryNoInternet is not yet implemented", false);

    }

    // Use Case 9 , test 3/3
    public void testDowloadStoryTurnOffInternet() {
        Story otherUserStory = new Story();
        otherUserStory.setAuthor("OtherUser");

        // uploads/publishes story
        // addStory(otherUserStory);

        // starts the activity (which will automatically find all
        // available stories and load them into the availableStoryList)
        activity = this.getActivity();
        availableStoryList = (ListView) activity
                .findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);
        /*
         * storyListPopupMenu = (PopupMenu) activity
         * .findViewById(ualberta.g12.adventurecreator
         * .R.id.storyListPoupMenuID);
         */

        // selects first (and only) story
        availableStoryList.setSelection(0);

        // causes popupMenu to open for selection
        availableStoryList.performLongClick();

        // gets download option if available
        //MenuItem downloadOption = storyListPopupMenu.getMenu().getItem(2);

        // turn off the internet
        // turnOffInternet();

        // asserts user can Download the story
        // assertTrue(downloadOption.getTitle().equals("Download"));
        assertTrue("testDownloadStoryTurnOffInternet is not yet implemented", false);
    }

}
