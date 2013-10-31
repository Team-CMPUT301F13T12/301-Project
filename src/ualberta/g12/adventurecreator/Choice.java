package ualberta.g12.adventurecreator;

public class Choice {
	private String choiceText;
	private Fragment linkedToPage;
	
	//default settings for a new choice
	public Choice() {
		this.choiceText = "Change My Text";
		this.linkedToPage = null;  //always check before linking from a choice that likedToPage != null
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}

	public Fragment getLinkedToPage() {
		return linkedToPage;
	}

	public void setLinkedToPage(Fragment linkedToPage) {
		this.linkedToPage = linkedToPage;
		linkedToPage.setLinkedTo(true); //page is definitely linked to now
	}
}
