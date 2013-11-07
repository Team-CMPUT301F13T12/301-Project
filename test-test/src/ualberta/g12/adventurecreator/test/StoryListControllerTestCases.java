
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
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        // int numBooks = slc.getSize();

        // assert(numBooks != 0);

        assertTrue("testAddStory has not been implemented", false);

    }

    // tests deleting a story
    public void testDeleteStory() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        slc.deleteStory(s);
        // int numBooks = slc.getSize();

        // assert(numBooks == 0);
        assertTrue("testDeleteStory has not been implemented", false);

    }

    // tests getting a story
    public void getStoryWithObject() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        Story s2 = slc.getStory(s);

        // assert(s == s2);
        assertTrue("getStoryWithObject has not been implemented", false);

    }

    // tests getting a story
    public void getStoryWithTitle() {

        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        Story s = new Story("Book", "Dan Dude");
        slc.addStory(s);
        Story s2 = slc.getStory("Book");

        // assert(s == s2);
        assertTrue("getStoryWithTitle has not been implemented", false);

    }

}
