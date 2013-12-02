
package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;

/**
 * These test cases test that all getters and setters in the Story Controller class work
 * properly. There are no methods other than getters and setters associated with 
 * this controller class, so they are tested extensively.
 * 
 * We test to see if adding and deleting stories respond correctly as well as the fields 
 * associated with stories, those being the title, author, and fragments of a story
 *
 */

public class StoryControllerTest extends TestCase {

    private StoryController sc;

    protected void setUp() throws Exception {
        super.setUp();
        sc = new StoryController();
    }

    /**
     * Tests setting a title of a story with a story controller
     * We will create a new story, set the title using the controller and make sure that the title is changed
     */
    public void testSetTitle() {
        Story s = new Story();
        sc.setTitle(s, "TEST");

        assert (s.getTitle().equals("TEST"));
    }

    /**
     * Tests setting the Author of a story with a story controller
     * We will create a new story, set the author  using the controller and make sure that the author is changed
     */
    public void testSetAuthor() {
        Story s = new Story();
        sc.setAuthor(s, "ME");

        assert (s.getAuthor().equals("ME"));
    }

    /**
     * Tests adding a fragment to a story with a story controller
     */
    public void testAddFragment() {
        Story s = new Story();
        Fragment f = new Fragment();
        sc.addFragment(s, f);
        assert (s.getFragments().size() == 1);
    }


    /**
     * Tests setting a fragment at a certain position
     * We know that the first fragment to be added will be at position zero
     * We then test setting the fragment at position zero to a new fragment we create
     * At the end we verify that the title has been changed
     */
    public void testSetFragAtPosition() {
        Story s = new Story();
        Fragment f = new Fragment();
        f.setTitle("HIHI");
        sc.addFragment(s, f);
        assert (s.getFragments().get(0).getTitle().equals("HIHI"));
        Fragment f1 = new Fragment();
        f1.setTitle("HEY");
        sc.setFragmentAtLocation(s, 0, f1);
        assert (s.getFragments().get(0).getTitle().equals("HEY"));
    }
}
