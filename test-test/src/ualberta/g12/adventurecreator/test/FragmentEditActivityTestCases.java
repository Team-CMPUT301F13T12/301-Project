
package ualberta.g12.adventurecreator.test;

/* Covers test cases for use cases:
 * 5, 6, 7, 8, 11
 * 
 * http://stackoverflow.com/questions/10845937/how-to-do-junit-testing-in-android
 * Referenced Oct 23, 2013 for general format of Test class
 * 
 */

/*
 * NOTE: THIS CURRENTLY HITS A NULL POINTER BECAUSE EDITFRAGMENTACTIVITY IS TO STUPID TO KNOW TO DO WITHOUT ANY EXTRAS IN ITS INTENT
 * 
 */

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ContextMenu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.FragmentEditActivity;
import ualberta.g12.adventurecreator.views.FragmentPartAdapter;

public class FragmentEditActivityTestCases extends
        ActivityInstrumentationTestCase2<FragmentEditActivity> {

    // declare activity and widgets
    private FragmentEditActivity myEditFragmentActivity;
    private EditText editTitleText;
    private ListView fragmentPartListView;
    private ContextMenu menu;
    
    //declare objects
    private FragmentPartAdapter adapter;
    private StoryListController storyListController;
    private StoryList storyList;
    private OfflineIOHelper offlineHelper;
    private Story story;
    private Fragment fragment;
    private AdventureCreator ac;

    public FragmentEditActivityTestCases() {
        super(FragmentEditActivity.class);
        // TODO Auto-generated constructor stub
    }

    // initializes things you want to be set for all tests (not necessary)
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // get widget references
        fragmentPartListView = (ListView) myEditFragmentActivity.findViewById(R.id.FragmentPartList);
        editTitleText = (EditText) myEditFragmentActivity.findViewById(R.id.fragmentTitle);
        menu = (ContextMenu) myEditFragmentActivity.findViewById(R.menu.fragment_part_menu);

        ac = new AdventureCreator();
        
        //set up a story to save
        fragment = new Fragment();
        fragment.setTitle("Start Frag");
        story = new Story();
        List<Fragment> fragments = story.getFragments();
        fragments.add(fragment);
        storyList = new StoryList();
        storyList.addStory(story);
        storyListController = AdventureCreator.getStoryListController();
          
        //save story
        storyListController.saveOfflineStories(storyList);

        //set up intent
        Intent FragmentIntent = new Intent();
        FragmentIntent.putExtra("Mode", "Edit");
        FragmentIntent.putExtra("StoryPos", 0);
        FragmentIntent.putExtra("FragmentPos", 0);
        setActivityIntent(FragmentIntent);

        myEditFragmentActivity = getActivity();

        //recreate activity
        myEditFragmentActivity.recreate();
    }
    /**
     * Tests that addImage() successfully adds an image to a fragment.
     */
    public void testAddImagePopups(){
        fragmentPartListView.performLongClick();
        menu.performIdentifierAction(R.id.insertIllustration, 0);
        assertTrue(myEditFragmentActivity.getCurrentFocus().getClass().toString().equals("AlertDialog.Builder"));
    }
