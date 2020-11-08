package sample;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.bson.Document;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Projections.excludeId;

public class Main extends Application {



    MyGymManager myGymManager = new MyGymManager();
    Scanner in = new Scanner(System.in);
    String choice;
    Button btn = new Button("");

    ArrayList < DefaultMember > member_list = MyGymManager.getMember_list();


    @Override
    public void start(Stage primaryStage) throws Exception {

        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        MongoCollection<Document> collection = DBConnection.getConnection();
        FindIterable<Document> documents = collection.find().projection(excludeId());
        ObjectMapper Obj = new ObjectMapper();

        for(Document doc : documents) {


            if(doc.containsKey("schoolName")){
                member_list.add(Obj.readValue(doc.toJson(), StudentMember.class));
            }
            else if(doc.containsKey("age")){
                member_list.add(Obj.readValue(doc.toJson(), Over60Member.class));
            }
            else{
                member_list.add(Obj.readValue(doc.toJson(), DefaultMember.class));

            }


        }



        Pane root = new Pane();
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 300, 275));


        btn.setOnAction(event -> {

            Stage gui = myGymManager.openGui();
            gui.show();

            gui.setOnHidden(event1 -> {

                run();

            });

        });


        System.out.println("\n" +
                "  __                                                         __                     \n" +
                " /__    ._ _    |\\/|  _. ._   _.  _   _  ._ _   _  ._ _|_   (_      _ _|_  _  ._ _  \n" +
                " \\_| \\/ | | |   |  | (_| | | (_| (_| (/_ | | | (/_ | | |_   __) \\/ _>  |_ (/_ | | |\n" +
                "     /                            _|                            /                   \n Created by Lakindu Sanjana Lankageeganage (Version 1)");


        run();




    }

    void run() {


        try {

            System.out.println("\n");
            System.out.println("Please Select the Option you want to Perform (1/2/3/4/5/6/7)\n");
            System.out.println("1. Add Member");
            System.out.println("2. Delete Member");
            System.out.println("3. Print the list of members");
            System.out.println("4. Sort the item");
            System.out.println("5. Write/Save in a file");
            System.out.println("6 .Open a Graphical User Interface");
            System.out.println("7 .Exit\n");
            System.out.print("Enter -> ");


            choice = in .nextLine();


            if (Integer.parseInt(choice) == 1) {


                System.out.println("----------Add Member---------");
                System.out.println("        1. Add Default Member");
                System.out.println("        2. Add Student Member");
                System.out.println("        3. Add Over 60 Member");
                System.out.println("        4. Back\n");
                System.out.print("Enter -> ");

                choice = in .nextLine();

                if(Integer.parseInt(choice) != 4) {

                    System.out.print("Enter Membership Number : ");
                    String membership_number = in .nextLine();
                    System.out.print("Enter Member Name : ");
                    String name = in .nextLine();
                    System.out.print("Enter Nic Number : ");
                    String Nic = in .nextLine();
                    System.out.print("Enter Email : ");
                    String Email = in .nextLine();
                    System.out.print("Enter Height in (cm) : ");
                    String Height = in .nextLine();
                    System.out.print("Enter Weight in (Kg) : ");
                    String Weight = in .nextLine();

                    System.out.print("Enter This Year : ");
                    String year = in .nextLine();
                    System.out.print("Enter This Month : ");
                    String month = in .nextLine();
                    System.out.print("Enter The Day : ");
                    String day = in .nextLine();

                    Date date = new Date();
                    date.setYear(Integer.parseInt(year));
                    date.setMonth(Integer.parseInt(month));
                    date.setDay(Integer.parseInt(day));


                    if (Integer.parseInt(choice) == 1) {

                        DefaultMember defaultMember = new DefaultMember();

                        defaultMember.setMembershipNumber(Integer.parseInt(membership_number));
                        defaultMember.setName(name.toUpperCase());
                        defaultMember.setNic(Nic);
                        defaultMember.setEmail(Email);
                        defaultMember.setHeight(Height);
                        defaultMember.setWeight(Weight);
                        defaultMember.setStartMembershipDate(date);
                        defaultMember.setCategory("Default Member");

                        myGymManager.addMember(defaultMember);
                        run();


                    } else if (Integer.parseInt(choice) == 2) {

                        System.out.print("Enter Student School Name : ");
                        String school_name = in .nextLine();
                        System.out.print("Enter Emergency contact number : ");
                        String Econtno = in .nextLine();

                        StudentMember studentMember = new StudentMember();

                        studentMember.setMembershipNumber(Integer.parseInt(membership_number));
                        studentMember.setName(name.toUpperCase());
                        studentMember.setNic(Nic);
                        studentMember.setEmail(Email);
                        studentMember.setHeight(Height);
                        studentMember.setWeight(Weight);
                        studentMember.setStartMembershipDate(date);
                        studentMember.setCategory("Student Member");
                        studentMember.setSchoolName(school_name.toUpperCase());
                        studentMember.setEmergencyContactNumber(Econtno);

                        myGymManager.addMember(studentMember);
                        run();

                    } else if (Integer.parseInt(choice) == 3) {

                        System.out.print("Enter Age : ");
                        String age = in .nextLine();

                        Over60Member over60Member = new Over60Member();

                        over60Member.setMembershipNumber(Integer.parseInt(membership_number));
                        over60Member.setName(name.toUpperCase());
                        over60Member.setNic(Nic);
                        over60Member.setEmail(Email);
                        over60Member.setHeight(Height);
                        over60Member.setWeight(Weight);
                        over60Member.setStartMembershipDate(date);
                        over60Member.setCategory("Over 60 Member");
                        over60Member.setAge(Integer.parseInt(age));

                        myGymManager.addMember(over60Member);

                        run();

                    } else {

                        System.out.println("Wrong Input Try Again");
                        run();

                    }

                } else {
                    System.out.println("Backing....");
                    run();
                }


            } else if (Integer.parseInt(choice) == 2) {


                System.out.print("Enter Membership Number : ");
                String membership_number = in .nextLine();

                myGymManager.dltMember(membership_number);
                run();


            } else if (Integer.parseInt(choice) == 3) {

                myGymManager.printMember(member_list);
                run();


            } else if (Integer.parseInt(choice) == 4) {

                myGymManager.sortMember(member_list);
                run();


            } else if (Integer.parseInt(choice) == 5) {

                myGymManager.saveMembers();
                run();


            } else if (Integer.parseInt(choice) == 6) {

                btn.fire();

            } else if (Integer.parseInt(choice) == 7) {

                System.out.println("Thanks You");
                Platform.exit();

            } else {

                System.out.println("Wrong Input");
                run();


            }
        } catch (Exception ex) {
            System.out.println(ex);
            run();

        }


    }



    public static void main(String[] args) {
        launch(args);
    }
}