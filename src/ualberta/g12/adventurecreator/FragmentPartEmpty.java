package ualberta.g12.adventurecreator;

public class FragmentPartEmpty extends FragmentPart<String> {
    String text;
    
    public FragmentPartEmpty(){
        this.text = "New Text";
    }

    @Override
    public String getAttribute() {
        return text;
    }

    @Override
    public void setAttribute(String attr) {

    }
}
