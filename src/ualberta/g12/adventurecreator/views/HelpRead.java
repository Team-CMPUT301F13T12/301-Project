/**
 * Activity for the help screen. Describes how to use the application when reading through stories. 
 */
package ualberta.g12.adventurecreator.views;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.R.layout;
import ualberta.g12.adventurecreator.R.menu;

public class HelpRead extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_read);
    }

}
