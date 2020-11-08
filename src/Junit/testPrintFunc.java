package Junit;

import org.junit.Test;
import sample.DefaultMember;
import sample.MyGymManager;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

public class testPrintFunc {

    MyGymManager myGymManager = new MyGymManager();
    ArrayList<DefaultMember> list = MyGymManager.getMember_list();


    @Test
    public void testPrintMember(){
        System.out.println("Start Printing......\n");

        assertTrue(myGymManager.printMember(list));

        System.out.println("\nStop Printing......\n");

    }
}
