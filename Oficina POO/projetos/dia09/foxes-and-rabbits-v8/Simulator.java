import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;
    // The probability that a actor will be created in any given grid position.
    private static final double ACTOR_CREATION_PROBABILITY = 0.1;

    // List of animals in the field.
    private List<Actor> actors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        actors = new ArrayList<Actor>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        //view.setColor(Rabbit.class, Color.orange);
        //view.setColor(Fox.class, Color.blue);
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation()
    {
        simulate(500);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Actor> newActors = new ArrayList<Actor>();        
        // Let all rabbits act.
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act(newActors);
            if(! actor.isActive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        actors.addAll(newActors);

        view.showStatus(step, field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with actors.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if (rand.nextDouble() <= ACTOR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Actor actor = ActorFactory.createActor(field, location);
                    actors.add(actor);
                }
            }
        }
    }
}
