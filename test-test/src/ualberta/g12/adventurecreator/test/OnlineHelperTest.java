package ualberta.g12.adventurecreator.test;

import java.io.IOException;
import java.util.ArrayList;

import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;
import ualberta.g12.adventurecreator.views.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class OnlineHelperTest  extends
ActivityInstrumentationTestCase2<MainActivity> {

	public OnlineHelperTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Tests inserting a story into the web server by seeing if the size of the collection retrieved is bigger than zero
	 * and also checking that the object inside is not null
	 */
	public void  testGetAllStories(){
		OnlineHelper oh = new OnlineHelper();
		ArrayList<Story>myStories   = oh.getAllStories();
		assertTrue(myStories.size() > 0);
		assertTrue(myStories.get(0)!= null);

	}

	/**
	 * Tests inserting the story into the web server and also getStoryById methods 
	 * Test works by creating a new story object, uploading it into the server and then trying to get the same story back
	 * then we compare if the id, title and also author are the same
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void  testinsertStoryAndGetStoryById() throws IllegalStateException, IOException{
		Story story = new Story();
		story.setTitle("a Title");
		story.setAuthor("Tester");
		int id  = story.getId();
		OnlineHelper oh = new OnlineHelper();
		oh.insertStory(story);
		Story returnedStory = oh.getStory(id);
		assertTrue(story.getTitle().equals(returnedStory.getTitle()));
		assertTrue(story.getAuthor().equals(returnedStory.getAuthor()));
		assertTrue(story.getId() == id);
	}

	/**
	 * Tests getAllStoryTitlesIdAuthor method we add a new story into the web sever
	 * for each story we get back 
	 * then we check if title and author are not null so they have something
	 * then we check if the Id is not -1 because only invalid ids will be this value
	 * then we check if the fragment size is 1 because all fragments will have a size of 1(starting fragment) if none has been added to it
	 * Test does this and that 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void  testGetAllStoryTiltesIdAuthor() throws IllegalStateException, IOException{
		Story story = new Story();
		story.setTitle("a Title");
		story.setAuthor("Tester");
		Fragment frag = new Fragment();
		frag.setTitle("test Frag");
		story.getFragments().add(frag);
		OnlineHelper oh = new OnlineHelper();
		oh.insertStory(story);
		ArrayList<Story> myStories = oh.getAllStoryTitlesIdAuthor();
		for (int i = 0 ; i < myStories.size();i++){
			assertTrue(myStories.get(i).getTitle()!= null);
			assertTrue(myStories.get(i).getAuthor()!=null);
			assertTrue(myStories.get(i).getId()!= -1);
			assertTrue(myStories.get(i).getFragments().size() == 1);
		}

	}

	/**
	 * Test if the checkID method is working for the online helper.
	 * It will be tested by adding a story with an id we know and seeing if the result of checkId is true with the inserted
	 * stories id
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void  testCheckId() throws IllegalStateException, IOException {
		Story story = new Story();
		story.setTitle("Title of my story");
		story.setAuthor("Author of that story ");
		int id  = story.getId();
		OnlineHelper oh = new OnlineHelper();
		oh.insertStory(story);
		ArrayList<Story> myStories = oh.getAllStoryTitlesIdAuthor();
		assertTrue(oh.checkId(id));
	}
	
	/**
	 * Tests the search search method which is used to search the web server for a particular story 
	 * This is tested by adding a story in which we know the title and author
	 * we then "search" for part of the title 
	 * and we find if the returned stories contains the inserted story
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	
	public void testSearchSearch() throws IllegalStateException, IOException{
		Story story = new Story();
		story.setTitle("Search me!");
		story.setAuthor("search by search search");
		int id  = story.getId();
		OnlineHelper oh = new OnlineHelper();
		oh.insertStory(story);
		ArrayList<Story> myStories = oh.searchsearchStories("search");
		boolean inThere = false;
		for(int i = 0; i < myStories.size(); i++){
			if (myStories.get(i).getId() == id)
				inThere = true;
		}
		
		assertTrue(inThere);
	}
	

	/**
	 * Tests deleting a story from the web server with the deleteStories method
	 * We first add a story and get the id
	 * we then get the size of available stories 
	 * after we call the deleteStories method and to make sure we deleted the item
	 * we get the size of the library again and make sure it decremented by 1
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void testDeleteStory() throws IllegalStateException, IOException{
		Story story = new Story();
		story.setTitle("delete me");
		story.setAuthor("erase me");
		int id  = story.getId();
		OnlineHelper oh = new OnlineHelper();
		oh.insertStory(story);
		int prevSize = oh.getAllStoryTitlesIdAuthor().size();
		oh.deleteStories(id);
		int currentSize = oh.getAllStoryTitlesIdAuthor().size();
		assertTrue(currentSize-1 == prevSize);

	}
}
