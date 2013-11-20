
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Activity displayed for the start of the application. Allows the user to
 * browse through available stories, and edit or read those stories depending on
 * what mode they are in.
 */
public class MainActivity extends Activity implements LView<StoryList>, OnItemClickListener {

    private List<Story> stories;
    private static StoryList storyList;
    private static final boolean DEBUG_LOG = true;
    private static final String TAG = "MainActivity";
    private ListView listView;
    private StoryListArrayAdapter adapter;
    private StoryListController storyListController = AdventureCreatorApplication
            .getStoryListController();
    private StoryController storyController = AdventureCreatorApplication.getStoryController();

    private static final String SHARED_PREF_IS_AUTHOR_FLAG = "isAuthor";
    private static boolean isAuthor = false;
    private Story currentRandStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get our storyList instance from the application
        storyList = AdventureCreatorApplication.getStoryList();

//         //Erases previous saves - ONLY FOR TESTING - should be commented out
//         storyList = new StoryList();
//         storyListController.saveOfflineStories(this, storyList);

        listView = (ListView) findViewById(R.id.main_activity_listview);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Load our local stories from the StoryList Model

        storyList = storyListController.loadStoryOffline(MainActivity.this);
        stories = storyListController.getAllStories();

        if (DEBUG_LOG)
            Log.d(TAG, String.format("Number of stories is: %d", stories.size()));
        // Add ourself to the StoryList Model
        storyList.addView(this);

        // Set up ListView Stuff
        adapter = new StoryListArrayAdapter(this, R.layout.listview_story_list, stories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove ourselves from the story list
        storyList.deleteView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        isAuthor = settings.getBoolean(SHARED_PREF_IS_AUTHOR_FLAG, false);
        listView.invalidateViews();

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(SHARED_PREF_IS_AUTHOR_FLAG, isAuthor);

        // Commit the edits
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Make sure check box is checked if it needs to be
        menu.findItem(R.id.menu_check_box_author).setChecked(isAuthor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_story:
                // TODO: Launch the EditStoryActivity with the id of NEW_STORY
                int storyPos = storyList.getAllStories().size();
                // Story story = new Story();
                // storyList.addStory(story);
                storyListController.createBlankStory();

                // save the newly added story
                // offlineHelper.saveOfflineStories(storyList);
                storyListController.saveOfflineStories(this, storyList);

                Intent i = new Intent(this, CreateStoryActivity.class);
                i.putExtra("StoryPos", storyPos);
                startActivity(i);
                return true;

            case R.id.menu_check_box_author:
                if (DEBUG_LOG)
                    Log.d(TAG,
                            String.format("Checkbox checked, current value: %s", item.isChecked()));

                boolean alreadyChecked = item.isChecked();
                // Set it to the opposite of what it previously was
                item.setChecked(!alreadyChecked);

                // Set the flag now
                isAuthor = item.isChecked();
                return true;
                
            case R.id.menu_random_story:
            	if (!isAuthor && storyListController.getAllStories().size() > 0){
            		//http://stackoverflow.com/questions/363681/generating-random-numbers-in-a-range-with-java
            		Random random = new Random();
            		int max = storyListController.getAllStories().size() -1;
            	    int randPos = random.nextInt((max - 0) + 1) + 0;
            	    
            	     currentRandStory = storyListController.getStoryAtPos(randPos);
                    int fragPos = currentRandStory.getStartFragPos();
                    Fragment goToFrag = storyController.getFragmentAtPos(currentRandStory, fragPos);
                    
                    //http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
                    AlertDialog.Builder popup = new AlertDialog.Builder(this);

                    popup.setTitle("Title");
                    popup.setMessage("Message");

                    // display the fragments name
                    TextView msg = new TextView(this);
                    msg.setText("Going to go to: "+currentRandStory.getStoryTitle()+" OK?");
                    popup.setView(msg);
                   
                    popup.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent i = new Intent( getApplicationContext(), FragmentViewActivity.class);
                        storyListController.getAllStories();
                        int fragPos = currentRandStory.getStartFragPos();
                        Fragment goToFrag = storyController.getFragmentAtPos(currentRandStory, fragPos);
                        i.putExtra("Fragment", goToFrag);
                        startActivity(i);
                	    Log.d("Where am I going to ?","I'm going to go to FragmentViewAcvtivity!");
                      }
                    });

                    popup.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                      }
                    });

                    popup.show();
                    
                    

            	    return true;
            		
            	}
            	return true;
        }
        
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    /**
     * Reloads the story listview from StoryList Model by notifying the ListView that 
     * a change has occurred and will refresh all stories. The refreshing is done via 
     * an adapter.
     * 
     *  @param model current model that is being used     
     */
    public void update(StoryList model) {
        // Reload our stories from StoryList Model
        stories = storyList.getAllStories();
        // Notify Our ListView that our array has changed
        adapter.notifyDataSetChanged();
    }

    @Override
    /**
     * Function that deals with listview clicks within the list of stories. 
     * Will open either edit or view story activities depending whether or 
     * not "Author Mode" is enabled thought the menu.
     * 
     *  @param v    mode that is currently selected (either author or reader)
     *  @param pos  position that the stories is in within the listview
     *  @param id   id of the selected story
     */
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        // Open Edit/View story Activity with
        Intent i;
        if (isAuthor) {
            i = new Intent(this, StoryEditActivity.class);
            i.putExtra("StoryPos", pos);
            startActivity(i);
        } else {
            
            Story story = storyListController.getStoryAtPos(pos);
            int fragPos = story.getStartFragPos();
            Fragment goToFrag = storyController.getFragmentAtPos(story, fragPos);
            
            i = new Intent(this, FragmentViewActivity.class);
            i.putExtra("Fragment", goToFrag);
            startActivity(i);
        }

    }
}
