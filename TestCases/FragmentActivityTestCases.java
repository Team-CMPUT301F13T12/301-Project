package g12.projecttestcases;
/* Covers test cases for use cases:
 * 1, 14
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */


import ca.ualberta.cs.livojevi_notes.EntryListActivity;
import ca.ualberta.cs.livojevi_notes.ViewEntryActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class FragmentActivityTestCases extends ActivityInstrumentationTestCase2<FragmentActivity> {
	//declare activity and widgets
	private FragmentActivity myFragmentActivity;
	private TextView myView;
	private Button annotateButton;

	//Constructor
	public FragmentActivityTestCases(){
		super("ualberta.g12.adventurecreator.FragmentActivity", FragmentActivity.class);
	}	

	//initializes things you want to be set for all tests (not necessary)
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}

	/* Test Cases - all start with "test" */

	// Use Case 1, test 1/2
	public void testReadStoryFragments() {
		//Start Activity
		myFragmentActivity = this.getActivity();
		mView = (TextView) myFragmentActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.textviewID);


		String fragment = myView.getText();
		assertNotEquals(fragment, null);
	}

	// Use Case 2, test 2/2
	public void testReadNoFragments() {
		//Start Activity
		myFragmentActivity = this.getActivity();
		mView = (TextView) myFragmentActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.textviewID);

		String fragment = myView.getText();
		assertEquals(fragment, null);
	}

	// Use Case 14, test 1/1
	public void testannotateFragment() {
		Fragment someFragment = new Fragment();
		
		//set up intent to pass info to activity
		Intent FragmentIntent = new Intent(ChooseStoryActivity.this, FragmentActivity.class);
		FragmentIntent.putExtra("FragmentPass", someFragment);
		
		//pass intent to activity
		setActivityIntent(FragmentIntent);
		
		//Start Activity
		myFragmentActivity = this.getActivity();
		annotateButton = (Button) myFragmentActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.annotateButtonID);

		//click annotate button and confirm there was a listener
		assertTrue(annotateButton.performClick());
	}

}
