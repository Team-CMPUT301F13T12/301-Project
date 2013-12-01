package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

import java.util.List;

public class StoryListTest extends TestCase {
    private List<Story> stories;
    private StoryList sl;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sl = new StoryList();
    }

    public void testStoryListNotNull() {
        assertNotNull(sl);
    }

    public void testAddStory() {
        int oldSize = sl.getAllStories().size();
        sl.addStory(new Story());
        assertFalse("No story was added", (oldSize == sl.getAllStories().size()));
    }

    public void testRemoveStory() {
        int oldSize;
        Story s = new Story("I am a", "Story");
        sl.addStory(s);
        oldSize = sl.getAllStories().size();
        sl.deleteStory(s);
        assertFalse("Story wasn't removed", (oldSize == sl.getAllStories().size()));
        assertNull("Story still exists", sl.getStory(s));
    }
    
    public void testGetStoryByTitle(){
        String title = "Title";
        Story s = new Story(title, "the author");
        sl.addStory(s);
        assertNotNull("Couldn't find story", sl.getStory(title));
        assertSame("Stories don't match", s, sl.getStory(title));
    }
    
}
