
package ualberta.g12.adventurecreator.views;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.R.layout;
import ualberta.g12.adventurecreator.R.menu;

public class HelpNew extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_new);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help_new, menu);
        return true;
    }

}
