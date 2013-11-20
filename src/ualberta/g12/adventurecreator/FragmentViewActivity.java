
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity for viewing a fragment.  User can follow choices and will
 * be taken to the next fragment.
 */
public class FragmentViewActivity extends Activity implements FView<Fragment> {

    private TextView fragmentTitleTextView;
    private ListView fragmentPartListView;
    private FragmentPartAdapter adapter;
    private FragmentController fragmentController = AdventureCreatorApplication
            .getFragmentController();
    private static final String TAG = "FragmentViewActivity";
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewer);

        // obtain the intent
        Intent viewFragIntent = getIntent();
        fragment = (Fragment) viewFragIntent.getSerializableExtra("Fragment");
        
        // get widget references
        fragmentPartListView = (ListView) findViewById(R.id.FragmentViewPartList);
        fragmentTitleTextView = (TextView) findViewById(R.id.fragmentTitleText);

        setListClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: This should be in its own method

        // First load fragment parts as that is the same for both modes
        // Loads fragment parts (text, images, videos, sounds, etc)
        adapter = new FragmentPartAdapter(this, R.layout.activity_fragment_editor, fragment);
        fragmentPartListView.setAdapter(adapter);


        // Loads title
        if (fragmentTitleTextView != null) 
            fragmentTitleTextView.setText(fragment.getTitle());
   }

    @Override
    public void update(Fragment model) {
        // TODO reload all fields based on new info from model

    }

    private void setListClickListener() {
        fragmentPartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment goToFrag = fragmentController.getLinkedToFragmentOfChoice(fragment, position);
                
                if (goToFrag != null){
                    Intent intent = new Intent(FragmentViewActivity.this, FragmentViewActivity.class);
                    intent.putExtra("Fragment", goToFrag);
                    startActivity(intent);
                    //finish();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_part_menu, menu);
    }
}
