
package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

import java.util.List;

public class StoryListControllerTestCases extends TestCase {

    private StoryListController slc;
    private StoryList sl;
    private List<Story> stories;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sl = new StoryList();
        slc = new StoryListController(sl, null);
    }

    public void testSetUp() {
        assertNotNull("StoryList was null.", sl);
        assertNotNull("StoryListController was null", sl);
    }

    public void testAddStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        int oldSize = sl.getAllStories().size();
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);

        assertTrue("StoryList size didn't increase", oldSize < sl.getAllStories().size());

    }


    /**
     * TODO dont work no more
     */
    public void testGetStoryWithObject() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        //Story s2 = sl.getStory(s);
        //assertTrue("Stories don't have same titles", s.getTitle().equals(s2.getTitle()));
       // assertTrue("Stories don't have same authors", s.getAuthor().equals(s2.getAuthor()));
    }

    /**
     * TODO dont work no more
     */
    public void testGetStoryWithTitle() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        //Story s2 = sl.getStory("Book");
        //assertTrue("Stories don't have same titles", s.getTitle().equals(s2.getTitle()));
        //assertTrue("Stories don't have same authors", s.getAuthor().equals(s2.getAuthor()));
    }

    // tests setting a story
    public void testSetStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        assertTrue(sl.getAllStories().get(1).getAuthor().equals("Dan Dude"));
        Story s2 = new Story("Bok", "Dude Dan");
        slc.setStory(s2, 0);
        assertTrue(sl.getAllStories().get(0).getAuthor().equals("Dude Dan"));

    }

    // testing getting a story at Position
    public void testGetStoryAtPos() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        assertTrue(sl.getAllStories().get(0).getAuthor().equals("Dan Dude"));
        Story s2 = sl.getStoryAtPos(0);
        assertTrue(s2.equals(s));

    }

    // testing getting a story at Position
    public void testCreateBlankStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        assertTrue(sl.getAllStories().size() == 0);
        slc.createBlankStory();
        assertTrue(sl.getAllStories().size() == 1);

    }

    // TODO load/save offline story tests

    public void testGetInitialListOfStories() {
        sl = new StoryList();
        slc = new StoryListController(sl, null);
        assertNotNull("Inital story list is null", sl);
        assertNotNull("Initial story list controller is null", slc);
        assertNotNull(sl.getAllStories());
        assertTrue("Initial list of stories wasn't 0", sl.getAllStories().size() == 0);
    }

    public void testAddStoriesToList() {
        Story s = new Story("A Dance With Dragons", "George R.R. Martin");
        int oldSize = sl.getAllStories().size();
        slc.addStory(s);
        stories = sl.getAllStories();
        assertTrue("Size of list of stories didn't increase when we added one",
                oldSize < stories.size());
        oldSize = stories.size();
        s = new Story("A Game of Thrones", "George R.R. Martin");
        slc.addStory(s);
        stories = sl.getAllStories();
        assertTrue("Size of list of stories didn't increase when we added a story",
                oldSize < stories.size());
    }

    public void testSetStory2() {
        Story s = new Story("The Winds of Winter", "George R.R. Martin");
        Story s1 = new Story("A Storm of Swords", "George R.R. Martin");
        Story s2 = new Story("A Clash of Kings", "George R.R. Martin");

        slc.addStory(s);
        slc.addStory(s1);
        slc.addStory(s2);

        int size = sl.getAllStories().size();
        int index = size - 1;

        assertTrue("We have zero stories in our story list", size > 0);

        Story s3 = new Story("Book!", "Author??");
        slc.setStory(s3, index);

        assertTrue("The name of the book didn't change", sl.getAllStories().get(index)
                .getTitle().equals(s3.getTitle()));
        assertTrue("The author of the book didn't change", sl.getAllStories().get(index)
                .getAuthor().equals(s3.getAuthor()));
    }

}
