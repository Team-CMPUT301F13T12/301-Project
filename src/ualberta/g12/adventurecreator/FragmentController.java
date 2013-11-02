
package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

public class FragmentController implements FController {

    @Override
    public void editBodyText(Fragment f, String b) {
        f.setBodyText(b);
    }

    @Override
    public void editTitle(Fragment f, String t) {
        f.setTitle(t);
    }

    @Override
    public void addIllustration(Fragment f, Drawable i) {
        // TODO: Do something with return value
        f.addIllustration(i);
    }

    @Override
    public void addChoice(Fragment f, Choice c) {
        // TODO: Do something with the return value
        f.addChoice(c);
    }

    @Override
    public boolean deleteChoice(Fragment f, Choice c) {
        return f.removeChoice(c);
    }

}
