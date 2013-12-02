
package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.FragmentPart;
import ualberta.g12.adventurecreator.views.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Tests the functions of the FragmentPart class
 */
public class FragmentPartTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * Default constructor; doesn't do anything fancy except for initializing
     * the testCases
     */
    public FragmentPartTest() {
        super(MainActivity.class);
    }

    /**
     * Tests to see if the default constructor is working properly We test to
     * see if a new FragmentPart has "" as it's data, 1 as it's picSize, a blank
     * Choice as it's choice, and the passed string as it's type.
     */
    public void testConstructor() {
        FragmentPart part = new FragmentPart("t");

        assertTrue(part.getData().equals(""));
        assertTrue(part.getPicSize() == 1);
        assertTrue(part.getChoice() != null);
        assertTrue(part.getChoice().getChoiceText().equals(""));
        assertTrue(part.getType().equals("t"));
    }

    /**
     * Tests to see if the getType function work as intended. Should return the
     * value originally passed in the constructor.
     */
    public void testgetType() {
        FragmentPart part = new FragmentPart("t");
        assertTrue(part.getType().equals("t"));

    }
    
    /**
     * Tests to see if the setData and getData functions work. To do this we
     * first make a new fragmentPart, then set the data to "test" and check if
     * getData returns "test".
     */
    public void testSetAndGetData() {
        FragmentPart part = new FragmentPart("t");
        part.setData("test");
        assertTrue(part.getData().equals("test"));
    }

    /**
     * Tests to see if the setChoice and getChoice functions work. To do this we
     * first make a new fragmentPart, then create a new Choice with some of the
     * default values changed, then set the choice to this new choice and check
     * if getChoice returns the new non-default Choice.
     */
    public void testSetAndGetChoice() {
        FragmentPart part = new FragmentPart("c");
        Choice choice = new Choice();
        choice.setChoiceText("test");

        part.setChoice(choice);
        assertTrue(part.getChoice().getChoiceText().equals("test"));
    }

    /**
     * Tests to see if the setPicSze and getPicSize functions work. To do this we
     * first make a new fragmentPart, then set the picSize to 2 and check if
     * getPicSize returns 2.
     */
    public void testSetAndGetPicSize() {
        FragmentPart part = new FragmentPart("i");
        part.setPicSize(2);
        assertTrue(part.getPicSize() == 2);
    }
}
