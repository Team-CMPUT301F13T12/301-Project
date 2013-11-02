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
        // TODO: Do something with return value
        f.addIllustration(i);
    }

    @Override
    public void addChoice(Choice c) {
        // TODO: Do something with the return value
        f.addChoice(c);
    }

    @Override
    public boolean deleteChoice(Choice c) {
        return f.removeChoice(c);
    }
	
}
