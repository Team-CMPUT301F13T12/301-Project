
package ualberta.g12.adventurecreator.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;
import ualberta.g12.adventurecreator.views.OView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This task is used to download a list of the online stories that are available
 * for download. It uses the OnlineHelper singleton to download all of the
 * stories, but not in their complete form, these story objects just contain the
 * Title, Author, and ID of the story.
 * <p>
 * This task is used by the OnlineStoryView activity in order to get the list of
 * stories that the user can then either download or cache.
 */
public class DownloadTitleAuthorsTask extends AsyncTask<String, String, Boolean> {

    private List<Story> tas;
    private Context context;
    private OView<List<Story>> view;

    // Log this
    private static final boolean DEBUG = true;
    private static final String TAG = "DownloadTitleAuthorsTask";

    /**
     * Create a DownloadTitleAuthorsTask with the Context and an OView to update
     * once the download has completed.
     */
    public DownloadTitleAuthorsTask(Context c, OView<List<Story>> view) {
        this.context = c;
        this.view = view;
    }

    /**
     * Downloads the list of stories from the OnlineHelper. If a search query is
     * provided it is used to return a list of stories that match the query.
     */
    @Override
    protected Boolean doInBackground(String... query) {
        // TODO Call the OnlineHelper method to get the real titles

        OnlineHelper onlineHelper = AdventureCreator.getOnlineHelper();
        tas = new ArrayList<Story>();
        if (query[0] == null || query[0].equals("")) {
            // No search
            try {
                tas = onlineHelper.getAllStoryTitlesIdAuthor();
                return true;
            } catch (ClientProtocolException e) {
                Log.e(TAG, "Downloading story failed, threw ClientProtocolException.");
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                Log.e(TAG, "Downloading story failed, threw IOException");
                e.printStackTrace();
                return false;
            }
        } else {
            // search
            try {
                tas = onlineHelper.searchsearchStories(query[0]);

                return true;
            } catch (ClientProtocolException e) {
                Log.e(TAG, "Downloading story failed, threw ClientProtocolException.");
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                Log.e(TAG, "Downloading story failed, threw IOException");
                e.printStackTrace();
                return false;
            }
        }

    }

    /**
     * Notifies the user that we are about to load all of the online stories.
     */
    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Loading stories", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }

    /**
     * Updates the calling Activities list of stories with the stories we have
     * just downloaded here.
     */
    @Override
    protected void onPostExecute(Boolean result) {
        if (!result) {
            Toast.makeText(context, "Error downloading stories", Toast.LENGTH_SHORT).show();
        }
        view.update(tas);
    }

}
