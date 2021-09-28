import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public abstract class Animal implements Actor
{
    // The animal's age.
    private int age;  
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        age = 0;
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Check whether the actor is active or not.
     * @return true if the actor is still active.
     */
    public boolean isActive(){
        return isAlive();
    }
    
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    public Field getField()
    {
        return field;
    }
    
    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's age.
     * @return the animal's age.
     */
    protected int getAge()
    {
        return age;
    }
    
    /**
     * Return the animal's age.
     * @return The animal's age.
     */
    protected void setAge(int theAge)
    {
        age = theAge;
    }
    
    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     * Return the animal's max age.
     * @return the animal's max age.
     */
    abstract protected int getMaxAge();
    
    /**
     * A animal can breed if it has reached the breeding age.
     * @return true if the animal can breed, false otherwise.
     */
    protected boolean canBreed()
    {
        return age >= getBreedingAge();
    }
    
    /**
     * Return the animal's breeding age.
     * @return the animal's breeding age.
     */
    abstract protected int getBreedingAge();
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        Random rand = Randomizer.getRandom();
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }
    
    /**
     * Return the animal's breeding probability.
     * @return the animal's breeding probability.
     */
    abstract protected double getBreedingProbability();
    
    /**
     * Return the animal's max litter size.
     * @return the animal's max litter size.
     */
    abstract protected int getMaxLitterSize();
}
