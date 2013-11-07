
package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.Fragment;
import ualberta.g12.adventurecreator.FragmentController;

public class StoryFragmentControllerTestCases extends TestCase {

    private FragmentController fc;
    private Fragment sf;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fc = new FragmentController();
    }

    // test editing Body Text
    public void testEditText() {
        sf = new Fragment();
        // sfc.editBodyText(sf , "who eats lunch");

        // assert(sf.bodyText == "who eats lunch");

        assertTrue("testEditText has not yet been implemented", false);
    }

    // tests add choice
    public void testAddChoice() {

        /*
         * sf = new sf("Lunch", "You eat lunch mmmm"); sfc.editTitle(sf ,
         * "Dinner"); Choice c = new Choice("To the kitchen!", 39);
         * sfc.editAddChoice(sf, c); assert(sfc.choices.contains(c));
         */
        assertTrue("testAddChoice has not yet been implemented", false);

    }

    // tests deleting Choice
    public void testDeleteStory() {

        /*
         * sf = new sf("Lunch", "You eat lunch mmmm"); sfc.editTitle(sf ,
         * "Dinner"); Choice c = new Choice("To the kitchen!", 39);
         * sfc.editAddChoice(c); assert(sfc.choices.contains(c));
         * sfc.deleteChoice(sf, c); assert(!sfc.choice.contains(c));
         */
        assertTrue("testDeleteStory has not been implemented", false);

    }

}
