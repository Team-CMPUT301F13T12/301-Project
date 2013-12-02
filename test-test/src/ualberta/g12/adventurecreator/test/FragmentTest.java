
package ualberta.g12.adventurecreator.test;

import java.util.List;

import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;
import ualberta.g12.adventurecreator.views.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Tests the functions of the Fragment class
 */
public class FragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * Default constructor; doesn't do anything fancy except for initializing
     * the testCases
     */
    public FragmentTest() {
        super(MainActivity.class);
    }

    /**
     * Tests to see if the default constructor is working properly We test to
     * see if a new fragment has "" as it's title and that the list of
     * FragmentParts is not null.
     */
    public void testDefaultConstructor() {
        Fragment fragment = new Fragment();
        assertTrue(fragment.getTitle().equals(""));
        assertTrue(fragment.getParts() != null);
    }

    /**
     * Tests to see if the setTitle and getTitle functions work. To do this we
     * first make a new fragment, then set the title to "test" and see if
     * getTitle returns "test"
     */
    public void testSetTitle() {
        Fragment fragment = new Fragment();
        fragment.setTitle("test");
        assertTrue(fragment.getTitle().equals("test"));

    }

    /**
     * This test ensures that the getting of setting of the FragmentPart list
     * works correctly. We will create our own FragmentPart List, then set it to
     * a Fragment, and then make getParts returns the same list.
     */
    public void testSetAndGetParts() {
        Fragment fragment = new Fragment();

        // Create new parts list
        FragmentPart part1 = new FragmentPart("t");
        FragmentPart part2 = new FragmentPart("i");
        FragmentPart part3 = new FragmentPart("c");
        List<FragmentPart> parts = fragment.getParts();
        parts.add(part1);
        parts.add(part2);
        parts.add(part3);

        // set the list
        fragment.setParts(parts);

        assertTrue(fragment.getParts().size() == 3);
        assertTrue(fragment.getParts().get(0).getType().equals("t"));
        assertTrue(fragment.getParts().get(1).getType().equals("i"));
        assertTrue(fragment.getParts().get(2).getType().equals("c"));
    }
}
