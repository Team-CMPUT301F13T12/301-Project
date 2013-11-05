package ualberta.g12.adventurecreator;

public class Choice {
	private String choiceText;
	private Fragment linkedToFragment;
	
	//default settings for a new choice
	public Choice() {
		this.choiceText = "Change My Text";
		this.linkedToFragment = null;  //always check before linking from a choice that likedToPage != null
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}

	public Fragment getLinkedToFragment() {
		return linkedToFragment;
	}

	public void setLinkedToFragment(Fragment linkedToFragment) {
		this.linkedToFragment = linkedToFragment;
		linkedToFragment.setLinkedTo(true); //page is definitely linked to now
	}
}
