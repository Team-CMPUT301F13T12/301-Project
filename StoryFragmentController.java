import java.util.LinkedList;


public class StoryFragmentController {

	public void addChoice(StoryFragment frag, Choice cho){
		LinkedList<Choice> choices = frag.getChoices();
		choices.add(cho);
		frag.setChoices(choices);
	}
}
