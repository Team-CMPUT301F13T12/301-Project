import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

//http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
//Referenced Oct 23, 2013 
public class ChooseStoryActivityTestCases extends ActivityInstrumentationTestCase2<ChooseStoryActivity> {
	//declare activity and widgets
	private ChooseStoryActivity myChooseStoryActivity;
	private TextView myView;
	
	//Constructor
	public ChooseStoryActivityTestCases(){
		super("ualberta.g12.adventurecreator.ChooseStoryActivity", ChooseStoryActivity.class);
	}	
	
	//initializes things you want to be set for all tests
	//(not necessary)
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        mView = (TextView) mActivity.findViewById(ualberta.g12.adventurecreator.ChooseStoryActivity.R.id.textviewID);
    }

	/* Test Cases - all start with "test" */
	
	public void testBrowseStoryList {
		addMultiStory();
		assertTrue(scrollable(mView.getText()));
	}

}
