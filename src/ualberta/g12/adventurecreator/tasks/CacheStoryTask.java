
package ualberta.g12.adventurecreator.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;
import ualberta.g12.adventurecreator.views.FragmentViewActivity;

/**
 * A task used to Cache a story. This task will be used whenever a user wants to
 * view a story from the online list of stories but doesn't want to download it
 * to their offline list. This task can be used to allow the user to navigate
 * through the story.
 * <p>
 * This task performs the cache by downloading the story in a temporary
 * location. Once the story has been successfully downloaded, we start the
 * FragmentViewActivity with this story.
 */
public class CacheStoryTask extends AsyncTask<Story, Void, String> {

    private Context context;
    private Story s;

    /**
     * Create a CacheStoryTask with the given context. This context will be used
     * to display the toast messages.
     */
    public CacheStoryTask(Context c) {
        this.context = c;
    }

    /**
     * Downloads the story using the OnlineHelper. If the story doesn't
     * download, we prepare to notify the user on the UI Thread in
     * onPostExecute.
     */
    @Override
    protected String doInBackground(Story... story) {
        OnlineHelper oh = AdventureCreator.getOnlineHelper();
        s = oh.getStory(story[0].getId());

        if (s != null) {
            return String.format("%s Ready to view", story[0].getTitle());
        } else {
            return String.format("Error viewing %s", story[0].getTitle());
        }
    }

    /**
     * Notifies the user of the action that we are taking, either about to view
     * the story, or if the story failed and we are doing nothing.
     * <p>
     * If the story was downloaded successfully we send it to the
     * FragmentViewActivity which will allow the user to walk through the story.
     */
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();

        if (this.s != null) {
            // Get first fragment and give it to FragmentViewActivity
            int fragPos = this.s.getStartFragPos();

            Fragment goToFrag = s.getFragmentAtPos(fragPos);
            Intent i = new Intent(this.context, FragmentViewActivity.class);
            i.putExtra("Story", s);
            i.putExtra("type", "online");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(i);
        }
        super.onPostExecute(result);
    }

}
