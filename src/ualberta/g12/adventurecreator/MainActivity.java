package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends Activity implements LView<StoryList>{

    private List<Story> stories;
    private StoryList storyList;
    
    private static final boolean DEBUG_LOG = true;
    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // TODO: Get our storyList instance from the application
        
        // TODO: Load our local stories from the StoryList Model
        // Something like stories = storyList.getAllStories()
        
        // TODO: Set up ListView Stuff
        
        // TODO: Set up listeners on items 
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.add_story:
		    // TODO: Launch the EditStoryActivity with the id of NEW_STORY
		    startActivity(new Intent(this, CreateStoryActivity.class));
			break;
			
		case R.id.settings:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


    @Override
    public void update(StoryList model) {
        // TODO: Reload our stories from StoryList Model
        
        // TODO: Notify Our ListView that our array has changed
        
    }
}
