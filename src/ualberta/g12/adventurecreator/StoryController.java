package ualberta.g12.adventurecreator;

public class StoryController implements SController{

    private Story s = null;

    public StoryController(Story s){
        this.s = s;
    }

    @Override
    public void addFragment(Fragment f) {
        s.addFragment(f);
    }

    @Override
    public boolean deleteFragment(Fragment f) {
        return s.removeFragment(f);
    }

}
