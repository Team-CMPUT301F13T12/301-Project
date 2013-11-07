/* Covers test cases for use cases:
 * 2, 5, 16 , 9
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */

import ca.ualberta.cs.livojevi_notes.R;
import ca.ualberta.cs.livojevi_notes.TitleAndDescriptionAdapter;
import android.test.ActivityInstrumentationTestCase2;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class ChooseStoryActivityTestCases extends ActivityInstrumentationTestCase2<ChooseStoryActivity> {
	//declare activity and widgets
	private ChooseStoryActivity myChooseStoryActivity;
	private TextView myView;
	private ListView availableStoryList;
	private PopupMenu storyListPopupMenu;

	//Constructor
	public ChooseStoryActivityTestCases(){
		super("ualberta.g12.adventurecreator.ChooseStoryActivity", ChooseStoryActivity.class);
	}	

	//initializes things you want to be set for all tests (not necessary)
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* Test Cases - all start with "test" */

	//Use Case 2, test 1/3
	public void testBrowseStoryList() {
		//add in multiple stories into the list
		for ({int i = 0; i < 15; i++) {
			Button button = (Button) findViewByid(R.id.menu_add_story);
			button.performClick();
			Button button2 = (Button) findViewByid(R.id.editTextSave);
			button2.performClick();
		}
		
		//starts the activity
		myChooseStoryActivity = this.getActivity();
		ListView view = (ListView) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.main_activity_listview);
		
		//scroll textview
		view.smoothScrollToPosition(100);
		
		//check that user can scroll through the list
		Assert.assertEquals(100, Robolectric.shadowOf(view).getSmoothScrolledPosition());
	}

	//Use Case 2, test 2/3
	public void testBrowseNoScrollList() {
		//add a single story into the list
		Button button = (Button) findViewByid(R.id.menu_add_story);
		button.performClick();
		Button button2 = (Button) findViewByid(R.id.editTextSave);
		button2.performClick();

		//starts the activity
		myChooseStoryActivity = this.getActivity();
		ListView view = (ListView) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.main_activity_listview);
		
		//scroll textview
		view.smoothScrollToPosition(100);
		
		//check if the list doesn't scroll with a single entry
		Assert.assertNotEquals(100, Robolectric.shadowOf(view).getSmoothScrolledPosition());
		
        }

	//Use Case 2, test 3/3
	public void testBrowseEmptyStoryList() {
		//no notes added
		
		//starts the activity
		myChooseStoryActivity = this.getActivity();
		ListView view = (ListView) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.main_activity_listview);
	
		//string set to text in the list
		String Story = view.getText();
		
		//checks if the screen has no input with no stories
		assertEquals(Story, null);
	}

	// Use case 5, test 1/3
	public void testEditExistingStory(){
	    myChooseStoryActivity = this.getActivity();
	    Story s = getStoryFromClick();
	    editStory(s);
	    assert(s != null);
	}
	
	//Use Case 16, test 1/1 
	//Use Case 9 , test 1/3
	public void testDowloadOtherUserStory() {
		Story otherUserStory = new Story();
		otherUserStory.setAuthor("OtherUser");

		//uploads/publishes story
		addStory(otherUserStory);

		//starts the activity (which will automatically find all 
		//available stories and load them into the availableStoryList)
		myChooseStoryActivity = this.getActivity();
		availableStoryList = (ListView) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.storyListID);
		storyListPopupMenu = (PopupMenu) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.storyListPoupMenuID);

		//selects first (and only) story
		availableStoryList.setSelection(0);
		
		//causes popupMenu to open for selection
		availableStoryList.performLongClick();

		//gets download option if available
		MenuItem downloadOption = storyListPopupMenu.getMenu().getItem(2);

		//asserts user can Download the story
		assertTrue(downloadOption.getTitle().equals("Download"));
		
		//check that story is written by another user
		assertFalse(someStory.getAuthor().equals(getCurrentUserName()));
				
	}
	
		//Use Case 9 , test 2/3
	public void testDowloadStoryNoInternet() {
		Story otherUserStory = new Story();
		otherUserStory.setAuthor("OtherUser");

		//uploads/publishes story
		addStory(otherUserStory);

		//starts the activity (which will automatically find all 
		//available stories and load them into the availableStoryList)
		myChooseStoryActivity = this.getActivity();
		availableStoryList = (ListView) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.storyListID);
		storyListPopupMenu = (PopupMenu) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.storyListPoupMenuID);

		//turn off the internet
		turnOffInternet();
		
		//selects first (and only) story
		availableStoryList.setSelection(0);
		
		//causes popupMenu to open for selection
		availableStoryList.performLongClick();

		//gets download option if available ( not available)
		MenuItem downloadOption = storyListPopupMenu.getMenu().getItem(2);
		
		AssertTrue(downloadOption.equals(NULL));
		
	}
	
	//Use Case 9 , test 3/3
	public void testDowloadStoryTurnOffInternet() {
		Story otherUserStory = new Story();
		otherUserStory.setAuthor("OtherUser");

		//uploads/publishes story
		addStory(otherUserStory);

		//starts the activity (which will automatically find all 
		//available stories and load them into the availableStoryList)
		myChooseStoryActivity = this.getActivity();
		availableStoryList = (ListView) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.storyListID);
		storyListPopupMenu = (PopupMenu) myChooseStoryActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.storyListPoupMenuID);


		//selects first (and only) story
		availableStoryList.setSelection(0);
		
		//causes popupMenu to open for selection
		availableStoryList.performLongClick();

		//gets download option if available
		MenuItem downloadOption = storyListPopupMenu.getMenu().getItem(2);
		
		//turn off the internet
		turnOffInternet();

		//asserts user can Download the story
		assertTrue(downloadOption.getTitle().equals("Download"));
	}


}
