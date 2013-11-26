
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

import java.io.File;
import java.text.SimpleDateFormat;
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

    // edit or add constants
    public static final int EDIT = 0;
    public static final int ADD = 1;

    // Logging info
    // private static final String TAG = "StoryEditActivity";
    // private static final boolean DEBUG_LOG = true;

    // UI Elements
    private EditText titleText;
    private EditText authorText;
    private ListView lView;

    private OfflineIOHelper offlineHelper;
    private FragmentListArrayAdapter adapter;
    private int storyId, storyPos;
    private List<Fragment> fragmentList;

    // Logging
    private static final String TAG = "StoryEditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_story_editor);

        // Load our story from the intent
        Intent i = getIntent();
        storyPos = (Integer) i.getSerializableExtra("StoryPos");

        offlineHelper = AdventureCreator.getOfflineIOHelper();

        storyList = AdventureCreator.getStoryList();

        story = storyList.getAllStories().get(storyPos);

        // get widget references
        titleText = (EditText) findViewById(R.id.story_editor_title_edit);
        authorText = (EditText) findViewById(R.id.story_editor_author_edit);
        titleText.setText(story.getTitle());
        authorText.setText(story.getAuthor());

        lView = (ListView) findViewById(R.id.story_editor_listview);
        fragmentList = story.getFragments();

        adapter = new FragmentListArrayAdapter(this, R.layout.listview_fragment_list, fragmentList);
        lView.setAdapter(adapter);

        // Set up all listeners
        setUpOnClickListeners();
    }

    /** Updates all of the Ui Elements for this Activity */
    @Override
    protected void onStart() {
        super.onStart();
        story.addView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        story.deleteView(this);
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

                // pass new fragment to edit fragment intent
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
        // update title and author
        titleText.setText(model.getTitle());
        authorText.setText(model.getAuthor());

        story = model;
        Log.d(TAG, "Updating");
        // populate the fragment list
        adapter.notifyDataSetChanged();
    }

    /**
     * opens the selected story fragment using intents
     * 
     * @param fragPos position of the selected fragment in the listview
     */
    private void openEditFragment(int fragPos) {
        // save before leaving activity
        saveChanges();

        Intent intent = new Intent(this, FragmentEditActivity.class);
        intent.putExtra("Mode", "Edit");
        intent.putExtra("StoryPos", storyPos);
        intent.putExtra("FragmentPos", fragPos);

        Log.d("This", String.format("The story id was: %d", storyId));
        startActivity(intent);
    }

    /**
     * saves any changes that have been modified to a story
     */
    private void saveChanges() {
        int oldId = story.getId();

        // save any changes
        storyController.setTitle(story, titleText.getText().toString());
        storyController.setAuthor(story, authorText.getText().toString());

        StoryListController slc = AdventureCreator.getStoryListController();
        slc.setStory(story, storyPos);

        if (oldId != story.getId()){
            // Setup or update story folder
            
            //following line modified from https://groups.google.com/forum/#!topic/android-developers/YjGcve7s5CQ
            //by Derek
            CharSequence appName = this.getResources().getText(this.getResources().getIdentifier("app_name", "string", this.getPackageName()));
            File appFolder = new File(Environment.getExternalStorageDirectory().toString(), appName.toString());
            File oldStoryFolder = new File(appFolder.getAbsolutePath(), Integer.toString(oldId));
            File newStoryFolder = new File(appFolder.getAbsolutePath(), Integer.toString(story.getId()));
            
            if (oldStoryFolder.exists()) {
                // change old story folder to follow new story id
                oldStoryFolder.renameTo(newStoryFolder);                
            } else {
                // or Just create new story id folder
                newStoryFolder.mkdirs();
            }
        }

        offlineHelper.saveOfflineStories(storyList);
    }
}
