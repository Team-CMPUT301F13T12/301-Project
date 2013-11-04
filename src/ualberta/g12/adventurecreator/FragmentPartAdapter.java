package ualberta.g12.adventurecreator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentPartAdapter extends ArrayAdapter{
    private final Context context;
    private final int resource;
    private final Fragment frag;

    public FragmentPartAdapter(Context context, int resource, Fragment frag) {
        super(context, resource, frag.getDisplayOrder());
        this.context = context;
        this.resource = resource;
        this.frag = frag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.listview_fragment_part_list, parent, false);
        }
        if (frag.getDisplayOrder().get(position).equals("t")){

            //get the occurence number of the textSegment
            int occurence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(position).equals("t"))
                    occurence++;  
            }

            String textSegment = frag.getTextSegments().get(occurence);
            if (textSegment != null){
                TextView text = (TextView) rowView.findViewById(R.id.fragmentPartText);
                if (text != null)
                    text.setText(textSegment);
            }
        } else if (frag.getDisplayOrder().get(position).equals("i")){

            //get the occurence number of the textSegment
            int occurence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(position).equals("i"))
                    occurence++;  
            }

            Drawable illustration = frag.getIllustrations().get(occurence);
            if (illustration != null){
                ImageView image = (ImageView) rowView.findViewById(R.id.ImageView04);
                if (image != null)
                    image.setImageDrawable(illustration);
            }
        }
        return rowView;
    }
}


