package Junit;

import org.junit.Test;
import sample.MyGymManager;
import static org.junit.Assert.assertTrue;

public class testSaveFunc {

    MyGymManager myGymManager = new MyGymManager();

    @Test
    public void testSaveMember(){

        System.out.println("\nStart Saving......\n");
        assertTrue(myGymManager.saveMembers());
        System.out.println("\nStop Saving......\n");

    }
}
