
package ualberta.g12.adventurecreator.data;

import ualberta.g12.adventurecreator.views.FView;

import java.util.ArrayList;

/**
 * The Model that all Fragment Models must extend. Contains methods to update
 * all of the FViews that are listening to us
 */
@SuppressWarnings("rawtypes")
public class FModel<V extends FView> {
    private ArrayList<V> views;

    /**
     * Sole constructor for the FModel. Initializes our views.
     */
    public FModel() {
        views = new ArrayList<V>();
    }

    /**
     * Adds a view to our list of views if it isn't already there
     * 
     * @param view the view to add
     */
    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    /**
     * Removes a view from our list of views.
     * 
     * @param view the view to remove
     */
    public void deleteView(V view) {
        views.remove(view);
    }

    /**
     * Notifies all of our attached FViews that we have changed and that they
     * should update.
     */
    @SuppressWarnings("unchecked")
    public void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}
