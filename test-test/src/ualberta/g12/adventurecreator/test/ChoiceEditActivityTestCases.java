
package ualberta.g12.adventurecreator.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.ChoiceEditActivity;
import ualberta.g12.adventurecreator.R;



/**
 * This class will test that the widgets in the ChoiceEditActivity are correctly initialized and can be edited
 * There are two widgets on the screen, on editText and also a button
 *
 */
public class ChoiceEditActivityTestCases extends
        ActivityInstrumentationTestCase2<ChoiceEditActivity> {

 
	private ChoiceEditActivity myActivity;
    private Fragment f;
    private Story s;
    private Choice c;
    private FragmentController fc;
    private StoryController sc;
    private StoryListController slc;
    private EditText myTitleET;
    private Button choiceButton;

    private static final String TAG = "ChoiceEditActivity";

    public ChoiceEditActivityTestCases() {
        super(ChoiceEditActivity.class);
    }

    /**
     * Lets add stories, controllers and also choices to our application!
     */
    @Override
    protected void setUp() {
        // super.setUp();
        StoryList sl = AdventureCreator.getStoryList();

        s = new Story("wow", "hey");
        f = new Fragment();
        c = new Choice();
        fc = AdventureCreator.getFragmentController();
        sc = AdventureCreator.getStoryController();
        sc.addFragment(s, f);
        fc.setTitle(f, "TITLE");
        sc.setTitle(s, "TITLE");
        sc.setAuthor(s, "AUTHOR");
        
        Fragment f2 = new Fragment();
        fc.setTitle(f2, "SecondFrag");
        sc.addFragment(s, f2);


        
        
        sc.setFragmentAtLocation(s, 0, f);
        sc.setFragmentAtLocation(s, 1, f2);
        Log.d(TAG, String.format("This many parts: %d", f.getParts().size()));

        c.setChoiceText("Look at me!");
        fc.addNewFragmentPart(f, "c", 0);
        f.getParts().get(0).setChoice(c);
        sc.setFragmentAtLocation(s, 0, f);
        Log.d(TAG, String.format("This many parts: %d", f.getParts().size()));


        slc = AdventureCreator.getStoryListController();
        slc.addStory(s);

        int StoryPos = -9;
        for (int i = 0 ; i < sl.getAllStories().size();i++){
        	if(sl.getAllStories().get(i).getTitle().equals(s.getTitle())){
        		StoryPos = i;
        	}
        		
        }
        
        Intent intent = new Intent();
        intent.putExtra("StoryPos", StoryPos);
        intent.putExtra("FragmentPos", 0);
        intent.putExtra("ChoicePos", 0);

        setActivityIntent(intent);

        myActivity = getActivity();

        myTitleET = (EditText) myActivity.findViewById(R.id.choiceBody);
        choiceButton = (Button) myActivity.findViewById(R.id.choiceButton);
    }


    /**
     * tests to see that the widgets all appear 
     */
   
    public void testWidgets() {
    	assertNotNull(myActivity);
    	assertNotNull(choiceButton);
    	assertNotNull(myTitleET);

    }
    
    
    /**
     * Tests to see if editText has the correct string
     */
    public void testViewEditTextView() {
    	assertTrue(myTitleET.getText().toString().equals(c.getChoiceText()));
    }
    
    /**
     * Tests to see if editText is correctly updated
     */
    @UiThreadTest
    public void testEditEditTextView() {
    	String myNewChoiceTitle = "NEW CHOICE TEXT";
    	myTitleET.setText(myNewChoiceTitle);
    	assertEquals(myTitleET.getText().toString(), myNewChoiceTitle);
    }
    
    /**
     * TODO IF we ever have time try to figure out how to test the button which displays a dialog (pretty hard right!)
     */

}
