import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

//http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
//Referenced Oct 23, 2013 
public class FragmentActivityTestCases extends ActivityInstrumentationTestCase2<FragmentActivity> {
	//declare activity and widgets
	private FragmentActivity myFragmentActivity;
	private TextView myView;
	
	//Constructor
	public FragmentActivityTestCases(){
		super("ualberta.g12.adventurecreator.FragmentActivity", FragmentActivity.class);
	}	
	
	//initializes things you want to be set for all tests
	//(not necessary)
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        mView = (TextView) mActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.textviewID);
    }

	/* Test Cases - all start with "test" */
	
	public void testReadNoFragments {
		String fragment = myView.getText();
		assertEquals(fragment, null);
	}

}
