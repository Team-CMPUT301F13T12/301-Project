package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.util.LinkedList;
import java.util.List;

public class Fragment {
	private String title;
	private String bodyText;
	private List<Drawable> illustrations;
	//private LinkedList<Sound> sounds;
	//private LinkedList<Video> videos;
	private List<Choice> choices;
	private List<String> displayOrder; //Contains one character representations of each type to display 
	//True if at least one page references it, can be used a flag for isolated pages
	//will have to be controlled from the story object
	private boolean isLinkedTo; 
	//private Annotation annotations;
	
	public Fragment() {
		this.title = "Choose a Title";
		this.bodyText = "Story body here.";
		this.choices = new LinkedList<Choice>();
		this.choices.add(new Choice()); //Add one default choice to start
		//true because it is only possible to create a new page from a parent page
		//or if it is the first page in a story
		isLinkedTo = true; 
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public List<Drawable> getIllustrations() {
		return illustrations;
	}

	public void setIllustrations(LinkedList<Drawable> illustrations) {
		this.illustrations = illustrations;
	}

//	public LinkedList<Sound> getSounds() {
//		return sounds;
//	}
//
//	public void setSounds(LinkedList<Sound> sounds) {
//		this.sounds = sounds;
//	}
//
//	public LinkedList<Video> getVideos() {
//		return videos;
//	}
//
//	public void setVideos(LinkedList<Video> videos) {
//		this.videos = videos;
//	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(LinkedList<Choice> newChoices) {
		this.choices = newChoices;
	}
	
	public void removeChoice(Choice oldChoice) {
		this.choices.remove(oldChoice);
	}

	public List<String> getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(LinkedList<String> displayOrder) {
		this.displayOrder = displayOrder;
	}

	public boolean isLinkedTo() {
		return isLinkedTo;
	}

	public void setLinkedTo(boolean isLinkedTo) {
		this.isLinkedTo = isLinkedTo;
	}

//	public Annotation getAnnotations() {
//		return annotations;
//	}
//
//	public void setAnnotations(Annotation annotations) {
//		this.annotations = annotations;
//	}

}
