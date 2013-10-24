import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

//http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
//Referenced Oct 23, 2013 
public class EditStoryActivityTestCases extends ActivityInstrumentationTestCase2<EditStoryActivity> {
	//declare activity and widgets
	private EditStoryActivity myEditStoryActivity;
	private TextView myView;
	
	//Constructor
	public EditStoryActivityTestCases(){
		super("ualberta.g12.adventurecreator.EditStoryActivity", EditStoryActivity.class);
	}	
	
	//initializes things you want to be set for all tests
	//(not necessary)
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
		Button mbutton = (Button) findViewById(R.id.saveStory)
    }

	/* Test Cases - all start with "test" */
	
	public void testPublishStory {
		assert click(mbutton);
	}

}
