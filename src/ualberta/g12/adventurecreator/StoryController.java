
package ualberta.g12.adventurecreator;

import java.util.List;

public class StoryController implements SController {

    
    /**
     * @param newTitle the new title of story s
     * @param s the story to change the title of*/
    public void setTitle(Story s, String newTitle){
        s.setStoryTitle(newTitle);
    }
    
    /**
     * @param newAuthor the new author of story s
     * @param s the story to change the author of*/
    public void setAuthor(Story s, String newAuthor){
        s.setAuthor(newAuthor);
    }
    
    @Override
    public void addFragment(Story s, Fragment f) {
        List<Fragment> fragments = s.getFragments();
        fragments.add(f);
        s.setFragments(fragments);
    }

    @Override
    public boolean deleteFragment(Story s, Fragment f) {
        return s.removeFragment(f);
    }

}
