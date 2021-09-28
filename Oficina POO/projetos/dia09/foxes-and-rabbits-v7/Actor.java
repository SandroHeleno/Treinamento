import java.util.List;
import java.awt.Color;

/**
 * Interface Actor - write a description of the interface here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public interface Actor
{
    /**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActors A list to add newly born actors to.
     */    
    void act(List<Actor> newActors);
    
    /**
     * Check whether the actor is active or not.
     * @return true if the actor is still active.
     */
    boolean isActive();
    /**
     * Return the actor's simulation color.
     * @return the actor's simulation color.
     */
    Color getSimulationColor();
}
