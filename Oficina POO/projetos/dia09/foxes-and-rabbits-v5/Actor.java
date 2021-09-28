import java.util.List;
/**
 * Abstract class Actor - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Actor
{
    /**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActors A list to add newly born actors to.
     */    
    abstract public void act(List<Actor> newActors);
    
    /**
     * Check whether the actor is active or not.
     * @return true if the actor is still active.
     */
    abstract public boolean isActive();
}
