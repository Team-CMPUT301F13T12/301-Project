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
import android.widget.EditText;
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
        EditText editText = (EditText) rowView.findViewById(R.id.fragmentPartEditText);
        ImageView image = (ImageView) rowView.findViewById(R.id.fragmentPartIllustration);
        TextView defaultText = (TextView) rowView.findViewById(R.id.fragmentPartTextPart);
        
        //make all invisible
        editText.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
        defaultText.setVisibility(View.GONE);
        
        System.out.println("POSITION "+position);
        if (frag.getDisplayOrder().get(position).equals("t")){
            System.out.println("TEXT "+position);
            //Display a text segment

            //get the occurence number of the textSegment
            int occurence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(position).equals("t"))
                    occurence++;  
            }

            String textSegment = frag.getTextSegments().get(occurence);
            if (textSegment != null){
                editText.setVisibility(View.VISIBLE);
                if (editText != null)
                    editText.setText(textSegment);
            }
        } else if (frag.getDisplayOrder().get(position).equals("i")){
            System.out.println("IMAGE "+position);
            //Display an illustration
            
            //get the occurence number of the textSegment
            int occurence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(position).equals("i"))
                    occurence++;  
            }

            Drawable illustration = frag.getIllustrations().get(occurence);
            if (illustration != null){
                image.setVisibility(View.VISIBLE);
                if (image != null)
                    image.setImageDrawable(illustration);
            }
        } else if (frag.getDisplayOrder().get(position).equals("e")){
            System.out.println("EMPTY "+position);
            //Display a DefaultPart
            
            
            String textSegment = "Add new text/image/chioce\n(This section will be removed when done editing)";
            if (textSegment != null){
                defaultText.setVisibility(View.VISIBLE);
                if (defaultText != null)
                    defaultText.setText(textSegment);
            }
        } 
        
        return rowView;
    }
}


