import java.util.LinkedList;



public class FragmentController {

	public void editTitle(Fragment frag, String newTitle){
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
	}
	
	public void addSound(Fragment frag, Sound sound){
		LinkedList<Sound> sounds = frag.getSounds();
		sounds.add(sound);
		frag.setSounds(sounds);
		LinkedList<String> displayOrder = frag.getDisplayOrder();
		displayOrder.add("s");
		frag.setDisplayOrder(displayOrder);
	}
	
	public void addVideo(Fragment frag, Video video){
		LinkedList<Video> videos = frag.getVideos();
		videos.add(video);
		frag.setVideos(videos);
		LinkedList<String> displayOrder = frag.getDisplayOrder();
		displayOrder.add("v");
		frag.setDisplayOrder(displayOrder);
	}
	
	public void addAnnotation(Fragment frag, Annotation annotation){
		Annotation annotations = frag.getAnnotations();
		annotations.addAnnotation(annotation);
		frag.setAnnotations(annotations);
	}
}
