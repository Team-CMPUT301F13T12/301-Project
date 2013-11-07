package ualberta.g12.adventurecreator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        ImageView image = (ImageView) rowView.findViewById(R.id.fragmentPartIllustration);
        TextView text = (TextView) rowView.findViewById(R.id.fragmentPartTextPart);
        TextView choiceButton = (TextView) rowView.findViewById(R.id.fragmentPartChoice);
                
        //make all invisible
        image.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        choiceButton.setVisibility(View.GONE);
        
        System.out.println("POSITION "+position);
        if (frag.getDisplayOrder().get(position).equals("t")){
            System.out.println("TEXT "+position);
            //Display a text segment

            //get the occurrence number of the textSegment
            int occurrence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(i).equals("t"))
                    occurrence++;  
            }

            String textSegment = frag.getTextSegments().get(occurrence);
            if (textSegment != null){
                text.setVisibility(View.VISIBLE);
                if (text != null)
                    text.setText(textSegment);
            }
        } else if (frag.getDisplayOrder().get(position).equals("i")){
            System.out.println("IMAGE "+position);
            //Display an illustration
            
            //get the occurrence number of the illustration
            int occurrence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(i).equals("i"))
                    occurrence++;  
            }

            System.out.println("probably dies here"+occurrence);
            String picturePath = frag.getIllustrations().get(occurrence);
            Bitmap illustration = BitmapFactory.decodeFile(picturePath);
            System.out.println("betchs don't see me");
            if (illustration != null){
                image.setVisibility(View.VISIBLE);
                if (image != null)
                    System.out.println("SET IMAGE");
                    image.setImageBitmap(illustration);
            }
        } else if (frag.getDisplayOrder().get(position).equals("c")){
            System.out.println("CHOICE "+position);
            //Display a choice
            
            //get the occurrence number of the illustration
            int occurrence = 0;
            for (int i = 0; i < position; i++){
                if (frag.getDisplayOrder().get(i).equals("c"))
                    occurrence++;  
            }

            Choice choice = frag.getChoices().get(occurrence);
            if (choice != null){
                choiceButton.setVisibility(View.VISIBLE);
                if (choiceButton != null)
                    System.out.println("SET CHOICE");
                    choiceButton.setText(choice.getChoiceText());
                    choiceButton.setBackgroundColor(Color.BLACK);
                    choiceButton.setTextColor(Color.WHITE);
            }
        } else if (frag.getDisplayOrder().get(position).equals("e")){
            System.out.println("EMPTY "+position);
            //Display a DefaultPart
            
            
            String textSegment = "Add new text/image/chioce\n(This section will be removed when done editing)";
            if (textSegment != null){
                text.setVisibility(View.VISIBLE);
                if (text != null)
                    text.setText(textSegment);
            }
        } 
        
        return rowView;
    }
}


