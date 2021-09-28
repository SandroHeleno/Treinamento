import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DayTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DayTest
{
    private Day day1;
    private Appointment appointm1;
    private Appointment appointm2;
    private Appointment appointm3;
    private Appointment appointm4;
    private Appointment appointm5;

    /**
     * Default constructor for test class DayTest
     */
    public DayTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        day1 = new Day(1);
        appointm1 = new Appointment("Java lecture", 1);
        appointm2 = new Appointment("Java class", 1);
        appointm3 = new Appointment("Meet John", 1);
        appointm4 = new Appointment("Business lunch", 2);
        appointm5 = new Appointment("Java workshop", 5);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test basic functionality by booking at either end
     * of a day, and in the middle.
     */
    @Test
    public void makeThreeAppointments()
    {
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(true, day1.makeAppointment(13, appointm2));
        assertEquals(true, day1.makeAppointment(17, appointm3));
    }

    /**
     * Check that double-booking is not permitted.
     */
    @Test
    public void doubleBooking()
    {
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(false, day1.makeAppointment(9, appointm2));
    }

    @Test
    public void findSpace9()
    {
        assertEquals(9, day1.findSpace(appointm1));
    }

    @Test
    public void findSpace10()
    {
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(10, day1.findSpace(appointm3));
    }

    @Test
    public void busyDay()
    {
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(true, day1.makeAppointment(10, appointm2));
        assertEquals(true, day1.makeAppointment(11, appointm4));
        assertEquals(true, day1.makeAppointment(13, appointm5));
        assertEquals(-1, day1.findSpace(appointm3));
    }

    @Test
    public void notOverlapEndOfDay()
    {
        assertEquals(false, day1.makeAppointment(16, appointm5));
    }

    @Test
    public void notOverlapBooking()
    {
        assertEquals(true, day1.makeAppointment(10, appointm1));
        assertEquals(false, day1.makeAppointment(9, appointm4));
    }
}






