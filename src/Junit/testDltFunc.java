package Junit;

import org.junit.Test;
import sample.MyGymManager;

import static org.junit.Assert.assertEquals;

public class testDltFunc {

    MyGymManager myGymManager = new MyGymManager();


    @Test
    public void testDltMember(){

        System.out.println("Start Deleting......\n");

        assertEquals("Student Member",myGymManager.dltMember("127"));

        System.out.println("\nStop Deleting......\n");

    }
}
