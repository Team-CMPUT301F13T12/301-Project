package ualberta.g12.adventurecreator;

import java.util.ArrayList;

/** Interface for models*/
public class LModel<V extends LView> {
    private ArrayList<V> views;
    
    public LModel(){
        views = new ArrayList<V>();
    }
    
    public void addView(V view){
        if(!views.contains(view)){
            views.add(view);
        }
    }
    
    public boolean deleteView(V view){
        return views.remove(view);
    }
    
    public void notifyViews(){
        for(V view: views){
            view.update(this);
        }
    }
}
