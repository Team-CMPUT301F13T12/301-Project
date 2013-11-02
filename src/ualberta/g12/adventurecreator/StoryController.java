
package ualberta.g12.adventurecreator;

public class StoryController implements SController {

    @Override
    public void addFragment(Story s, Fragment f) {
        s.addFragment(f);
    }

    @Override
    public boolean deleteFragment(Story s, Fragment f) {
        return s.removeFragment(f);
    }

}
