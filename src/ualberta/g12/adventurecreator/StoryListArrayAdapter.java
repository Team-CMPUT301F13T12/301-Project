
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter that communicates between the list of stories and the text
 * corresponding to display the story in the list. Will retrieve and transfer
 * the title and author saved within the story editing and will display on the
 * listview for the stories
 */
public class StoryListArrayAdapter extends ArrayAdapter<Story> {
    private final Context context;

    //private static final boolean DEBUG_LOG = true;
    //private static final String TAG = "StoryListArrayAdapter";

    /**
     * Sole Constructor for the StoryListArrayAdapter. Creates an Adapter that
     * can be used with a list of stories.
     * 
     * @param context the Context that this will be used in
     * @param id the layout of the listview
     * @param list the list of stories
     */
    public StoryListArrayAdapter(Context context, int id, List<Story> list) {
        super(context, id, list);
        this.context = context;
    }

    private class ViewHolder {
        TextView title;
        TextView author;
    }

    @Override
    /**Returns the inflated view that will be displayed as a listview row.*/
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
