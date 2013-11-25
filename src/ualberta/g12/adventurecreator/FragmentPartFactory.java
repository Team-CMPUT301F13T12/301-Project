package ualberta.g12.adventurecreator;

public class FragmentPartFactory {

    public FragmentPart<?> createFragmentPart(String type){
        
        if (type == "t"){
            return new FragmentPartText();
        } if (type == "i"){
            return new FragmentPartIllustration();
        } if (type == "c"){
            return new FragmentPartChoice();
        } if (type == "e"){
            return new FragmentPartEmpty();
        }
        return null;
    }
}
