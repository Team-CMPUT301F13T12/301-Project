

import java.util.LinkedList;


public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Choice> things = new LinkedList<Choice>();
		things.add(new Choice());
		things.add(new Choice());
		LinkedList<Choice> things2 = new LinkedList<Choice>();
		
		for(int i=0; i<things.size(); i++){
			things2.add(things.get(i));
		}
		
		System.out.println(things2.get(1).getChoiceText());
		things.get(1).setChoiceText("newtext");
		System.out.println(things2.get(1).getChoiceText());

		System.out.println(things.get(1).getChoiceText());


	}

}
