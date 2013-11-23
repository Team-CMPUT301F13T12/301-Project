package ualberta.g12.adventurecreator.views;

/*
 * Modified from: http://stackoverflow.com/questions/10614696/how-to-pass-parameters-to-onclicklistener
 * By: Sherif elKhatib
 */

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Listener for a fragment modification. Listens for for a click in a position
 * of a listview within a fragment to allow for the position to be passed back.
 * 
 */
public class EditTextSegOnClickListener implements OnClickListener{

      private int position;
      /**Sets the position.
       * @param position the position to set for this OnClickListener*/
      public EditTextSegOnClickListener(int position) {
           this.position = position;
      }

      /**@return the position*/
      public int getPosition(){
          return this.position;
      }
      
      @Override
      public void onClick(View v)
      {
          //read your lovely variable
      }
}
