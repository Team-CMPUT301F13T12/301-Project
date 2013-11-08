
package ualberta.g12.adventurecreator;

import java.util.ArrayList;

/**
 * A Model class that all Story Model Classes should extend. Contains the
 * Necessary methods to be an actual Story Model
 */
public class SModel<V extends SView<SModel>> {
    private ArrayList<V> views;

    /** Sole Constructor for a SModel, initializes our list of views. */
    public SModel() {
        views = new ArrayList<V>();
    }

    /**
     * Adds a view to our list of views that will updated whenever we are
     * updated. The view will only be added if it isn't already included in our
     * list of views
     * 
     * @param view The view to add to our list of views
     */
    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    /**
     * Removes a view from our list of views.<br>
     * This should be called whenever a View is dying, so that we don't keep a
     * reference to it or try to update it when it is dead.
     * 
     * @param view the View to remove from our list of views.
     */
    public void deleteView(V view) {
        views.remove(view);
    }

    /**
     * Notifies our list of views that we have been updated, and calls each of
     * their update(SModel) methods which should update them
     */
    public void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}
