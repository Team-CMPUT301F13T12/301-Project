
package ualberta.g12.adventurecreator;

import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * View for editing a Story using a StoryController.<br>
 * This particular Activity should only be started via an intent from the main
 * activity that has as an extra either the ID of the story to edit or
 * NEW_STORY_ID
 */
public class StoryEditActivity extends Activity implements SView<Story> {

    private Story story;
    private StoryController storyController;

    // Story ID Constants
    public static final String INTENT_STORY_ID = "storyid";
    public static final int INVALID_STORY_ID = -1;

    //edit or add contants
    public static final int EDIT = 0;
    public static final int ADD = 1;
    
    // Logging info
    private static final String TAG = "StoryEditActivity";
    private static final boolean DEBUG_LOG = true;

    // UI Elements
    private EditText titleText;
    private EditText authorText;
    private ListView lView;
    
    private FragmentListArrayAdapter adapter;
    private int storyId;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_story_editor);

        // Load our story from the intent

        Intent i = getIntent();
        int id = i.getIntExtra(INTENT_STORY_ID, INVALID_STORY_ID);

        if (DEBUG_LOG)
            Log.d(TAG, String.format("Started with story id: %d", id));

        if (id == INVALID_STORY_ID) {
            // There was no id passed to us in this intent
            // Also what about on an orientation change
            // TODO: What should we do here?
            Log.w(TAG, "We were created withoug being passed a story to load.");
        }
        StoryList sl = AdventureCreatorApplication.getStoryList();
        story = sl.getStoryById(id);

        if (story == null) {
            // TODO: What should we do here?
            Log.w(TAG, String.format("There was no story with id: %d", id));
        }

        
        // TODO: Set up our storyController
        storyController = AdventureCreatorApplication.getStoryController();

        // TODO: Load our story contents into fields (ListView of Fragments)
        titleText = (EditText) findViewById(R.id.story_editor_title_edit);
        authorText = (EditText) findViewById(R.id.story_editor_author_edit);
        // TODO: Update listview
        fragmentList = story.getFragments();
        adapter = new FragmentListArrayAdapter(this, R.layout.listview_fragment_list, fragmentList);
        lView = (ListView) findViewById(R.id.story_editor_listview);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{

				openEditFragment(0, position );

			}
		}
				);
        // TODO: Setup Adapter
        updateUiElements();
        // TODO: Set up all listeners
        setUpOnClickListeners();
        
        storyId = id;
    }
    

    


    /** Updates all of the Ui Elements for this Activity */
    private void updateUiElements() {
        titleText.setText(story.getStoryTitle());
        authorText.setText(story.getAuthor());

        // TODO: update listView
        fragmentList = story.getFragments();
        adapter.notifyDataSetChanged();
    }

    /**
     * Sets up this activities onClickListeners:
     * <p>
     * saveButton: Save the current story which updates list of stories<br>
     * addPageButton: Opens EditFragmentActivity to create a new Fragment
     */
    private void setUpOnClickListeners() {
        /*
         * TODO: SaveButton - Tell the StoryListController to update the
         * StoryList Model as a story has been changed or created
         */

        /*
         * TODO: AddPageButton - Use the StoryController to add another Fragment
         * which might update this story and then Open EditFragmentActivity with
         * and Intent that has extras of a NEW_FRAGMENT_ID. Model should tell us
         * to update.
         */

    }

    /*
     * Set up the context menu for the ListViews elements. This is probably not
     * going to be done in this method however this is used to remind us it
     * needs to be done
     */
    private void setUpContextMenu() {
        // TODO: ListItemClick: Same as AddPageButton except our extra will be
        // the ID of the intent

        // TODO: DeleteFragment: Use the StoryController to remove the fragment
        // with this id. Model should tell us to update
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.story_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.add_fragment:
                // TODO: Need to Pass the ID of the fragment to edit
                Intent intent = new Intent(this, EditFragmentActivity.class);
                intent.putExtra("EditType", "Add");
                intent.putExtra("Id", storyId);
                startActivity(intent);
                return true;
            case R.id.save_story:
                // Save values
                storyController.setTitle(titleText.getText().toString(), story);
                storyController.setAuthor(authorText.getText().toString(), story);

                // Tell StoryList to update Story
                StoryListController slc = AdventureCreatorApplication.getStoryListController();
                if (DEBUG_LOG)
                    Log.d(TAG, String.format("SLC is null:%b story is null: %b", (slc == null),
                            (story == null)));
                slc.updateStoryWithId(story.getId(), story);
                // Leave activity
                // TODO: Should we stay in this activity?
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Story model) {
        // TODO: Update our local story variable
    	updateUiElements();
    	
        // TODO: Reload value from our story into fields - notify adapter our
        // story has changed

    }
    

	private void openEditFragment(int type  , int position ){
		Fragment selectedFrag = fragmentList.get(position);
		int id = selectedFrag.getId();
        Intent intent = new Intent(this, EditFragmentActivity.class);
        intent.putExtra("EditType", "Edit");
        intent.putExtra("StoryId",storyId);
        intent.putExtra("FragmentId", id);
        //TODO need as we dont have a way to pass find fragment by id yet
        intent.putExtra("pos", position);
        startActivity(intent);
	}

}
