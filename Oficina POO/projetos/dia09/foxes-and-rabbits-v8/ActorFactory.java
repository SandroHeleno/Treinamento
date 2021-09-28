import java.util.Random;

/**
 * Write a description of class ActorFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ActorFactory
{
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.2;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 1 - FOX_CREATION_PROBABILITY;    

    public ActorFactory()
    {
    }

    public static Actor createActor(Field field, Location location)
    {
        Random rand = Randomizer.getRandom();
        if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
            Fox fox = new Fox(true, field, location);
            return fox;
        }
        else {
            Rabbit rabbit = new Rabbit(true, field, location);
            return rabbit;
        }
    }
}
