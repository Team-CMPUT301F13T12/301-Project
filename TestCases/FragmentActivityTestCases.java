/* Covers test cases for use cases:
 * 1
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */


import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FragmentActivityTestCases extends ActivityInstrumentationTestCase2<FragmentActivity> {
	//declare activity and widgets
	private FragmentActivity myFragmentActivity;
	private TextView myView;
	
	//Constructor
	public FragmentActivityTestCases(){
		super("ualberta.g12.adventurecreator.FragmentActivity", FragmentActivity.class);
	}	
	
	//initializes things you want to be set for all tests (not necessary)
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        mView = (TextView) mActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.textviewID);
    }

	/* Test Cases - all start with "test" */
	
	// Use Case 1, test 1/2
	public void testReadStoryFragments {
		String fragment = myView.getText();
		assertNotEquals(fragment, null);
	}
	
	// Use Case 2, test 2/2
	public void testReadNoFragments {
		String fragment = myView.getText();
		assertEquals(fragment, null);
	}

}
