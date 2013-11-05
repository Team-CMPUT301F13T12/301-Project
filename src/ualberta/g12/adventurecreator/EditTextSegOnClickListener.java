package ualberta.g12.adventurecreator;

/*
 * Modified from: http://stackoverflow.com/questions/10614696/how-to-pass-parameters-to-onclicklistener
 * By: Sherif elKhatib
 */

import android.view.View;
import android.view.View.OnClickListener;

public class EditTextSegOnClickListener implements OnClickListener{

      int position;
      public EditTextSegOnClickListener(int position) {
           this.position = position;
      }

      @Override
      public void onClick(View v)
      {
          //read your lovely variable
      }
}
