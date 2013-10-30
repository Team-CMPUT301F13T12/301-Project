import java.util.LinkedList;


public class FragmentController {

	public void addChoice(Fragment frag, Choice cho){
		LinkedList<Choice> choices = frag.getChoices();
		choices.add(cho);
		frag.setChoices(choices);
	}
}