//
//    // Use Case 7, test 1/2 and Use Case 5
//    public void testAddChoiceFragment() {
//
//        /*
//         * fragmentId = (EditText) myEditFragmentActivity
//         * .findViewById(ualberta.g12.adventurecreator.R.id.fragmentPartEdit);
//         * String title = "Test_Choice";
//         */
//        // String myFragmentId = myView.getText();
//        // Choice myChoice = new Choice(title,myFragmentId);
//        // addChoice(choice));
//        // AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));
//        assertTrue("testAddChoiceFragment is not yet implemented", false);
//
//    }
//
//    // Use Case 7, test 2/2 and Use Case 5
//    public void testMyselfAsChoiceFragment() {
//        // String title = "Test_Choice";
//        // String myFragmentId = myEditFragmentActivity.getFragId();
//        // Choice myChoice = new Choice(title,myFragmentId);
//        // addChoice(choice));
//        // AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));
//        assertTrue("testMyselfAsChoiceFragment is not yet implemented", false);
//
//    }
//
//    // Use Case 8, test 1/3 and Use Case 5
//    public void testAddChoiceToEmptyFragment() {
//
//        // Fragment EmptySF = new Fragment();
//        // myEditFragmentActivity.changeStoryFragment(EmptySF);
//        // Choice myChoice = new Choice();
//        // addChoice(myChoice);
//        // AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));
//        assertTrue("testAddChoiceToEmptyFragment is not yet implemented", false);
//
//    }
//
//    // Use Case 8 2/3 and Use Case 5
//    public void testAddChoiceToFragment() {
//
//        // String title = "Test_Choice";
//        // String myFragmentId = myEditFragmentActivity.getFragId(0);
//        // Choice myChoice = new Choice(title,myFragmentId);
//        // addChoice(choice));
//        // AssertTrue(myEditFragmentActivity.storyFragments.Choices.get(myFragmentId).equals(myChoice));
//        assertTrue("testAddChoiceToFragment is not yet implemented", false);
//
//    }
//
//    // Use Case 8, test 3/3
//    public void testAddEmptyChoice() {
//
//        // addChoice(new Choice());
//        // AssertTrue(myEditFragmentActivity.storyFragments.Choice.contains(emptyChoice);
//        assertTrue("testAddEmptyChoice is not yet implemented", false);
//
//    }
//
//    // Use Case 6
//    // user should be have ability to add a picture
//    public void testAddPicture() {
//        Button addPicture = (Button) myEditFragmentActivity
//                .findViewById(ualberta.g12.adventurecreator.R.id.add_fragment);
//        assert (addPicture.performClick());
//        assertTrue("testAddPicture is not yet implemented", false);
//    }
//
//    // Use Case 11 1/2
//    // User enables adding illustrations by adding the first one to a fragment
//    public void testEnableIllustration() {
//        Button addIllustration = (Button) myEditFragmentActivity
//                .findViewById(ualberta.g12.adventurecreator.R.id.add_fragment);
//        // ImageView ill1 = (ImageView)
//        // findViewById(ualberta.g12.adventurecreatorR.id.ill1ID);
//        addIllustration.performClick();
//        // should be an illustration
//        // assertNotNull(ill.getDrawable());\
//        assertTrue("testEnableIllustration is not yet implemented", false);
//    }
//
//    // Use Case 11 2/2
//    // Default is illustrations are not enabled, they become enabled by
//    // adding the first illustration to a fragment
//    public void testDisableIllustration() {
//        // Button addIllustration = (Button)
//        // findViewById(R.id.add_illustration);
//        // ImageView ill1 = (ImageView) findViewById(R.id.ill1ID);
//        // should be no illustration
//        // assertNull(ill.getDrawable());
//        assertTrue("testDisableIllustration is not yet implemented", false);
//    }
//
//    /* Test Cases - all start with "test" */
//
//    // Use Case 1, test 1/2
//    public void testReadStoryFragments() {
//        Story UserStory = new Story();
//        StoryList sl = AdventureCreator.getStoryList();
//        sl.addStory(UserStory);
//
//        final Instrumentation inst = getInstrumentation();
//        final IntentFilter intentFilter = new IntentFilter();
//
//        ActivityMonitor monitor = inst.addMonitor(intentFilter, null, false);
//        assertEquals(1, monitor.getHits());
//    }
//
//    // Use Case 1, test 2/2
//    public void testReadNoFragments() {
//
//        final Instrumentation inst = getInstrumentation();
//        final IntentFilter intentFilter = new IntentFilter();
//
//        ActivityMonitor monitor = inst.addMonitor(intentFilter, null, false);
//        assertEquals(1, monitor.getHits());
//    }
//
//    // Use Case 14, test 1/1
//    public void testAnnotateFragment() {
//        /*
//         * Story someStory = new Story(); Fragment someFragment = new
//         * Fragment(); someStory.setAuthor("someoneElse");
//         * someStory.addFragment(someFragment);
//         */
//
//        // downloads story
//        // downloadStory(someStory);
//
//        // set up intent to pass info to activity
//        /*
//         * Intent FragmentIntent = new Intent(ChooseStoryActivity.this,
//         * FragmentActivity.class); FragmentIntent.putExtra("StoryPass",
//         * someStory); FragmentIntent.putExtra("FragmentPass", someFragment);
//         */
//
//        // pass intent to activity
//        // setActivityIntent(FragmentIntent);
//
//        // Start Activity
//        myEditFragmentActivity = this.getActivity();
//        /*
//         * annotateButton = (Button) myFragmentActivity
//         * .findViewById(ualberta.g12
//         * .adventurecreator.FragemntActivity.R.id.annotateButtonID);
//         */
//
//        // check that story is written by another user
//        // assertFalse(someStory.getAuthor().equals(getCurrentUserName()));
//
//        // click annotate button and confirm there was a listener
//        // assertTrue(annotateButton.performClick());
//
//        // int prevAnnotationLength =
//        // someFragment.getAnnotation().getPhotos().size();
//
//        // uploads a photo to annotation
//        // Picture somePic = new Picture();
//        // AddAnnotation(someFragment, somePic);
//
//        // assert a new photo has been added
//        // assertTrue(prevAnnotationLength <
//        // someFragment.getAnnotation().getPhotos().size());
//        assertTrue("testAnnotateFragment has not been implemented", false);
//    }
//
//    // Use Case 15, test 1/1
//    public void testRetakePhoto() {
//        Story someStory = new Story();
//        Fragment someFragment = new Fragment();
//        someStory.setAuthor("someoneElse");
//        someStory.getFragments().add(someFragment);
//
//        // downloads story
//        // downloadStory(someStory);
//
//        // set up intent to pass info to activity
//        // Intent FragmentIntent = new Intent(ChooseStoryActivity.this,
//        // FragmentActivity.class);
//        /*
//         * FragmentIntent.putExtra("StoryPass", someStory);
//         * FragmentIntent.putExtra("FragmentPass", someFragment); // pass intent
//         * to activity setActivityIntent(FragmentIntent);
//         */
//
//        // Start Activity
//        myEditFragmentActivity = this.getActivity();
//        /*
//         * annotateButton = (Button) myFragmentActivity
//         * .findViewById(ualberta.g12
//         * .adventurecreator.FragemntActivity.R.id.annotateButtonID); camera =
//         * myFragmentActivity.getCamera();
//         */
//
//        // click annotate button and confirm there was a listener
//        // assertTrue(annotateButton.performLongClick());
//
//        // take a photo
//        // camera.takePicture(null, null, new Photo());
//
//        // if camera is not null the activity hasn't released the
//        // camera yet meaning the user may take another picture
//        // assertTrue(camera != null);
//
//        // take a second photo
//        // camera.takePicture(null, null, new Photo());
//
//        // if camera is still not null, the activity hasn't released the
//        // camera, meaning the user may take yet another picture
//        // assertTrue(camera != null);
//        assertTrue("testRetakePhoto has not been implemented", false);
//    }
//
//    // test case 10
//    public void testPostPhotoToAnnotateFragment() {
//        // start activity
//        myEditFragmentActivity = this.getActivity();
//        /*
//         * annotateButton = (Button) myFragemntActivity
//         * .findViewById(ualberta.g12
//         * .adventurecreator.FragemntActivity.R.id.annotateButtonID); // click
//         * annotate button asserTrue(annotateButon.performClick()); int
//         * prevAnnotationLenght =
//         * someFragment.getAnnotation().getPhotos().size(); // upload a phote to
//         * annotate the fragment Picture somePicture = new Picture();
//         * AddAnnotation(someFragment, somePicture); // confirm a new phote
//         * added assertTrue(prevAnnotationLenght <
//         * someFragment.getAnnotation().getPhotos().size());
//         */
//        assertTrue("testPostPhotoToAnnotateFragment has not yet been implemented", false);
//    }
//
//    // test case 12
//    public void testStoreFragment() {
//        // Fragment someFragment = new Fragment();
//        // start activity
//        myEditFragmentActivity = this.getActivity();
//
//        // choice a store choose from fragment choices
//        /*
//         * myChooseFragmentActivity = this.getActivity(); availableFragmentList
//         * =(ListView)
//         * myChooseFragmentActvity.findViewById(ualberta.g12.adventurecreator
//         * .ChooseStoryActivity.R.id.fragmetnListID); fragmentListPopupMenu =
//         * (PopupMenu)
//         * myChooseFragmentActivity.findViewById(ualberta.g12.adventurecreator
//         * .ChooseStoryActivity.R.id.fragmentListPopMenuID); // popup menu open
//         * for selection availableFragmentList.performLongClick(); //store
//         * option if available MenuItem storeOption =
//         * fragmentListPopupMenu.getMenu().getItem(1); // assert fragemnt stored
//         * asserTrue(storOption.getTitle().equals("Store");
//         */
//        assertTrue("testStoryFragment has not yet been implemented", false);
//    }

}   
