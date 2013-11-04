
package ualberta.g12.adventurecreator;

import java.util.LinkedList;
import java.util.List;

import android.graphics.drawable.Drawable;


public class FragmentController implements FController {
    
    public FragmentController(){
        super();
    }

    @Override
    public void editTitle(Fragment frag, String newTitle){
        frag.setTitle(newTitle);
    }

    public static void addTextSegment(Fragment frag, String textSegment){
        List<String> textSegments = frag.getTextSegments();
        textSegments.add(textSegment);
        frag.setTextSegments(textSegments);

        //insert t into corresponding spot in display order
        List<String> displayOrder = frag.getDisplayOrder();
        
        int i=0;        
        while (i < displayOrder.size()){
            if (displayOrder.get(i).equals("c"))
                break;
            i++;
        }
        if (displayOrder.size()==i){
            displayOrder.add("t");
        } else {
            displayOrder.add(i, "t");
        }
        
        frag.setDisplayOrder(displayOrder);
    }
    
//  TODO  
//    @Override
//    public void editTextSegment(Fragment frag, String textSegment){
//        //TODO
//    }

    public void deleteTextSegment(Fragment frag, String textSegment){
        List<String> textSegments = frag.getTextSegments();
        List<String> displayOrder = frag.getDisplayOrder();

        //find the entry of display order that should be removed
        int occurence = textSegments.indexOf(textSegment);
        int i = 0;
        while(occurence >= 0){
            if (displayOrder.get(i) == "t"){
                occurence--;
            }
            i++;
        }
        displayOrder.remove(1-1);
        frag.setDisplayOrder(displayOrder);

        textSegments.remove(textSegment);
        frag.setTextSegments(textSegments);
    }

    @Override
    public void addIllustration(Fragment frag, Drawable illustration){
        List<Drawable> illustrations = frag.getIllustrations();
        illustrations.add(illustration);
        frag.setIllustrations(illustrations);

        //insert t into corresponding spot in display order
        List<String> displayOrder = frag.getDisplayOrder();
        int i=0;
        
        while (i < displayOrder.size()){
            if (displayOrder.get(i).equals("c"))
                break;
            i++;
        }
        if (displayOrder.size()==i){
            displayOrder.add("i");
        } else {
            displayOrder.add(i, "i");
        }
        frag.setDisplayOrder(displayOrder);
    }

    public void deleteIllustration(Fragment frag, Drawable illustration){
        List<Drawable> illustrations = frag.getIllustrations();
        List<String> displayOrder = frag.getDisplayOrder();

        //find the entry of display order that should be removed
        int occurence = illustrations.indexOf(illustration);
        int i = 0;
        while(occurence >= 0){
            if (displayOrder.get(i) == "i"){
                occurence--;
            }
            i++;
        }
        displayOrder.remove(1-1);
        frag.setDisplayOrder(displayOrder);

        illustrations.remove(illustration);
        frag.setIllustrations(illustrations);
    }


    //  public void addSound(Fragment frag, Sound sound){
    //      LinkedList<Sound> sounds = frag.getSounds();
    //      sounds.add(sound);
    //      frag.setSounds(sounds);
    //      LinkedList<String> displayOrder = frag.getDisplayOrder();
    //      displayOrder.add("s");
    //      frag.setDisplayOrder(displayOrder);
    //  }
    //  
    //  public void addVideo(Fragment frag, Video video){
    //      LinkedList<Video> videos = frag.getVideos();
    //      videos.add(video);
    //      frag.setVideos(videos);
    //      LinkedList<String> displayOrder = frag.getDisplayOrder();
    //      displayOrder.add("v");
    //      frag.setDisplayOrder(displayOrder);
    //  }
    //  
    
    @Override
    public void addChoice(Fragment frag, Choice cho){
        List<Choice> choices = frag.getChoices();
        choices.add(cho);
        frag.setChoices(choices);
        List<String> displayOrder = frag.getDisplayOrder();
        displayOrder.add("c");
        frag.setDisplayOrder(displayOrder);
    }

    @Override
    public boolean deleteChoice(Fragment frag, Choice cho){
        List<String> displayOrder = frag.getDisplayOrder();
        List<Choice> choices = frag.getChoices();

        //find the entry of display order that should be removed
        int occurence = choices.indexOf(cho);
        int i = 0;
        while(occurence >= 0){
            if (displayOrder.get(i) == "c"){
                occurence--;
            }
            i++;
        }
        displayOrder.remove(1-1);
        frag.setDisplayOrder(displayOrder);
        
        choices.remove(cho);
        frag.setChoices(choices);
        return true;
    }

    //  public void addAnnotation(Fragment frag, Annotation annotation){
    //      Annotation annotations = frag.getAnnotations();
    //      annotations.addAnnotation(annotation);
    //      frag.setAnnotations(annotations);
    //  }
}
