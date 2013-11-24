
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.online.OnlineHelper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
    private Button onlineButton;
    private StoryListArrayAdapter adapter;
    private OfflineIOHelper offlineHelper;
    private OnlineHelper onlineHelper;

    public static final String IS_AUTHOR_FLAG = "isAuthor";
    private static boolean isAuthor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get our storyList instance from the application
        storyList = AdventureCreator.getStoryList();

        // Load our local stories from the StoryList Model
        stories = storyList.getAllStories();

        // Set up our onlin helper
        onlineHelper = AdventureCreator.getOnlineHelper();
        
        // //Erases previous saves - ONLY FOR TESTING - should be commented out
        // storyList = new StoryList();
        // offlineHelper.saveOfflineStories(storyList);

        listView = (ListView) findViewById(R.id.main_activity_listview);
        onlineButton = (Button) findViewById(R.id.main_activity_start_online_mode);
        onlineButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), OnlineStoryViewActivity.class));
            }
        });

        setupListView();

        // Lets see who started us
        handleIntent(getIntent());

        offlineHelper = AdventureCreator.getOfflineIOHelper();
    }

    private void setupListView() {
        // Set up ListView Stuff
        adapter = new StoryListArrayAdapter(this, R.layout.listview_story_list, stories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(DEBUG_LOG) Log.d(TAG, "LONG CLICK");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tryPublishStory(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         // Cancel the dialog.
                        
                    }
                });
                builder.setTitle("Do you want to publish this story?");
                AlertDialog dialog = builder.create();
                dialog.show();
                // We don't want onItemClickListener to fire
                return true; 
            }

        });
    }

    /**
     * @param position the position of the story
     */
    private void tryPublishStory(int position) {
        final Story s = stories.get(position);
        
        try{
        //if(onlineHelper.checkId(position)){
            if(true){
            // Story already exists, ask if they want to update
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    publishStory(s);
                    
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Cancel the dialog, don't do anything
                    
                }
            });
            
            builder.setTitle(String.format("%s already exists. Do you want to update it?", s.getStoryTitle()));
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            // Story doesn't exist, just update it.
            publishStory(s);
            throw new IOException();
        }
        } catch(IOException e){
            Toast.makeText(getApplicationContext(), "Error publishing story", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    
    private void publishStory(Story s){
        
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // If we got searched lets search
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY).toLowerCase(Locale.CANADA);
            Intent searchIntent = new Intent(getApplicationContext(), StorySearchActivity.class);
            searchIntent.putExtra(SearchManager.QUERY, query);
            searchIntent.putExtra(MainActivity.IS_AUTHOR_FLAG, isAuthor);
            startActivity(searchIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add ourself to the StoryList Model
        storyList.addView(this);
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
        isAuthor = settings.getBoolean(IS_AUTHOR_FLAG, false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(IS_AUTHOR_FLAG, isAuthor);

        // Commit the edits
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the searchview
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.story_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
                Story story = new Story();
                storyList.addStory(story);

                // save the newly added story
                offlineHelper.saveOfflineStories(storyList);

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
            i.putExtra("Mode", "Edit");
            i.putExtra("StoryPos", pos);
            startActivity(i);
        } else {
            int fragPos = stories.get(pos).getStartFragPos();

            i = new Intent(this, EditFragmentActivity.class);
            i.putExtra("Mode", "View");
            i.putExtra("StoryPos", pos);
            i.putExtra("FragmentPos", fragPos);
            startActivity(i);
        }
    }

}
