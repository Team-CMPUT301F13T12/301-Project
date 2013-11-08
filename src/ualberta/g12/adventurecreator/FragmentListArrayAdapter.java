
package ualberta.g12.adventurecreator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/** An extension of ArrayAdapter that is used to display Fragments in a ListView */
public class FragmentListArrayAdapter extends ArrayAdapter<Fragment> {
    private final Context context;
    private final int resource;
    private final List<Fragment> frags;

    private static final boolean DEBUG_LOG = true;
    private static final String TAG = "FragmentListArrayAdapter";

    /**
     * Sole Constructor that sets up the context, resources, and List of
     * Fragments
     * 
     * @param context the context of the calling activity
     * @param resource the listview we're displaying in
     * @param frags the list of fragments to display
     */
    public FragmentListArrayAdapter(Context context, int resource, List<Fragment> frags) {
        super(context, resource, frags);
        this.context = context;
        this.resource = resource;
        this.frags = frags;
        if (DEBUG_LOG)
            Log.d(TAG, "Yo creating a fragmentList adapter yos?");
    }

    @Override
    /**
     * @return the View that will be displayed in a row of the ListView
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.listview_fragment_list, parent, false);
        }

        TextView text = (TextView) rowView.findViewById(R.id.list_fragment_title);

        String title = this.frags.get(position).getTitle();
        if (title != null) {
            text.setVisibility(View.VISIBLE);
            if (text != null)
                text.setText(title);
        }

        return rowView;
    }
}
