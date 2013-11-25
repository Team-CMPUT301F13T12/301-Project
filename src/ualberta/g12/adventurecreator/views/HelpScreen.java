
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
           HelpScreen.this.startActivity(myIntent);      //listens for read story button to be clicked 
       } 
       });
    }
}
