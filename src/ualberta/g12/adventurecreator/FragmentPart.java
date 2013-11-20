package ualberta.g12.adventurecreator;

public abstract class FragmentPart<E> {
    
    public abstract E getAttribute();
    
    public abstract void setAttribute(E attr);
}
