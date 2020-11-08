package Junit;

import org.junit.Test;
import sample.*;

import static org.junit.Assert.assertTrue;

public class testAddFunc {

    MyGymManager myGymManager = new MyGymManager();


    DefaultMember defaultMember = new DefaultMember();
    StudentMember studentMember = new StudentMember();
    Over60Member over60Member = new Over60Member();

    Date date = new Date();


    @Test
    public void testAddMember(){

        date.setYear(2020);
        date.setMonth(01);
        date.setDay(14);

        defaultMember.setMembershipNumber(126);
        defaultMember.setName("Pasindu");
        defaultMember.setStartMembershipDate(date);
        defaultMember.setCategory("Default Member");

        studentMember.setMembershipNumber(127);
        studentMember.setSchoolName("PWC");
        studentMember.setName("Yasiru");
        studentMember.setStartMembershipDate(date);
        studentMember.setCategory("Student Member");

        over60Member.setMembershipNumber(128);
        over60Member.setAge(78);
        over60Member.setName("Kavindu");
        over60Member.setStartMembershipDate(date);
        over60Member.setCategory("Over 60 Member");



        System.out.println("Start Adding .........\n");

        //Add Member
        assertTrue(myGymManager.addMember(defaultMember));
        assertTrue(myGymManager.addMember(studentMember));
        assertTrue(myGymManager.addMember(over60Member));

        System.out.println("\nStop Adding .........\n");

    }
}