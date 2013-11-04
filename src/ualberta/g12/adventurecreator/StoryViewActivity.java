
package ualberta.g12.adventurecreator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StoryViewActivity extends Activity {

    public static final String INTENT_STORY_ID = "storyId";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.story_view, menu);
        return true;
    }

}
