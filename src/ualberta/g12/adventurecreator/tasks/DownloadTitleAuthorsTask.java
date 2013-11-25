
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

public class DownloadTitleAuthorsTask extends AsyncTask<String, String, Boolean> {

    private List<Story> tas;
    private Context context;
    private OView<List<Story>> view;

    private static final String TAG = "DownloadStoryTask";

    public DownloadTitleAuthorsTask(Context c, OView<List<Story>> view) {
        this.context = c;
        this.view = view;
    }

    @Override
    protected Boolean doInBackground(String... query) {
        // TODO Call the OnlineHelper method to get the real titles

        OnlineHelper onlineHelper = AdventureCreator.getOnlineHelper();
        tas = new ArrayList<Story>();
        if (query[0] == null) {
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

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Loading stories", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (!result) {
            Toast.makeText(context, "Error downloading stories", Toast.LENGTH_SHORT);
        }
        view.update(tas);
    }

}
