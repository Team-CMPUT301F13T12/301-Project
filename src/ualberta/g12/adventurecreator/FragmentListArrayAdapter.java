package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class FragmentListArrayAdapter extends ArrayAdapter<Fragment> {
    private final Context context;
    private final int resource;
    private final List<Fragment> frags;

    private static final boolean DEBUG_LOG = true;
    private static final String TAG = "FragmentListArrayAdapter";

    public FragmentListArrayAdapter(Context context, int resource, List<Fragment> frags) {
        super(context, resource, frags);
        this.context = context;
        this.resource = resource;
        this.frags = frags;
        if (DEBUG_LOG)
            Log.d(TAG, "Yo creating a fragmentList adapter yos?");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.listview_fragment_list, parent, false);
        }

        TextView text = (TextView) rowView.findViewById(R.id.list_fragment_title);

        String title = this.frags.get(position).getTitle();
        if (title != null){
            text.setVisibility(View.VISIBLE);
            if (text != null)
                text.setText(title);
        }

        return rowView;
    }
}
