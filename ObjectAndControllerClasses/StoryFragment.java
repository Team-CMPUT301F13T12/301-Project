import java.util.LinkedList;

public class StoryFragment {
	private String pageTitle;
	private String bodyLayout; //change to layout later
	private LinkedList<Choice> choices;
	//True if at least one page references it, can be used a flag for isolated pages
	//will have to be controlled from the story object
	private boolean isLinkedTo; 
	
	public StoryFragment() {
		this.pageTitle = "Choose a Title";
		this.bodyLayout = "Story body here.";
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

	public String getBodyLayout() {
		return bodyLayout;
	}

	public void setBodyLayout(String bodyLayout) {
		this.bodyLayout = bodyLayout;
	}

	public LinkedList<Choice> getChoices() {
		return choices;
	}

	public void addChoice(Choice newChoice) {
		this.choices.add(newChoice);
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
