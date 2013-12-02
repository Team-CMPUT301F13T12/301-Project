package ualberta.g12.adventurecreator.test;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.views.ChoiceEditActivity;
import ualberta.g12.adventurecreator.views.FragmentPartAdapter;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.EditText;
import ualberta.g12.adventurecreator.R;

public class ChoiceEditActivityTestCases extends ActivityInstrumentationTestCase2<ChoiceEditActivity> {

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
	    private FragmentController fc;
	    private StoryController sc;
	    private StoryListController slc;
	
	public ChoiceEditActivityTestCases() {
		super(ChoiceEditActivity.class);
	}
	
	@Override
	protected void setUp() {
		//super.setUp();
		
        s = new Story("wow", "hey");
        f = new Fragment();
        fc = AdventureCreator.getFragmentController();
        fc.setTitle(f, "TITLE");
        sc = AdventureCreator.getStoryController();
        sc.addFragment(s, f);
        slc = AdventureCreator.getStoryListController();
        slc.addStory(s);
		Intent intent= new Intent();
        intent.putExtra("StoryPos", 0);
        intent.putExtra("FragmentPos", 0);
        intent.putExtra("ChoicePos", 0);
		setActivityIntent(intent);
		myActivity = getActivity();

	}
	
	/**
	 * Tests for use case 7 Connecting 2 story fragments as a choice
	 * will test if choice has two objects linked
	 */
	@UiThreadTest
	public void testAddChoiceConnect(){
		

		//EditText titleET = (EditText) myActivity.findViewById(R.id.choiceBody);
		
	}
	
	

}
