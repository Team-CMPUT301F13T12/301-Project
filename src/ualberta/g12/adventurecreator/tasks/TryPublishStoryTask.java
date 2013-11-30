
package ualberta.g12.adventurecreator.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;

import java.io.IOException;

/**
 * Task used to check if the story passed in already exists on the online repo
 * of stories. This is done by comparing the ids of the two stories. If two
 * stories have the same id then we ask the user if they want to update the
 * online story, as publishing a story that already exists will overwrite the
 * existing online story
 * <p>
 * This class will be used whenever a user tries to publish a story.
 */
public class TryPublishStoryTask extends AsyncTask<Story, Void, Boolean> {

    private Story s;
    private Context context;

    /**
     * Creates a TryPublishStoryTask with the given context. The context will be
     * used to create toast messages to notify the user of the progress and a
     * dialog window if the story being published will overwrite an existsing
     * story.
     */
    public TryPublishStoryTask(Context c) {
        this.context = c;
    }

    /**
     * Compares the id of the story to upload with all of the ids of the online
     * story and Story.INVALID id. We will notify onPostExecute of the
     * comparison result which will notify the user on the UI thread.
     * 
     * @return true if a story with this id already exists, false if none does,
     *         and null if the story has an invalid id
     */
    @Override
    protected Boolean doInBackground(Story... s) {
        this.s = s[0];
        // Check for invalid id
        if (this.s.getId() == Story.INVALID_ID) {
            return null;
        }

        OnlineHelper oh = AdventureCreator.getOnlineHelper();
        try {
            return oh.checkId(this.s.getId());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * If the story already exists online, asks the user via a dialog if they
     * want to overwrite the story. If they agree to this or if the story has
     * not already been uploaded the story is published using the
     * PublishStoryTask. However if the user does not want to update the story
     * or if the story had an INVALID_ID, the story is not updated.
     */
    @Override
    protected void onPostExecute(Boolean update) {
        if (update == null) {
            if (this.s.getId() == Story.INVALID_ID) {
                Toast.makeText(context, "Story has Invalid Title or Author", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(context, "Error publishing story", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (update) {
            // id Already exists, ask them if they want to update
            // Story already exists, ask if they want to update
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    publishStory();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Cancel the dialog, don't do anything

                }
            });

            builder.setTitle(String.format("%s already exists. Do you want to update it?",
                    s.getTitle()));
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            // Go ahead and publish it
            publishStory();
        }

        super.onPostExecute(update);
    }

    private void publishStory() {
        Toast.makeText(context, "Publishing story..", Toast.LENGTH_SHORT).show();
        PublishStoryTask publishTask = new PublishStoryTask(context);
        publishTask.execute(s);
    }

}
