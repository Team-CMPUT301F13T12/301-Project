import java.util.LinkedList;
import android.graphics.drawable.Drawable;

public class Fragment {
	private String pageTitle;
	private String bodyText;
	private LinkedList<Drawable> illustrations;
	private LinkedList<Choice> choices;
	//True if at least one page references it, can be used a flag for isolated pages
	//will have to be controlled from the story object
	private boolean isLinkedTo; 
	
	public Fragment() {
		this.pageTitle = "Choose a Title";
		this.bodyText = "Story body here.";
		this.choices = new LinkedList<Choice>();
		this.choices.add(new Choice()); //Add one default choice to start
		//true because it is only possible to create a new page from a parent page
		//or if it is the first page in a story
		isLinkedTo = true; 
	}
	
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public LinkedList<Drawable> getIllustrations() {
		return illustrations;
	}

	public void setIllustrations(LinkedList<Drawable> illustrations) {
		this.illustrations = illustrations;
	}

	public LinkedList<Choice> getChoices() {
		return choices;
	}

	public void setChoices(LinkedList<Choice> newChoices) {
		this.choices = newChoices;
	}
	
	public void removeChoice(Choice oldChoice) {
		this.choices.remove(oldChoice);
	}

	public boolean isLinkedTo() {
		return isLinkedTo;
	}

	public void setLinkedTo(boolean isLinkedTo) {
		this.isLinkedTo = isLinkedTo;
	}

}
