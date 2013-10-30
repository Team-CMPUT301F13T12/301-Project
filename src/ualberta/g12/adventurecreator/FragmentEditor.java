package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentEditor extends Activity {

	Button addImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_editor);

		addImage = (Button) findViewById(R.id.buttonAddIll);
		addImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// From: http://stackoverflow.com/q/16391124/1684866
				   Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT,null);
				    galleryIntent.setType("image/*");
				    galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);

				    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 


				    Intent chooser = new Intent(Intent.ACTION_CHOOSER);
				    chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);      
				    chooser.putExtra(Intent.EXTRA_TITLE, "Select Illustration From");

				    Intent[] intentArray =  {cameraIntent}; 
				    chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
				    startActivityForResult(chooser,0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_editor, menu);
		return true;
	}

}
