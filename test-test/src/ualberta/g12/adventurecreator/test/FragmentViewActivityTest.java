package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.views.FragmentViewActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Tests for the fragment view activity
 * These tests should cover all tests in use case 1
 * These tests ensure that the FragmentViewActivity properly displays fragments in a story
 * 
 *
 */
public class FragmentViewActivityTest extends ActivityInstrumentationTestCase2<FragmentViewActivity>{

	private FragmentViewActivity myActivity;
	
	
	public FragmentViewActivityTest() {
		super(FragmentViewActivity.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Tests to see if the activity can view a fragment correctly 
	 * We first create a story with a custom fragment and make sure that the 
	 * textView of the activity matches with the title of the fragment
	 * This test covers part 1 of use case 1 test cases
	 */
	public void testViewFrag(){
		Story s = new Story();
		Fragment f = new Fragment();
		FragmentController fc = AdventureCreator.getFragmentController();
        StoryController sc = AdventureCreator.getStoryController();
        
        Fragment f2 = new Fragment();
        
        sc.setFragmentAtLocation(s, 0, f);
        sc.addFragment(s, f2);
        sc.setFragmentAtLocation(s, 1, f2);
        fc.setTitle(f, "Can you see this?");
        fc.setTitle(f2,"end");
        fc.addNewFragmentPart(f, "t", 0);
        f.getParts().get(0).setData("hello!");
        
        Choice c = new Choice();
        c.setChoiceText("go here!");
        c.setLinkedToFragmentPos(1);
        fc.addNewFragmentPart(f, "c", 1);
        f.getParts().get(1).setChoice(c);
       	
        sc.setTitle(s, "TITLE");
        sc.setAuthor(s, "AUTHOR");
        
        Intent intent = new Intent();
        intent.putExtra("Story", s);

        setActivityIntent(intent);
        myActivity = getActivity();

        TextView myTitle = (TextView) myActivity.findViewById(R.id.fragmentTitleText);
        ListView myParts= (ListView) myActivity.findViewById(R.id.fragmentViewPartList);
        Log.d("this is the title:", myTitle.getText().toString());
        assertTrue(myTitle.getText().toString().equals("Can you see this?"));
        assertNotNull(myParts);
        assertTrue(myParts.getCount()>0);
	}
	
	/**
	 * Tests to see if the fragmentView can display stories with no fragments
	 * Because stories always have a default fragment called Story Start Fragment we can assume that the text will also be this
	 * This test covers part 2 of use case 1 test cases
	 */
	public void testViewNoneFrag(){
		Story s = new Story();
        StoryController sc = AdventureCreator.getStoryController();
        
        sc.setTitle(s, "TITLE");
        sc.setAuthor(s, "AUTHOR");
        
        Intent intent = new Intent();
        intent.putExtra("Story", s);

        setActivityIntent(intent);
        myActivity = getActivity();

        TextView myTitle = (TextView) myActivity.findViewById(R.id.fragmentTitleText);
        Log.d("this is the title:", myTitle.getText().toString());
        assertTrue(myTitle.getText().toString().equals("Story Start Fragment"));
	}
	
}
