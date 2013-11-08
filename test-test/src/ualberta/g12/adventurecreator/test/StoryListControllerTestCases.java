
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

    // test adding a story
    public void testAddStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        int oldSize = sl.getAllStories().size();
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);

        assertTrue("StoryList size didn't increase", oldSize < sl.getAllStories().size());

    }

    // tests deleting a story
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

    // tests getting a story
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

    // tests getting a story
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

}
