
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

public class CacheStoryTask extends AsyncTask<Story, Void, String> {

    private Context context;
    private Story s;

    public CacheStoryTask(Context c) {
        this.context = c;
    }

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

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();

        if (this.s != null) {
            // Get first fragment and give it to FragmentViewActivity
            int fragPos = this.s.getStartFragPos();

            Fragment goToFrag = AdventureCreator.getStoryController().getFragmentAtPos(s, fragPos);
            Intent i = new Intent(this.context, FragmentViewActivity.class);
            i.putExtra("Fragment", goToFrag);
            this.context.startActivity(i);
        }
        super.onPostExecute(result);
    }

}
