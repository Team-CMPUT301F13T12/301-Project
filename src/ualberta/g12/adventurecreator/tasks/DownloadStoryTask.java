
package ualberta.g12.adventurecreator.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;

public class DownloadStoryTask extends AsyncTask<Story, Void, String> {

    private Context context;

    public DownloadStoryTask(Context c) {
        this.context = c;
    }

    @Override
    protected String doInBackground(Story... story) {

        // TODO: Actually download the story
                
        if (true) {
            StoryListController slc = AdventureCreator.getStoryListController();
            slc.addStory(story[0]);
            
            OfflineIOHelper offlineHelper = AdventureCreator.getOfflineIOHelper();
            offlineHelper.saveOfflineStories(AdventureCreator.getStoryList());
            return String.format("%s Download complete", story[0].getTitle());
        } else {
            return String.format("%s Download failed", story[0].getTitle());
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        super.onPostExecute(result);
    }

}
