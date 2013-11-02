
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EditFragmentActivity extends Activity implements FView<Fragment> {

    private Button addImage;
    private Fragment fragment;
    private FragmentController fragmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_editor);

        // TODO: Load our fragment from the story model using the id given to us as an extra
        
        // TODO: Set the fragmentController to our Fragment
        
        // TODO: Load our fragment into view
        
        // TODO: Set up on clickers
        setUpOnClickListeners();

    }

    private void setUpOnClickListeners() {
        addImage = (Button) findViewById(R.id.buttonAddIll);
        addImage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // From: http://stackoverflow.com/q/16391124/1684866
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
                galleryIntent.setType("image/*");
                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                Intent chooser = new Intent(Intent.ACTION_CHOOSER);
                chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
                chooser.putExtra(Intent.EXTRA_TITLE, "Select Illustration From");

                Intent[] intentArray = {
                        cameraIntent
                };
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooser, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_editor, menu);
        return true;
    }

    @Override
    public void update(Fragment model) {
        // TODO reload all fields based on new info from model

    }

}
