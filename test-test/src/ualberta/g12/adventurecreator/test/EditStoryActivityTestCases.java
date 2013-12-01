package ualberta.g12.adventurecreator.test;
/* Covers test cases for use cases:
 * 3, 5
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */

import android.test.ActivityInstrumentationTestCase2;

import ualberta.g12.adventurecreator.views.StoryEditActivity;

public class EditStoryActivityTestCases extends ActivityInstrumentationTestCase2<StoryEditActivity> {
        //declare activity and widgets
        //private StoryEditActivity myEditStoryActivity;
        //private TextView myView;
        //private Button mbutton;
        
        //Constructor
        public EditStoryActivityTestCases(){
                super(StoryEditActivity.class);
        }        
        
        //initializes things you want to be set for all tests (not necessary)
        @Override
    protected void setUp() throws Exception {
        super.setUp();
        /*myEditStoryActivity = this.getActivity();
                Button mbutton = (Button) findViewById(ualberta.g12.adventurecreator.R.id.saveStory);*/
    }

        /* Test Cases - all start with "test" */
        
        //Use Case 3, test 1/2
        public void testPublishStory() {
                
                //add story info 
                //addStory();
                
                //check whether or not the publish button successfully executes
                //assert click(mbutton);
            assertTrue("testPublishStory has not been implemented", false);
        }
        
        //Use Case 3, test 2/2
        public void testPublishEmptyStory() {
                
                //checks if story with no info can successfull publish
                //assert click(mbutton);
            assertTrue("testPublishEmptyStory has not been implemented", false);
        }
        
        // Use case 5, test 3/3
        public void testAddFragmentToStory(){
            //Button editStory = (Button) findViewById(R.id.editStory);
            //assert(click(editStory);
            assertTrue("testAddFragmentToStory has not been implemented", false);
        }
        
}