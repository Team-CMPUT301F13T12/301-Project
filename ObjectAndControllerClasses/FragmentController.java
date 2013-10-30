import java.util.LinkedList;


public class FragmentController {

	public void editTitle(Fragment frag, String newTitle){
		frag.setTitle(newTitle);
	}
	
	public void addChoice(Fragment frag, Choice cho){
		LinkedList<Choice> choices = frag.getChoices();
		choices.add(cho);
		frag.setChoices(choices);
	}
	
	public void deleteChoice(Fragment frag, Choice cho){
		LinkedList<Choice> choices = frag.getChoices();
		choices.remove(cho);
		frag.setChoices(choices);
	}
	
	public void addIllustration(Fragment frag, Drawable illustration){
		LinkedList<Drawable> illustrations = frag.getIllustrations();
		illustrations.add(illustration);
		frag.setSounds(illustrations);
	}
	
	public void addSound(Fragment frag, Sound sound){
		LinkedList<Sound> sounds = frag.getSounds();
		sounds.add(sound);
		frag.setSounds(sounds);
	}
	
	public void addVideo(Fragment frag, Video video){
		LinkedList<Video> videos = frag.getVideos();
		videos.add(video);
		frag.setVideos(videos);
	}
	
	public void addAnnotation(Fragment frag, Annotation annotation){
		Annotation annotations = frag.getAnnotations();
		annotations.addAnnotation(annotation);
		frag.setAnnotations(annotations);
	}
}
