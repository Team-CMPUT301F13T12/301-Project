
package ualberta.g12.adventurecreator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Activity_read_story extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_story);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_read_story, menu);
        return true;
    }

}
