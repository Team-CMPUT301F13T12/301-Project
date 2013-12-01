
package ualberta.g12.adventurecreator.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;

/**
 * This task is used to download a story from the list of online stories to a
 * users offline list. If the story doesn't properly download then we will
 * notify the user of this.
 */
public class DownloadStoryTask extends AsyncTask<Story, Void, String> {

    private Context context;
    private Story s;

    /**
     * Creates a new DownloadStoryTask with the given Context. The context is
     * used to create toast messages to update the user of the status of the
     * download.
     */
    public DownloadStoryTask(Context c) {
        this.context = c;
    }

    /**
     * Downloads the Story using the OnlineHelper Singleton. The status of the
     * story download is then passed onto the onPostExecute.
     */
    @Override
    protected String doInBackground(Story... story) {

        OnlineHelper oh = AdventureCreator.getOnlineHelper();
        s = oh.getStory(story[0].getId());

        if (s != null) {
            return String.format("%s Download complete", story[0].getTitle());
        } else {
            return String.format("%s Download failed", story[0].getTitle());
        }

    }

    /**
     * Adds the downloaded story to the users list of offline stories, and saves
     * the story if the story download was successful.
     * <p>
     * Displays the message passed to us from doInBackground in a toast message
     * to the user to notify them of the result of the download.
     */
    @Override
    protected void onPostExecute(String result) {
        /*
         * This has to be in onPostExecute because it ends up calling Update in
         * Other activities which means that we're going to be teling a ListView
         * adapter to change, and UI changes can only be done on the main UI
         * Thread.
         */
        if (s != null) {
            StoryListController slc = AdventureCreator.getStoryListController();
            // TODO: Check if story is already there and then update it with
            // updateStoryWithId
            slc.addStory(s);

            OfflineIOHelper offlineHelper = AdventureCreator.getOfflineIOHelper();
            offlineHelper.saveOfflineStories(AdventureCreator.getStoryList());
        }
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        super.onPostExecute(result);
    }

}
