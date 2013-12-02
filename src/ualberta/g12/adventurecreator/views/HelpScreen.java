/**
 * Main help screen activity. Describes the basic uses of the application as well as any functions that can be accessed 
 * through the main screen. Provides options for more help screens depending on the actions the user wants to do. 
 */

package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ualberta.g12.adventurecreator.R;

public class HelpScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        Button btn = (Button) findViewById(R.id.Read);

        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(HelpScreen.this, HelpRead.class);
                // listens for read story button to be clicked
                HelpScreen.this.startActivity(myIntent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.CreateNew);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(HelpScreen.this, HelpNew.class);
                // Listens for create button to be called
                HelpScreen.this.startActivity(myIntent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.goOnline);

        btn3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(HelpScreen.this, HelpOnline.class);
                // listens for go online button to be called
                HelpScreen.this.startActivity(myIntent);
            }
        });

        Button btn4 = (Button) findViewById(R.id.youtube_video);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // listens for youtube button press
                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("https://www.youtube.com/watch?v=b7eKWk2Sizc")));

            }
        });
    }
}
