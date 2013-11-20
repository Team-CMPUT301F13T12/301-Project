package ualberta.g12.adventurecreator;

public class FragmentPartIllustration extends FragmentPart<String> {
    String picPath;
    
    public FragmentPartIllustration(){
        this.picPath = "";
    }
    
    @Override
    public String getAttribute() {
        return picPath;
    }

    @Override
    public void setAttribute(String attr) {
        if (attr != null){
            picPath = attr;
        }
    }

}
