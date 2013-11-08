
package ualberta.g12.adventurecreator;

import java.util.ArrayList;

/**
 * All StoryList models adhere to this class. Adds the required methods to
 * update all of the views that our displaying our data.
 */
public class LModel<V extends LView> {
    private ArrayList<V> views;

    /**
     * No argument constructor for all Models that extend us. Initializes our
     * list of stories.
     */
    public LModel() {
        views = new ArrayList<V>();
    }

    /**
     * Adds a view to our list of views if that view is not already present.
     * 
     * @param view the view to add
     */
    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    /**
     * Deletes a view from our list of views
     * 
     * @param view the view to delete
     */
    public boolean deleteView(V view) {
        return views.remove(view);
    }

    /**
     * Notifies all our listening Views that we have updated our data and that
     * they should update theirs.<br>
     * Loops through all of our views and calls their update(LModel) method
     * which should be implemented in each of them.
     */
    public void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}
