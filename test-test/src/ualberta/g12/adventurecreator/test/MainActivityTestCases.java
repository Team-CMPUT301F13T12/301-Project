
package ualberta.g12.adventurecreator.test;

/* Covers test cases for use cases:
 * 2, 5, 16 , 9 , 17
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

import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
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

	/**
	 *  Use Case 2 test , we see that the list is able to show all stories
	 *  and if so then we can browse them in our listview then 
	 *  This is only browsing OFFLINE Stories however.
	 */
	public void testBrowseStoryList() {

		StoryController sc = AdventureCreator.getStoryController();
		StoryList sl = AdventureCreator.getStoryList();
		int realSize = sl.getAllStories().size();

		// starts the activity
		activity = this.getActivity();
		ListView view = (ListView) activity
				.findViewById(ualberta.g12.adventurecreator.R.id.main_activity_listview);

		// scroll textview
		view.smoothScrollToPosition(100);

		assertTrue(realSize == view.getCount());

	}


	/**
	 * Use case 17 test- for "I'm feeling lucky" button (Random Offline story)
	 * Because we pass the storyposition in the storylist to the view fragment activity we hav eto generate a random number
	 * we test the getRand method inside the Mainactivity to ensure that the random number is valid and is random through 10 cases
	 * This functionality will not work however if storyList is zero 
	 */
	public void testRandomStory(){
		StoryController sc = AdventureCreator.getStoryController();
		StoryList sl = AdventureCreator.getStoryList();
		int realSize = sl.getAllStories().size();
		assertTrue(realSize -1 >=0);
		activity = this.getActivity();
		int randomNumber = -99;
		int previous = randomNumber;
		boolean isDifferent = false;
		for (int i = 0; i < 10;i++){
			randomNumber = activity.getRand(realSize-1);
			if (randomNumber != previous ){
				isDifferent = true;
			}
			previous = randomNumber;
		}
		
		assertTrue(isDifferent);
		assertTrue(randomNumber>=0);
		assertTrue(randomNumber <= realSize -1);
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
