package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.EditFragmentActivity;
import ualberta.g12.adventurecreator.Fragment;
import ualberta.g12.adventurecreator.FragmentController;
import ualberta.g12.adventurecreator.Story;
import ualberta.g12.adventurecreator.StoryController;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;

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

    /**
     * Tests getting fragments from a fragment
     */
    public void testGetAllFragments() {
    	Story s = new Story();
    	Fragment f = new Fragment();
    	sc.addFragment(s, f);
    	assert(s.getFragments().size() == 1);
    	assert(sc.getFragments(s).size() == 1);
    }
    
    /**
     * Tests getting a fragment at a certain position
     */
    public void testGetFragAtPosition(){
       	Story s = new Story();
    	Fragment f = new Fragment();
    	sc.addFragment(s, f);
    	assert(s.getFragments().size() == 1);
    	Fragment f2 = sc.getFragmentsPos(s, 0);
    	assert(f.equals(f2));
    }
    
    /**
     * Tests setting a fragment at a certain position
     */
    public void testSetFragAtPosition(){
       	Story s = new Story();
    	Fragment f = new Fragment();
    	f.setTitle("HIHI");
    	sc.addFragment(s, f);
    	assert(s.getFragments().get(0).getTitle().equals("HIHI"));
    	Fragment f1 = new Fragment();
    	f1.setTitle("HEY");
    	sc.setFragmentAtLocation(s, 0, f1);
    	assert(s.getFragments().get(0).getTitle().equals("HEY"));
    }
}
