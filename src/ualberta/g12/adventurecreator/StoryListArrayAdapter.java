
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StoryListArrayAdapter extends ArrayAdapter<Story> {
    private final Context context;

    private static final boolean DEBUG_LOG = true;
    private static final String TAG = "StoryListArrayAdapter";

    public StoryListArrayAdapter(Context context, int id, List<Story> list) {
        super(context, id, list);
        this.context = context;
        if (DEBUG_LOG)
            Log.d(TAG, "Creating a story list array adapter k?");
    }

    private class ViewHolder {
        TextView title;
        TextView author;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Story story = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_story_list, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.list_story_title);
            holder.author = (TextView) convertView.findViewById(R.id.list_story_author);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(story.getStoryTitle());
        holder.author.setText(story.getAuthor());

        return convertView;
    }
}
