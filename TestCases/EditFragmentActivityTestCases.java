/* Covers test cases for use cases:
 * 5, 6, 7, 8, 11
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */

import android.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

public class EditFragmentActivityTestCases extends ActivityInstrumentationTestCase2<EditStoryActivity> {
	//declare activity and widgets
	private EditFragmentActivity myEditFragmentActivity;
	private EditText fragmentId;

	//Constructor
	public EditStoryActivityTestCases(){
		super("ualberta.g12.adventurecreator.EditFragmentActivity", EditFragmentActivity.class);
	}	

	//initializes things you want to be set for all tests (not necessary)
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		myEditFragmentActivity = this.getActivity();
	}

	/* Test Cases - all start with "test" */

	//Use Case 7, test 1/2 and Use Case 5
	public void testAddChoiceFragment() {

		fragmentId = (EditText) myEditFragmentActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.fragmentIdID);
		String title = "Test_Choice"; 
		String myFragmentId = myView.getText();
		Choice myChoice = new Choice(title,myFragmentId);
		addChoice(choice));
		AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));

	}

	//Use Case 7, test 2/2 and Use Case 5
	public void testMyselfAsChoiceFragment() {
		String title = "Test_Choice"; 
		String myFragmentId = myEditFragmentActivity.getFragId();
		Choice myChoice = new Choice(title,myFragmentId);
		addChoice(choice));
		AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));

	}

	//Use Case 8, test 1/3 and Use Case 5
	public void testAddChoiceToEmptyFragment() {
	
		StoryFragment EmptySF = new storyFragment;
		myEditFragmentActivity.changeStoryFragment(EmptySF);
		Choice myChoice = new Choice("test");
		addChoice(choice));
		AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));

	}

	//Use Case 8 2/3 and Use Case 5
	public void testAddChoiceToFragment() {


		String title = "Test_Choice"; 
		String myFragmentId = myEditFragmentActivity.getFragId(0);
		Choice myChoice = new Choice(title,myFragmentId);
		addChoice(choice));
		AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));

	}

	//Use Case 8, test 3/3
	public void testAddEmptyChoice() {

		Choice emptyChoice = new Choice();
		addChoice(new Choice());
		AssertTrue(myEditFragmentActivity.storyFragments.Choice.contains(emptyChoice);

	}

	// Use Case 6
	public void testAddPicture(){
	    Button addPicture = (Button) findViewById(R.id.add_picture);
	    assert(click(addPicture));
	}
	
	//Use Case 11
	public void testAddIllustration(){
		Button addIllustration = (Button) findViewById(R.id.add_illustration);
		assert(click(addIllustraion));
	}
	
}
