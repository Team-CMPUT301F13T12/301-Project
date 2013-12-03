package ualberta.g12.adventurecreator.test;


import java.util.LinkedList;
import java.util.List;

import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.views.StoryEditActivity;
import android.test.ActivityInstrumentationTestCase2;

public class StoryTest extends ActivityInstrumentationTestCase2<StoryEditActivity> {

	/**
	 * Default constructor; doesn't do anything fancy except for initializing the testCases
	 */
	public StoryTest(){
		super(StoryEditActivity.class);
	}
	
	/**
	 * Tests to see if the default constructor is working properly
	 * We test to see if a new story has "" as its author and title as this is what it
	 * should be when the story is created with no arguments
	 */
	public void testDefaultConstructor(){
		Story s = new Story();
		assertTrue(s.getAuthor().equals(""));
		assertTrue(s.getTitle().equals(""));
	}
	
	/**
	 * Tests to see if constructor works when we give it the arguments of the story title and also author 
	 * Actually this works the same way as the previous test in that the author and title are defaulted to "" even with
	 * contructors 
	 */
	public void testConstructorWithArguments(){
		Story s = new Story("test","tester");
		assertTrue(s.getAuthor().equals("tester"));
		assertTrue(s.getTitle().equals("test"));
	}
	
	
	/**
	 * Tests if the GetID works
	 * getId returns the current id of the object which should never be -1
	 * This is becuase the id is a hash of the author and title strings 
	 */ 
	public void testUpdateIdAndGetId(){
		Story s = new Story();
		s.setAuthor("test");
		s.setTitle("test");
		assertTrue(s.getId() != -1);

	}
	
	/**
	 * 	Tests to see if the GetTitle function works
	 *  to do this we first make a new story with title test 
	 *  and see if the title is "" which is the fault title 
	 */ 
	public void testGetTitle(){
		Story s = new Story("test","tester");
		
		assertTrue(s.getTitle().equals("test"));

	}
	
	/**
	 * 	Tests to see if the setTitle function works
	 *  to do this we first make a new story with title "test" 
	 *  and see if the title is "test"
	 *  then we use the setTitle to set the title to "changed"
	 *  and ensure that the title has been changed
	 */ 
	public void testSetTitle(){
		Story s = new Story();
		s.setTitle("changed");
		assertTrue(s.getTitle().equals("changed"));

	}
	
	
	/**
	 * 	Tests too see if the getStartFragPos is workign correctly 
	 * 	StartFragPos is always default to zero so we check if it is zero
	 */ 
	public void testGetStartFragPos(){
		Story s = new Story("test","tester");
		assertTrue(s.getStartFragPos() == 0);
	}
	
	/**
	 * 	This test checks if the getter and setter methods for the Author field work correctly
	 * 	We will create a  new story object, change the author and see if we get the changed author string back
	 */ 
	public void testSetAuthorAndGetAuthor(){
		Story s = new Story();
		s.setAuthor("new Author");
		assertTrue(s.getAuthor().equals("new Author"));
	}
	
	/**
	 * This test ensures that the getting of setting of the Fragments(list of Fragment) works correctly 
	 * We will add our own Fragment List and make sure that it is the same as the one we set to the Story
	 * We will then test if getFragmentAtPos works correctly which is supposed to return the fragment as any position
	 */
	public void testGetAndSetFragments(){
		Story s = new Story("test","tester");
		Fragment f = new Fragment();
		f.setTitle("fragment1");
		Fragment f2 = new Fragment();
		f.setTitle("fragment2");
		List<Fragment> firstList = new LinkedList<Fragment>();
		firstList.add(f);
		firstList.add(f2);
		s.setFragments(firstList);
		List<Fragment> secondList = s.getFragments();
		assertTrue(firstList.size() == secondList.size());
		for(int i = 0; i < secondList.size() ; i++){
			assertTrue(firstList.get(i).getTitle().equals(secondList.get(i).getTitle()));
		}
		Fragment f3 = s.getFragmentAtPos(0);
		assertTrue(f3.getTitle().equals("fragment2"));
		
	}
}
