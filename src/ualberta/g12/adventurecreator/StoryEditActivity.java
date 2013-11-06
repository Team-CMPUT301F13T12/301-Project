
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

    private StoryList storyList;
    private Story story;
    private StoryController storyController = new StoryController();

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
    

    private OfflineIOHelper offlineHelper;
    private FragmentListArrayAdapter adapter;
    private int storyId, storyPos;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_story_editor);

        // Load our story from the intent
        Intent i = getIntent();
        int id = i.getIntExtra(INTENT_STORY_ID, INVALID_STORY_ID);
        story = (Story)i.getSerializableExtra("Story");
        storyList = (StoryList)i.getSerializableExtra("StoryList"); 
        storyPos = (Integer)i.getSerializableExtra("StoryPos"); 

        //get widget references
        titleText = (EditText) findViewById(R.id.story_editor_title_edit);
        authorText = (EditText) findViewById(R.id.story_editor_author_edit);
        lView = (ListView) findViewById(R.id.story_editor_listview);
        
        if (DEBUG_LOG)
            Log.d(TAG, String.format("Started with story id: %d", id));

        if (id == INVALID_STORY_ID) {
            // There was no id passed to us in this intent
            // Also what about on an orientation change
            // TODO: What should we do here?
            Log.w(TAG, "We were created withoug being passed a story to load.");
        }

        if (story == null) {
            // TODO: What should we do here?
            Log.w(TAG, String.format("There was no story with id: %d", id));
        }

        
//        // TODO: Set up our storyController
//        storyController = AdventureCreatorApplication.getStoryController();
        
        // Set up all listeners
        setUpOnClickListeners();
    }
    
    /** Updates all of the Ui Elements for this Activity */
    @Override
    protected void onStart() {
        super.onStart();
        story = storyList.getAllStories().get(storyPos);
        
        //update title and author
        titleText.setText(story.getStoryTitle());
        authorText.setText(story.getAuthor());

        //populate the fragment list
        fragmentList = story.getFragments();
        adapter = new FragmentListArrayAdapter(this, R.layout.listview_fragment_list, fragmentList);
        lView.setAdapter(adapter);      
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

        lView.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Fragment selectedFrag = fragmentList.get(position);
                openEditFragment(selectedFrag, position);
            }
        });
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
                // Create and add new fragment
                Fragment newFrag = new Fragment();
                int fragPos = story.getFragments().size();
                storyController.addFragment(story, newFrag);
                //pass new fragment to edit fragment intent
                openEditFragment(newFrag, fragPos);
                return true;
                
            case R.id.save_story:
                // Save values
                saveChanges();
                                
                // Leave activity
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Story model) {
        // Update our local story variable   	
        // Reload value from our story into fields - notify adapter our
        // story has changed
        onStart();

    }
    

	private void openEditFragment(Fragment frag, int fragPos){
	    System.out.println("entered opoenedit");
	    //save before leaving activity
        saveChanges();
        
        Intent intent = new Intent(this, EditFragmentActivity.class);
        intent.putExtra("Mode", "Edit");
        intent.putExtra("StoryList", storyList);
        intent.putExtra("StoryPos",storyPos);
        intent.putExtra("Story", story);
        intent.putExtra("FragmentPos", fragPos);
        intent.putExtra("Fragment", frag);
        
        Log.d("This",String.format("The story id was: %d", storyId));
        startActivity(intent);
	}

	 private void saveChanges(){
        //save any changes
	     story.setStoryTitle(titleText.getText().toString());
         story.setAuthor(authorText.getText().toString());
         
	    storyList.getAllStories().set(storyPos, story);
        offlineHelper = new OfflineIOHelper(StoryEditActivity.this);
        offlineHelper.saveOfflineStories(storyList);
    }
}
