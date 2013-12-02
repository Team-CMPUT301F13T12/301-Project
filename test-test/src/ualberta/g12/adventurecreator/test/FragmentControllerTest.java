
package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;
import ualberta.g12.adventurecreator.views.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Tests the functions of the Fragment class
 */
public class FragmentControllerTest extends ActivityInstrumentationTestCase2<MainActivity> {
    FragmentController fragmentController;
    Fragment fragment;

    /**
     * Default constructor; doesn't do anything fancy except for initializing
     * the testCases
     */
    public FragmentControllerTest() {
        super(MainActivity.class);
        fragmentController = new FragmentController();
        fragment = new Fragment();
    }

    /**
     * Tests to see if the setTitle and getTitle functions work. To do this we
     * first make a new fragment, then set the title to "test" and see if
     * getTitle returns "test"
     */
    public void testSetTitle() {
        fragmentController.setTitle(fragment, "test");
        assertTrue(fragment.getTitle().equals("test"));

    }

    /**
     * This test checks if the method, addNewFragmentPart, functions correctly. It should
     * add a new fragment part of the given type to the existing Fragment. It
     * should also return the added part.
     */
    public void testAddNewFragmentPart() {
        FragmentPart part = fragmentController.addNewFragmentPart(fragment, "i", 0);
        assertTrue(fragment.getParts().size() == 1);
        assertTrue(fragment.getParts().get(0).getType().equals("i"));
        assertTrue(part.getType().equals("i"));
    }

    /**
     * This test checks if the method, setFragmentPartData, functions correctly. It should
     * change the data field of a the passed fragment part.
     */
    public void testSetFragmentPartData() {
        FragmentPart part = fragmentController.addNewFragmentPart(fragment, "t", 0);

        assertTrue(fragment.getParts().size() == 1);
        /* should be blank originally */
        assertTrue(fragment.getParts().get(0).getData() == "");

        /* set the data */
        fragmentController.setFragmentPartData(fragment, part, "newText");

        assertTrue(fragment.getParts().size() == 1);
        assertTrue(fragment.getParts().get(0).getType().equals("t"));
        assertTrue(fragment.getParts().get(0).getData().equals("newText"));
    }

    /**
     * This test checks if the method, setFragmentPartChoice, functions correctly. It
     * should change the choice of a the passed fragment part.
     */
    public void testSetFragmentPartChoice() {
        FragmentPart part = fragmentController.addNewFragmentPart(fragment, "c", 0);

        assertTrue(fragment.getParts().size() == 1);
        /* should be a blank choice originally */
        assertTrue(fragment.getParts().get(0).getChoice().getChoiceText() == "");

        /* create the new Choice */
        Choice choice = new Choice();
        choice.setChoiceText("newText");

        /* set the choice */
        fragmentController.setFragmentPartChoice(fragment, part, choice);

        assertTrue(fragment.getParts().size() == 1);
        assertTrue(fragment.getParts().get(0).getType().equals("c"));
        assertTrue(fragment.getParts().get(0).getChoice().getChoiceText().equals("newText"));
    }

    /**
     * This test checks if the method, setFragmentPartPicSize, functions correctly. It
     * should change the picSize field of a the passed fragment part.
     */
    public void testSetFragmentPartPicSize() {
        FragmentPart part = fragmentController.addNewFragmentPart(fragment, "i", 0);

        assertTrue(fragment.getParts().size() == 1);
        /* should be 1 originally */
        assertTrue(fragment.getParts().get(0).getPicSize() == 1);

        /* set the data */
        fragmentController.setFragmentPartPicSize(fragment, part, 2);

        assertTrue(fragment.getParts().size() == 1);
        assertTrue(fragment.getParts().get(0).getType().equals("i"));
        assertTrue(fragment.getParts().get(0).getPicSize() == 2);
    }

    /**
     * This test checks if the method, deleteFragmentPart, functions correctly. It should
     * remove the FragmentPart located at index partNum in the Fragment's list
     * of FragmentParts.
     */
    public void testDeleteFragmentPart() {
        fragmentController.addNewFragmentPart(fragment, "t", 0);
        fragmentController.addNewFragmentPart(fragment, "i", 1);
        fragmentController.addNewFragmentPart(fragment, "e", 2);
        fragmentController.addNewFragmentPart(fragment, "c", 3);

        assertTrue(fragment.getParts().size() == 4);

        /* set the data */
        fragmentController.deleteFragmentPart(fragment, 1);

        assertTrue(fragment.getParts().size() == 3);
        assertTrue(fragment.getParts().get(0).getType().equals("t"));
        assertTrue(fragment.getParts().get(1).getType().equals("e"));
        assertTrue(fragment.getParts().get(2).getType().equals("c"));
    }

    /**
     * This test checks if the method, removeEmptyPart, functions correctly. It should
     * remove the first instance of an empty part the Fragment's list
     * of FragmentParts.
     */
    public void testRemoveEmptyPart() {
        fragmentController.addNewFragmentPart(fragment, "t", 0);
        fragmentController.addNewFragmentPart(fragment, "i", 1);
        fragmentController.addNewFragmentPart(fragment, "e", 2);
        fragmentController.addNewFragmentPart(fragment, "c", 3);
        fragmentController.addNewFragmentPart(fragment, "e", 4);

        assertTrue(fragment.getParts().size() == 5);

        /* set the data */
        fragmentController.removeEmptyPart(fragment);
        
        assertTrue(fragment.getParts().size() == 4);
        assertTrue(fragment.getParts().get(0).getType().equals("t"));
        assertTrue(fragment.getParts().get(1).getType().equals("i"));
        assertTrue(fragment.getParts().get(2).getType().equals("c"));
        assertTrue(fragment.getParts().get(3).getType().equals("e"));
    }
}
