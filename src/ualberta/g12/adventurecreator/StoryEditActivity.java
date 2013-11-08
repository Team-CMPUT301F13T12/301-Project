
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

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
    

    private OfflineIOHelper offlineHelper = new OfflineIOHelper(StoryEditActivity.this);
    private FragmentListArrayAdapter adapter;
    private int storyId, storyPos;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_story_editor);

        // Load our story from the intent
        Intent i = getIntent(); 
        storyPos = (Integer)i.getSerializableExtra("StoryPos"); 

        //get widget references
        titleText = (EditText) findViewById(R.id.story_editor_title_edit);
        authorText = (EditText) findViewById(R.id.story_editor_author_edit);
        lView = (ListView) findViewById(R.id.story_editor_listview);
        
        // Set up all listeners
        setUpOnClickListeners();
    }
    
    /** Updates all of the Ui Elements for this Activity */
    @Override
    protected void onStart() {
        super.onStart();
        update(story);

    }
    
    @Override
    public void onResume() {
        super.onResume();
        lView.invalidateViews();

    }

    /**
     * Sets up this activities onClickListeners:
     * <p>
     * saveButton: Save the current story which updates list of stories<br>
     * addPageButton: Opens EditFragmentActivity to create a new Fragment
     */
    private void setUpOnClickListeners() {

        lView.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Fragment selectedFrag = fragmentList.get(position);
                openEditFragment(position);
            }
        });
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
                openEditFragment(fragPos);
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
        storyList = offlineHelper.loadOfflineStories();
        
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
     * opens the selected story fragment using intents
     * 
     * @param fragPos   position of the selected fragment in the listview
     */
	private void openEditFragment(int fragPos){
	    //save before leaving activity
        saveChanges();
        
        Intent intent = new Intent(this, EditFragmentActivity.class);
        intent.putExtra("Mode", "Edit");
        intent.putExtra("StoryPos",storyPos);
        intent.putExtra("FragmentPos", fragPos);
        
        Log.d("This",String.format("The story id was: %d", storyId));
        startActivity(intent);
	}

	/**
	 * saves any changes that have been modified to a story 
	 * 
	 */
	 private void saveChanges(){
        //save any changes
	     story.setStoryTitle(titleText.getText().toString());
         story.setAuthor(authorText.getText().toString());
         
	    storyList.getAllStories().set(storyPos, story);
        offlineHelper.saveOfflineStories(storyList);
    }
}
