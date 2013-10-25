package g12.projecttestcases;
/* Covers test cases for use cases:
 * 1, 14, 15
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */

import ca.ualberta.cs.livojevi_notes.EntryListActivity;
import ca.ualberta.cs.livojevi_notes.ViewEntryActivity;
import android.content.Intent;
import android.hardware.Camera;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class FragmentActivityTestCases extends ActivityInstrumentationTestCase2<FragmentActivity> {
	//declare activity and widgets
	private FragmentActivity myFragmentActivity;
	private TextView myView;
	private Button annotateButton;
	private Camera camera;

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
		Story someStory = new Story();
		Fragment someFragment = new Fragment();
		someStory.setAuthor("someoneElse");
		someStory.addFragment(someFragment);
		
		//downloads story
		downloadStory(someStory);
		
		//set up intent to pass info to activity
		Intent FragmentIntent = new Intent(ChooseStoryActivity.this, FragmentActivity.class);
		FragmentIntent.putExtra("StoryPass", someStory);
		FragmentIntent.putExtra("FragmentPass", someFragment);
		
		//pass intent to activity
		setActivityIntent(FragmentIntent);
		
		//Start Activity
		myFragmentActivity = this.getActivity();
		annotateButton = (Button) myFragmentActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.annotateButtonID);

		//check that story is written by another user
		assertFalse(someStory.getAuthor().equals(getCurrentUserName()));
		
		//click annotate button and confirm there was a listener
		assertTrue(annotateButton.performClick());
		
		int prevAnnotationLength = someFragment.getAnnotation().getPhotos().size(); 
		
		//uploads a photo to annotation
		Picture somePic = new Picture();
		AddAnnotation(someFragment, somePic);
		
		//assert a new photo has been added
		assertTrue(prevAnnotationLength < someFragment.getAnnotation().getPhotos().size());
	}
	
	// Use Case 15, test 1/1
		public void testretakePhoto() {
			Story someStory = new Story();
			Fragment someFragment = new Fragment();
			someStory.setAuthor("someoneElse");
			someStory.addFragment(someFragment);
			
			//downloads story
			downloadStory(someStory);
			
			//set up intent to pass info to activity
			Intent FragmentIntent = new Intent(ChooseStoryActivity.this, FragmentActivity.class);
			FragmentIntent.putExtra("StoryPass", someStory);
			FragmentIntent.putExtra("FragmentPass", someFragment);
			
			//pass intent to activity
			setActivityIntent(FragmentIntent);
			
			//Start Activity
			myFragmentActivity = this.getActivity();
			annotateButton = (Button) myFragmentActivity.findViewById(ualberta.g12.adventurecreator.FragemntActivity.R.id.annotateButtonID);
			camera = myFragmentActivity.getCamera();
			
			//click annotate button and confirm there was a listener
			assertTrue(annotateButton.performLongClick());
			
			//take a photo
			camera.takePicture(null, null, new Photo());
			
			//if camera is not null the activity hasn't released the
			//camera yet meaning the user may take another picture
			assertTrue(camera != null);	
			
			//take a second photo
			camera.takePicture(null, null, new Photo());
			
			//if camera is still not null, the activity hasn't released the
			//camera, meaning the user may take yet another picture
			assertTrue(camera != null);
		}

}
