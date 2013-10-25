/* Covers test cases for use cases:
 * 3, 5
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */

import android.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class EditStoryActivityTestCases extends ActivityInstrumentationTestCase2<EditStoryActivity> {
	//declare activity and widgets
	private EditStoryActivity myEditStoryActivity;
	private TextView myView;
	private Button mbutton;
	
	//Constructor
	public EditStoryActivityTestCases(){
		super("ualberta.g12.adventurecreator.EditStoryActivity", EditStoryActivity.class);
	}	
	
	//initializes things you want to be set for all tests (not necessary)
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        myEditStoryActivity = this.getActivity();
		Button mbutton = (Button) findViewById(R.id.saveStory)
    }

	/* Test Cases - all start with "test" */
	
	//Use Case 3, test 1/2
	public void testPublishStory() {
		addStory();
		assert click(mbutton);
	}
	
	//Use Case 3, test 2/2
	public void testPublishStory2() {
		assert click(mbutton);
	}
	
	// Use case 5, test 3/3
	public void testAddFragmentToStory(){
	    Button editStory = (Button) findViewById(R.id.editStory);
	    assert(click(editStory);
	}
	
}
