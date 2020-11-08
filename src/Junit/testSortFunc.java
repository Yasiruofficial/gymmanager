package Junit;

import org.junit.Test;
import sample.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

public class testSortFunc {

    MyGymManager myGymManager = new MyGymManager();
    ArrayList<DefaultMember> toBeSort = MyGymManager.getMember_list();

    @Test
    public void testSortMember(){

        System.out.println("Start Sorting......\n");
        assertTrue(myGymManager.sortMember(toBeSort));
        System.out.println("\nStop Sorting......\n");
    }

}
