
package ualberta.g12.adventurecreator.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.online.OnlineHelper;

public class TryPublishStoryTask extends AsyncTask<Story, Void, Boolean> {

    private Story s;
    private Context context;

    public TryPublishStoryTask(Context c) {
        this.context = c;
    }

    @Override
    protected Boolean doInBackground(Story... s) {
        this.s = s[0];
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

    @Override
    protected void onPostExecute(Boolean update) {
        if(update == null){
            Toast.makeText(context, "Error publishing story", Toast.LENGTH_SHORT).show();
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
                    s.getStoryTitle()));
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
