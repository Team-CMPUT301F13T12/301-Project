package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.views.ChoiceEditActivity;
import ualberta.g12.adventurecreator.views.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * These test cases test that all getters and setters of the Choice class work correctly,
 * because there are no methods other than getters and setters associated with the choice class
 * thats all we test
 * 
 * We test if all fields choiceText, linkedToFragmentPos, and isRandom behave correctly
 *
 */
public class ChoiceTest extends ActivityInstrumentationTestCase2<ChoiceEditActivity> {

	public ChoiceTest(){
		super(ChoiceEditActivity.class);
	}
	
	public ChoiceTest(Class<MainActivity> activityClass) {
		super(ChoiceEditActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Tests to see if we can both set the choice text and also get the choice text from the newly Created Choice
	 * To we create a new choice object set the text and then get the text and see if it is the same
	 * 
	 */
	public void testGetChoiceTextAndSetChoiceText(){
		Choice choice = new Choice();
		choice.setChoiceText("Hi mom!");
		assertTrue(choice.getChoiceText().equals("Hi mom!"));
	}
	
	
	/**
	 * Tests to see if the getisRandom and setisRandom works correctly 
	 * We do this by creating a new choice then setting and getting true and false to the isRandom 
	 */
	public void testSetIsRandomAndGetIsRandom(){
		Choice choice = new Choice();
		choice.setisRandom(true);
		assertTrue(choice.getisRandom());
		choice.setisRandom(false);
		assertFalse(choice.getisRandom());
	}
	
	
	/**
	 * Tests to see if the getLinkedToFragmentPos and setLinkedToFragmentPos work
	 * We can do this by creating a new choice object..then setting and getting the fragmentPos to make sure its the samesies
	 * then make sure its the same 
	 */
	public void testLinkedToFragment(){
		Choice choice = new Choice();
		choice.setisRandom(true);
		Fragment f = new Fragment();
		f.setTitle("Heya bud");
		choice.setLinkedToFragmentPos(55);
		assertTrue(choice.getLinkedToFragmentPos() == 55);
	}
	
	
	
	

}
