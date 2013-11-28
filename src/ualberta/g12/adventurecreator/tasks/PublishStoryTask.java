
package ualberta.g12.adventurecreator.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;

import java.io.IOException;

/**
 * Task used to publish a local story to the online repo of stories. If their is
 * a story that has the same id as this story we will overwrite the existing
 * online story.
 * <p>
 * This task is used by a user when they want to publish their local story to
 * the online list of stories.
 */
public class PublishStoryTask extends AsyncTask<Story, Void, Boolean> {

    private Context context;
    private String title;
    private OnlineHelper onlineHelper;

    public PublishStoryTask(Context c) {
        this.context = c;
        onlineHelper = AdventureCreator.getOnlineHelper();
    }

    @Override
    /**
     * Uploads the story passed in by that calling activity to the list of online stories.
     * @return true if the story was published successfully, otherwise false*/
    protected Boolean doInBackground(Story... story) {
        title = story[0].getTitle();
        try {
            onlineHelper.insertStory(story[0]);
            /*
             * If we didn't throw an exception we're assuming story was inserted
             * successfully
             */
            return true;
        } catch (IOException e) {
            return false;
        } catch (IllegalStateException e) {
            return false;
        }

    }

    @Override
    /**
     * Notifies the user of the status of the upload based on the result passed in by doInBackground.
     * @param result the result of the upload*/
    protected void onPostExecute(Boolean result) {
        if (result) {
            // Tell user story was uploaded successfully
            Toast.makeText(context, String.format("%s publish successful.", this.title),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Tell them upload failed
            Toast.makeText(context, String.format("%s publish unsuccessful.", this.title),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
