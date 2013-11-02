package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.util.List;

public class FragmentController implements FController{

    private Fragment f = null;
    
    public FragmentController(Fragment f){
        this.f =f;
    }
    
    @Override
    public void editBodyText(String b){
        f.setBodyText(b);
    }
    
    @Override
    public void editTitle(String t) {
        f.setTitle(t);
    }
    
    @Override
    public void addIllustration(Drawable i){
        List<Drawable> illustrations = f.getIllustrations();
        illustrations.add(i);
        f.setIllustrations(illustrations);
    }

    @Override
    public void addChoice(Choice c) {
        List<Choice> choices = f.getChoices();
        choices.add(c);
        f.setChoices(choices);
    }

    @Override
    public boolean deleteChoice(Choice c) {
        List<Choice> choices = f.getChoices();
        return choices.remove(c);
    }
	
}
