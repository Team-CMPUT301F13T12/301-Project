package ualberta.g12.adventurecreator;

public class FragmentPartChoice extends FragmentPart<Choice> {
    Choice choice;
    
    public FragmentPartChoice(){
        this.choice = new Choice();
    }

    @Override
    public Choice getAttribute() {
        return choice;
    }

    @Override
    public void setAttribute(Choice attr) {
        if (attr != null){
            choice = attr;
        }
    }

}
