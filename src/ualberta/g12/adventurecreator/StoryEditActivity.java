
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StoryEditActivity extends Activity implements SView<Story> {

    private Story story;
    private StoryController storyController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_story_editor);

        // TODO: Load our story from the intent

        // TODO: Set up our storyController

        // TODO: Load our story contents into fields (ListView of Fragments)

        // TODO: Set up all listeners
        setUpOnClickListeners();
    }

    /**
     * Sets up this activities onClickListeners:
     * <p>
     * saveButton: Save the current story which updates list of stories<br>
     * addPageButton: Opens EditFragmentActivity to create a new Fragment
     */
    private void setUpOnClickListeners() {
        /*
         * TODO: SaveButton - Tell the StoryListController to update the
         * StoryList Model as a story has been changed or created
         */

        /*
         * TODO: AddPageButton - Use the StoryController to add another Fragment
         * which might update this story and then Open EditFragmentActivity with
         * and Intent that has extras of a NEW_FRAGMENT_ID. Model should tell us
         * to update.
         */

    }

    /*
     * Set up the context menu for the ListViews elements. This is probably not
     * going to be done in this method however this is used to remind us it
     * needs to be done
     */
    private void setUpContextMenu() {
        // TODO: ListItemClick: Same as AddPageButton except our extra will be
        // the ID of the intent

        // TODO: DeleteFragment: Use the StoryController to remove the fragment
        // with this id. Model should tell us to update
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.story_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.add_fragment:
                startActivity(new Intent(this, EditFragmentActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Story model) {
        // TODO: Update our local story variable

        // TODO: Reload value from our story into fields - notify adapter our
        // story has changed

    }
}
