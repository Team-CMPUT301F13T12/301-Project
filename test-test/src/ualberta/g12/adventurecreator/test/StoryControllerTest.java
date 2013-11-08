package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.Fragment;
import ualberta.g12.adventurecreator.Story;
import ualberta.g12.adventurecreator.StoryController;

public class StoryControllerTest extends TestCase {


	
	private StoryController sc;
    
    protected void setUp() throws Exception {
        super.setUp();
        sc = new StoryController();
    }
    
    /**
     * Tests setting a title of a story with a story controller
     */
    public void testSetTitle() {
    	Story s = new Story();
    	sc.setTitle(s, "TEST");

         assert(s.getStoryTitle().equals("TEST"));
    }
    
    /**
     * Tests setting the Author of a story with a story controller
     */
    public void testSetAuthor() {
    	Story s = new Story();
    	sc.setAuthor(s, "ME");

         assert(s.getAuthor().equals("ME"));
    }
    
    /**
     * Tests adding a fragment to a story with a story controller
     */
    public void testAddFragment() {
    	Story s = new Story();
    	Fragment f = new Fragment();
    	sc.addFragment(s, f);
    	assert(s.getFragments().size() == 1);
    }
    
    /**
     * Tests deleting a fragment to a story with a story controller
     */
    public void testDeleteFragment() {
    	Story s = new Story();
    	Fragment f = new Fragment();
    	sc.addFragment(s, f);
    	assert(s.getFragments().size() == 1);
    	sc.deleteFragment(s, f);
    	assert(s.getFragments().size() == 0);
    }


}
