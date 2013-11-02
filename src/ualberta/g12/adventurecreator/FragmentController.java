package ualberta.g12.adventurecreator;

import java.util.LinkedList;

import android.graphics.drawable.Drawable;


public class FragmentController implements FController{

    private Fragment f = null;
    
    public FragmentController(Fragment f){
        this.f =f;
    }
    
    /*
     * None of this code should be in here, it should all be within the model
     * and the controller should just be full of simple methods that do simple
     * things like f.editTitle(newTitle); 
     */
    
/*	public void editTitle(Fragment frag, String newTitle){
		frag.setTitle(newTitle);
	}
	
	public void addChoice(Fragment frag, Choice cho){
		LinkedList<Choice> choices = frag.getChoices();
		choices.add(cho);
		frag.setChoices(choices);
		LinkedList<String> displayOrder = frag.getDisplayOrder();
		displayOrder.add("c");
		frag.setDisplayOrder(displayOrder);
	}
	
	public void deleteChoice(Fragment frag, Choice cho){
		LinkedList<String> displayOrder = frag.getDisplayOrder();
		LinkedList<Choice> choices = frag.getChoices();
		
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
	}
	
	public void addIllustration(Fragment frag, Drawable illustration){
		LinkedList<Drawable> illustrations = frag.getIllustrations();
		illustrations.add(illustration);
		frag.setIllustrations(illustrations);
		LinkedList<String> displayOrder = frag.getDisplayOrder();
		displayOrder.add("i");
		frag.setDisplayOrder(displayOrder);
	}*/

    @Override
    public void editTitle(String t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addChoice(Choice c) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteChoice(Choice c) {
        // TODO Auto-generated method stub
        
    }
	
//	public void addSound(Fragment frag, Sound sound){
//		LinkedList<Sound> sounds = frag.getSounds();
//		sounds.add(sound);
//		frag.setSounds(sounds);
//		LinkedList<String> displayOrder = frag.getDisplayOrder();
//		displayOrder.add("s");
//		frag.setDisplayOrder(displayOrder);
//	}
//	
//	public void addVideo(Fragment frag, Video video){
//		LinkedList<Video> videos = frag.getVideos();
//		videos.add(video);
//		frag.setVideos(videos);
//		LinkedList<String> displayOrder = frag.getDisplayOrder();
//		displayOrder.add("v");
//		frag.setDisplayOrder(displayOrder);
//	}
//	
//	public void addAnnotation(Fragment frag, Annotation annotation){
//		Annotation annotations = frag.getAnnotations();
//		annotations.addAnnotation(annotation);
//		frag.setAnnotations(annotations);
//	}
}
