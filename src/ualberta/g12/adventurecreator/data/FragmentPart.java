package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public abstract class FragmentPart<E> implements Serializable{
    
    public abstract E getAttribute();
    
    public abstract void setAttribute(E attr);
}
