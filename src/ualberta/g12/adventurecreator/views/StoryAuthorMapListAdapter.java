
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.data.TitleAuthor;

import java.util.List;

/**
 * Adapter that communicates between the online list of stories and the listview
 * that displays them
 */
public class StoryAuthorMapListAdapter extends ArrayAdapter<TitleAuthor> {

    private final Context context;

    // Logging
    private static final boolean DEBUG = true;
    private static final String TAG = "StoryAuthorMapListAdapter";

    public StoryAuthorMapListAdapter(Context c, int id, List<TitleAuthor> list) {
        super(c, id, list);
        this.context = c;
    }

    private class ViewHolder {
        TextView title;
        TextView author;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        TitleAuthor ta = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.listview_story_list, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.list_story_title);
            holder.author = (TextView) convertView.findViewById(R.id.list_story_author);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.title.setText(ta.getTitle());
        holder.author.setText(ta.getAuthor());
        
        return convertView;
    }
}
