package ualberta.g12.adventurecreator.test;

import java.util.ArrayList;
import java.util.List;

import android.test.ActivityInstrumentationTestCase2;


import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.MainActivity;

/**
 * This set of test cases will test that our StoryList methods all behave as intended
 * The methods that will be tested are getAllStories,setAllStories,addStory, and getStoryAtPos
 * The storyList is a collection of stories that we have made a class for
 * From the storyList you will be able to access all stories that have been loaded into it 
 */
public class StoryListTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
    public StoryListTest() {
		super(MainActivity.class);
	}

    /**
     * Tests to see if we added a story to the storyList
     * We first check the size of the story at the beginning 
     * then we add a story and make sure that the size has changed
     */
    public void testAddStory() {
    	StoryList sl = new StoryList();
        int oldSize = sl.getAllStories().size();
        sl.addStory(new Story());
        assertFalse("No story was added", (oldSize == sl.getAllStories().size()));
    }

    
    /**
     * Tests if the getStoryAtPos works correctly
     * We will add one story to the storyList which should be a position 0
     * then check if we can get the story with getSToryAtPos correctly 
     */
    public void testGetStoryByTitle(){
    	StoryList sl = new StoryList();
        String title = "Title";
        Story s = new Story();
        s.setTitle("Title");
        s.setAuthor("the author");
        sl.addStory(s);
        assertTrue(sl.getStoryAtPos(0).getTitle().equals("Title"));
        assertTrue(sl.getStoryAtPos(0).getAuthor().equals("the author"));
    }
    
    
    /**
     * This test makes sure that the story list is able to get and test the List of stories
     * We will make a new list of stories, then use the setter and then getter and see if the returned list
     * has the same stories as the original list 
     */
    public void testGetAndSetAllStories(){
    	StoryList sl = new StoryList();
    	List<Story> stories = new ArrayList<Story>();
    	Story sOne = new Story();
    	sOne.setAuthor("author");
    	sOne.setTitle("1st story");
    	Story sTwo = new Story();
    	sTwo.setAuthor("author");
    	sTwo.setTitle("2nd story");
    	stories.add(sOne);
    	stories.add(sTwo);
    	sl.setAllStories(stories);
    	List<Story> returnedStories = sl.getAllStories();
    	for (int i = 0; i < returnedStories.size(); i++){
    		assertTrue(returnedStories.get(i).getTitle().equals(stories.get(i).getTitle()));
    		assertTrue(returnedStories.get(i).getAuthor().equals(stories.get(i).getAuthor()));
    	}
    	
    	
    }
    
}
