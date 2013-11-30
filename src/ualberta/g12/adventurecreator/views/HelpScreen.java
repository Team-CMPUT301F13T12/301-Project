/**
 * Main help screen activity. Describes the basic uses of the application as well as any functions that can be accessed 
 * through the main screen. Provides options for more help screens depending on the actions the user wants to do. 
 */
package ualberta.g12.adventurecreator.views;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.R.id;
import ualberta.g12.adventurecreator.R.layout;
import ualberta.g12.adventurecreator.R.menu;

public class HelpScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        
        Button btn=(Button)findViewById(R.id.Read);

        btn.setOnClickListener(new View.OnClickListener()
       {
       public void onClick(View v) 
       {
           Intent myIntent = new Intent(HelpScreen.this, HelpRead.class);
           HelpScreen.this.startActivity(myIntent);      //listens for read story button to be clicked 
       } 
       });
        
        Button btn2=(Button)findViewById(R.id.CreateNew);

        btn2.setOnClickListener(new View.OnClickListener()
       {
       public void onClick(View v) 
       {
           Intent myIntent = new Intent(HelpScreen.this, HelpNew.class);
           HelpScreen.this.startActivity(myIntent);      //listens for create button to be clicked 
       } 
       });
        
        Button btn3=(Button)findViewById(R.id.goOnline);

        btn3.setOnClickListener(new View.OnClickListener()
       {
       public void onClick(View v) 
       {
           Intent myIntent = new Intent(HelpScreen.this, HelpOnline.class);
           HelpScreen.this.startActivity(myIntent);      //listens for go online button to be clicked 
       } 
       });
    }
}
