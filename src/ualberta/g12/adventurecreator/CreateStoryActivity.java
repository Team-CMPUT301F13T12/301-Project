package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateStoryActivity extends Activity {
	
	Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);
        
        createButton = (Button) findViewById(R.id.button1);
        createButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				// create a new story object! id should be unique too! 
				// create one for now but should be made using a controller 
				
				
				// get the editTexts!
				EditText title = (EditText) findViewById(R.id.editStoryTitle);
				EditText author = (EditText) findViewById(R.id.editStoryAuthor);
				
				Story myNewStory = new Story(title.getText().toString(), author.getText().toString());
				
				// TODO: make Id and also add to our list of stories!~
				
				
				// launch the startEditActivty with intent of the story id 
            	Intent intent = new Intent(getApplication(), StoryEditActivity.class);
            	
            	// should be  intent.putExtra("StoryId", myStory.getId() );  or something like this i think 
            	intent.putExtra("StoryId", 5 ); // putting storyid of 1 but this should be unique and we have get it somehow later
            	
            	intent.putExtra("EditType", "New");
				startActivity(intent);
			}
		});
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
		return super.onOptionsItemSelected(item);
	}
    
    
}
