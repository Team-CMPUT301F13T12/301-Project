package ualberta.g12.adventurecreator;

public class FragmentPartText extends FragmentPart<String>{
    String text;
    
    public FragmentPartText(){
        this.text = "";
    }

    @Override
    public String getAttribute() {
        return text;
    }

    @Override
    public void setAttribute(String attr) {
        if (attr != null){
            text = attr;
        }
    }
}
