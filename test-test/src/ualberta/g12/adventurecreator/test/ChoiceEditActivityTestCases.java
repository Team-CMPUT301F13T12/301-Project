
package ualberta.g12.adventurecreator.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.ChoiceEditActivity;
import ualberta.g12.adventurecreator.views.FragmentPartAdapter;

import java.util.List;

public class ChoiceEditActivityTestCases extends
        ActivityInstrumentationTestCase2<ChoiceEditActivity> {

    private ChoiceEditActivity myActivity;
    private FragmentPartAdapter adapter;
    private StoryListController storyListController;
    private StoryList storyList;
    private OfflineIOHelper offlineHelper;
    private Story story;
    private Fragment fragment;
    private AdventureCreator ac;
    private Fragment f;
    private Story s;
    private Choice c;
    private FragmentController fc;
    private StoryController sc;
    private StoryListController slc;

    private static final String TAG = "ChoiceEditActivity";

    public ChoiceEditActivityTestCases() {
        super(ChoiceEditActivity.class);
    }

    @Override
    protected void setUp() {
        // super.setUp();
        StoryList sl = AdventureCreator.getStoryList();

        s = new Story("wow", "hey");
        f = new Fragment();
        c = new Choice();
        fc = AdventureCreator.getFragmentController();
        fc.setTitle(f, "TITLE");

        sc = AdventureCreator.getStoryController();
        sc.addFragment(s, f);
        
        sc.setFragmentAtLocation(s, 0, f);

        Log.d(TAG, String.format("This many parts: %d", f.getParts().size()));

        fc.addNewFragmentPart(f, "c", 0);

        sc.setFragmentAtLocation(s, 0, f);
        Log.d(TAG, String.format("This many parts: %d", f.getParts().size()));

        sc.setFragmentAtLocation(s, 0, f);

        slc = AdventureCreator.getStoryListController();
        slc.addStory(s);

        Story ss1 = sl.getAllStories().get(0);
        Story ss2 = sl.getAllStories().get(1);
        
        //fail("s1: " + ss1.getTitle() + " ss2: " + ss2.getTitle());
        
        
        
        // Let's do what its gonna do
        Story s1 = sl.getStoryAtPos(0);
        assertNotNull("Story was null", s1);
        List<Fragment> lf = s1.getFragments();
        Log.d(TAG , String.format("%d fragments", lf.size()));

        //fail("fragments: " + lf.size());
        
        Fragment f1 = s1.getFragmentAtPos(0);
        assertNotNull("Fragment was null", f1);

        Intent intent = new Intent();
        intent.putExtra("StoryPos", 0);
        intent.putExtra("FragmentPos", 0);
        intent.putExtra("ChoicePos", 0);

        setActivityIntent(intent);

        myActivity = getActivity();

    }

    /**
     * Tests for use case 7 Connecting 2 story fragments as a choice will test
     * if choice has two objects linked
     */
    @UiThreadTest
    public void testAddChoiceConnect() {

        // EditText titleET = (EditText)
        // myActivity.findViewById(R.id.choiceBody);

    }

}
