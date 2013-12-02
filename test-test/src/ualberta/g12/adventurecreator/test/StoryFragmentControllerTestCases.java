
package ualberta.g12.adventurecreator.test;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;

import java.util.List;

public class StoryFragmentControllerTestCases extends TestCase {
    private FragmentController fc;
    private Fragment sf;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fc = new FragmentController();
    }

    /**
     * Tests editing the title of a story fragment with a story Fragment
     * Controller
     */
    public void testEditTitle() {
        sf = new Fragment();
        fc.setTitle(sf, "LUNCH");

        assert (sf.getTitle().equals("LUNCH"));
    }

    /**
     * Tests adding Text Segments to a story fragment with a story Fragment
     * Controller
     */
    public void testAddTextSegment() {

        sf = new Fragment();
        fc.addNewFragmentPart(sf, "t", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.get(0).getType().equals("t"));

    }

    /**
     * Tests deleting an existing story fragment 
     * Fragment Controller
     */
    public void testDeleteStoryFragment() {

        sf = new Fragment();
        fc.addNewFragmentPart(sf, "t", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.get(0).getType().equals("t"));
        assertTrue(sf.getParts().size() == 1);
        fc.deleteFragmentPart(sf, 0);
        assertTrue(sf.getParts().size() == 0);

    }

    /**
     * Tests inserting an "Illustration" to a story fragment with a story
     * Fragment Controller
     */
    public void testInsertIllustration() {
        sf = new Fragment();
        fc.addNewFragmentPart(sf, "i", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.get(0).getType().equals("i"));
        assertTrue(sf.getParts().size() == 1);
    }

    /**
     * Tests deleting an "Illustration" of a story fragment with a story
     * Fragment Controller
     */
    public void testDeleteIllustration() {
        sf = new Fragment();
        fc.addNewFragmentPart(sf, "i", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.get(0).getType().equals("i"));
        assertTrue(sf.getParts().size() == 1);
        fc.deleteFragmentPart(sf, 0);
        assertTrue(sf.getParts().size() == 0);
    }

    /**
     * Tests adding a choice to a Story Fragment with a story Controller
     * fragment
     */
    public void testAddChoice() {
        sf = new Fragment();
        Choice c = new Choice();
        c.setChoiceText("Test");
        fc.addNewFragmentPart(sf, "c", 0);
        assertTrue(c.getChoiceText().equals("Test"));
        assertTrue(sf.getParts().size() == 1);
    }

    /**
     * tests deleting a choice in the story fragment
     */
    public void testDeleteChoice() {
        sf = new Fragment();
        Choice c = new Choice();
        c.setChoiceText("Test");
        fc.addNewFragmentPart(sf, "c", 0);
        assertTrue(c.getChoiceText().equals("Test"));
        assertTrue(sf.getParts().size() == 1);
        fc.deleteFragmentPart(sf, 0);
        assertTrue(sf.getParts().size() == 0);

    }

    /**
     * tests adding an empty part to our Story fragment
     */
    public void testAddEmptyPart() {
        sf = new Fragment();
        fc.addNewFragmentPart(sf, "e", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.get(0).getType().equals("e"));
    }

    /**
     * tests removing an empty part of a story fragment
     */
    public void testRemoveEmptyPart() {
        sf = new Fragment();
        fc.addNewFragmentPart(sf, "e", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.get(0).getType().equals("e"));
        fc.removeEmptyPart(sf);
        assertTrue(sf.getParts().size() == 0);

    }

    /**
     * tests getting the linked to fragment of a choice which is the position of
     * the fragment it is linked to
     */
    public void testGetLinkedToFragmentPosOfChoice() {
//        sf = new Fragment();
//        Choice c = new Choice();
//        c.setLinkedToFragmentPos(0);
//        int pos = c.getLinkedToFragmentPos();
//        int test = fc.getLinkedToFragmentOfChoice(sf, 0).getId();
//        assertTrue(pos == test);
    }

    /**
     * tests getting all text segments inside a fragment part
     */
    public void testGetTexSegments() {
        sf = new Fragment();
        fc.addNewFragmentPart(sf, "t", 0);
        List<FragmentPart> myList = sf.getParts();
        assertTrue(myList.size() == 1);
        assertTrue(sf.getParts().size() == myList.size());
    }

    /**
     * tests the setting of Id from a fragment at position pos
     */

    public void testChangeId() {
        /*
         * To change a
         */
//        sf = new Fragment();
//        sf.setId(32);
//        assertTrue(sf.getId() == 32);
//        sf.setId(31);
//        assertTrue(sf.getId() == 31);
    }

    /**
     * tests the setting of text of a choice
     */

    public void testChangeChoiceText() {
        sf = new Fragment();
        Choice c = new Choice();
        c.setChoiceText("Before");
        assertTrue(c.getChoiceText().equals("Before"));
        c.setChoiceText("After");
        assertTrue(c.getChoiceText().equals("After"));
    }

    /**
     * Changes the LinkedFragment position of a choice
     */
    public void testChangeChoiceLinkedTo() {
//        sf = new Fragment();
//        Choice c = new Choice();
//        c.setLinkedToFragmentPos(4);
//        assertTrue(c.getLinkedToFragmentPos() == 4);
//        sf.getChoices().add(c);
//        fc.setLinkedFragmentOfChoice(sf, 0, null);
//        assertTrue(c.getLinkedToFragmentPos() == 5);
    }

}
