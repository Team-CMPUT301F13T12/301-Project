
package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.Story;
import ualberta.g12.adventurecreator.StoryList;
import ualberta.g12.adventurecreator.StoryListController;

import java.util.List;

public class StoryListControllerTestCases extends TestCase {

    private StoryListController slc;
    private StoryList sl;
    private List<Story> stories;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sl = new StoryList();
        slc = new StoryListController(sl);
    }

    public void testSetUp() {
        assertNotNull("StoryList was null.", sl);
        assertNotNull("StoryListController was null", sl);
    }

    public void testAddStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        int oldSize = sl.getAllStories().size();
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);

        assertTrue("StoryList size didn't increase", oldSize < sl.getAllStories().size());

    }

    public void testDeleteStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        int oldSize = sl.getAllStories().size();
        slc.deleteStory(s);
        assertTrue("StoryList size dien't decrease", oldSize > sl.getAllStories().size());

    }

    public void testGetStoryWithObject() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        Story s2 = slc.getStory(s);
        assertTrue("Stories don't have same titles", s.getStoryTitle().equals(s2.getStoryTitle()));
        assertTrue("Stories don't have same authors", s.getAuthor().equals(s2.getAuthor()));
    }

    public void testGetStoryWithTitle() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        Story s2 = slc.getStory("Book");
        assertTrue("Stories don't have same titles", s.getStoryTitle().equals(s2.getStoryTitle()));
        assertTrue("Stories don't have same authors", s.getAuthor().equals(s2.getAuthor()));
    }

    public void testGetInitialListOfStories() {
        sl = new StoryList();
        slc = new StoryListController(sl);
        assertNotNull("Inital story list is null", sl);
        assertNotNull("Initial story list controller is null", slc);
        assertNotNull(slc.getAllStories());
        assertTrue("Initial list of stories wasn't 0", slc.getAllStories().size() == 0);
    }

    public void testAddStoriesToList() {
        Story s = new Story("A Dance With Dragons", "George R.R. Martin");
        int oldSize = slc.getAllStories().size();
        slc.addStory(s);
        stories = slc.getAllStories();
        assertTrue("Size of list of stories didn't increase when we added one",
                oldSize < stories.size());
        oldSize = stories.size();
        s = new Story("A Game of Thrones", "George R.R. Martin");
        slc.addStory(s);
        stories = slc.getAllStories();
        assertTrue("Size of list of stories didn't increase when we added a story",
                oldSize < stories.size());
    }

    public void testSetStory() {
        Story s = new Story("The Winds of Winter", "George R.R. Martin");
        Story s1 = new Story("A Storm of Swords", "George R.R. Martin");
        Story s2 = new Story("A Clash of Kings", "George R.R. Martin");

        slc.addStory(s);
        slc.addStory(s1);
        slc.addStory(s2);

        int size = slc.getAllStories().size();
        int index = size - 1;

        assertTrue("We have zero stories in our story list", size > 0);

        Story s3 = new Story("Book!", "Author??");
        slc.setStory(s3, index);

        assertTrue("The name of the book didn't change", slc.getAllStories().get(index)
                .getStoryTitle().equals(s3.getStoryTitle()));
        assertTrue("The author of the book didn't change", slc.getAllStories().get(index)
                .getAuthor().equals(s3.getAuthor()));
    }

}
