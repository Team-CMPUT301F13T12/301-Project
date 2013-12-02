package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * This class tests the OffLineIOHelper
 * As the Offline helper only contains two methods besides the constructor we test those
 * Those methods are the loadOfflineStories and also the saveOffLineStories 
 * which we test with one test case to ensure that loading/saving with file io works properly 
 *
 */
public class OfflineIOHelperTest extends
ActivityInstrumentationTestCase2<MainActivity>{

	/**
	 * constructor for the test 
	 */
	public OfflineIOHelperTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Tests to see if we can save and load stories properly from a file
	 * We first create a new StoryList and add a story that we made to it
	 * We then call the saveOfflineStories method to save our list
	 * We then load our stories with loadOfflineStories and make sure that the size is 1 and also that the story was saved by checking
	 * the title and author of the only object in the returned story list 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void testLoadansSaveOfflineStories() throws InstantiationException, IllegalAccessException{
		OfflineIOHelper io = new OfflineIOHelper(getActivity());
		StoryList storylist = new StoryList();
		Story s = new Story();
		s.setAuthor("Tester");
		s.setTitle("This is a test");
		storylist.addStory(s);
		io.saveOfflineStories(storylist);
		StoryList sl = io.loadOfflineStories();
		assertTrue(sl.getAllStories().size() == 1);
		assertTrue(sl.getAllStories().get(0).getAuthor().equals("Tester"));
		assertTrue(sl.getAllStories().get(0).getTitle().equals("This is a test"));
		
	}
}
