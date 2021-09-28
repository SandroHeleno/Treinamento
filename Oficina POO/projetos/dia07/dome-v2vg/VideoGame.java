
/**
 * Write a description of class VideoGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VideoGame extends Item
{
    private String platform;
    private int numberOfPlayers;

    /**
     * Constructor for objects of class VideoGame
     */
    public VideoGame(String theTitle, String thePlatform, int players, int time)
    {
        super(theTitle, time);
        platform = thePlatform;
        numberOfPlayers = players;
    }
    /**
     * @return The platform for this VideoGame.
     */
    public String getPlatform()
    {
        return platform;
    }

    /**
     * @return The number of players on this VideoGame.
     */
    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }
}
