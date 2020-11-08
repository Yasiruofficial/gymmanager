package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.eq;

public class MyGymManager implements GymManager{

    int[] memberPlace = new int[5];

    MongoCollection<Document> collection = DBConnection.getConnection();


    private static ArrayList<DefaultMember> member_list = new ArrayList<>();
    public static ArrayList<DefaultMember> getMember_list() {
        return member_list;
    }
    public static void setMember_list(ArrayList<DefaultMember> member_list) {
        MyGymManager.member_list = member_list;
    }




    private static ArrayList<DefaultMember> temp_member_list = new ArrayList<>();

    public static ArrayList<DefaultMember> getTemp_member_list() {
        return temp_member_list;
    }

    public static void setTemp_member_list(ArrayList<DefaultMember> temp_member_list) {
        MyGymManager.temp_member_list = temp_member_list;
    }



    @Override
    public boolean addMember(DefaultMember defaultMember) {

        if(isFull()){

            throw new IllegalStateException("Gym is already full");
        }


        memberPlace = isAlreadyIn(defaultMember.getMembershipNumber());


        if(memberPlace[0] == 0){

            temp_member_list.add(defaultMember);
            System.out.println("Added " + defaultMember.getCategory());
            return true;

        }
        else {

            throw new IllegalArgumentException("Member Already In");

        }

    }



    @Override
    public String dltMember(String id) {



        if(isEmpty()){
            throw new IllegalStateException("Gym is Empty");
        }

        memberPlace = isAlreadyIn(Integer.parseInt(id));

        if(memberPlace[0] == 0){

            throw new IllegalArgumentException("Member Not in Gym");

        }
        else if(memberPlace[0] == 1){

            String category = member_list.get(memberPlace[1]).getCategory();
            member_list.remove(memberPlace[1]);


            collection.deleteOne(eq("membershipNumber" , Integer.parseInt(id) ));

            System.out.println("Deleted " + category);
            return category;

        }
        else {

            String category = temp_member_list.get(memberPlace[1]).getCategory();
            temp_member_list.remove(memberPlace[1]);

            System.out.println("Deleted " + category);
            return category;


        }

    }

    @Override
    public boolean printMember(ArrayList<DefaultMember> members) {


        if(members.size() > 0){

            for(int i = 0;i < members.size();i++){

                System.out.println("\n           Member " + (i+1) );
                System.out.println("    * ID = " + members.get(i).getMembershipNumber());
                System.out.println("    * Name = " +  members.get(i).getName());
                System.out.println("    * Member Category = " + members.get(i).getCategory());
                System.out.println("    * Nic = " + members.get(i).getNic());
                System.out.println("    * Email = " + members.get(i).getEmail());
                System.out.println("    * Height = " + members.get(i).getHeight() + " cm");
                System.out.println("    * Weight = " + members.get(i).getWeight() + " cm");

                if(member_list.get(i).getCategory().equals("Student Member")){

                    StudentMember studentMember = (StudentMember)member_list.get(i);

                    System.out.println("    * School name = " + studentMember.getSchoolName());
                    System.out.println("    * Emergency contact number = " + studentMember.getEmergencyContactNumber());
                }

                if(member_list.get(i).getCategory().equals("Over 60 Member")){

                    Over60Member over60Member = (Over60Member)member_list.get(i);

                    System.out.println("    * Age = " + over60Member.getAge());
                }

            }

            return true;

        }else{

            return false;

        }

    }




    @Override
    public boolean sortMember(ArrayList<DefaultMember> members) {

        int n = members.size();

        DefaultMember temp1;
        DefaultMember temp2;


        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (members.get(j - 1).getName().compareTo(members.get(j).getName()) > 0) {

                    temp1 = members.get(j - 1);
                    temp2 = members.get(j);

                    members.remove(j - 1);
                    members.add(j - 1, temp2);

                    members.remove(j);
                    members.add(j, temp1);
                }
            }

        }

        System.out.println("Sorted");

        return true;


    }




    @Override
    public boolean saveMembers() {

        ObjectMapper Obj = new ObjectMapper();

        try {


            for(int i = 0;i<temp_member_list.size();i++){

                String jsonStr = Obj.writeValueAsString(temp_member_list.get(i));
                Document member = Document.parse(jsonStr);
                collection.insertOne(member);

            }// Insert one by one to the DB


            for(int i = 0;i<temp_member_list.size();i++){

                member_list.add(temp_member_list.get(i));

            } // add members to the member_list who are in temp_member list


            temp_member_list.clear();//Clear temp_member_list




            String writable_String="";


            for(int i = 0;i<member_list.size();i++){

                int membershipNumber = member_list.get(i).getMembershipNumber();
                String name = member_list.get(i).getName();
                String category = member_list.get(i).getCategory();
                String NIC = member_list.get(i).getNic();
                String Email = member_list.get(i).getEmail();
                String Height = member_list.get(i).getHeight();
                String Weight = member_list.get(i).getWeight();
                Date StartMembershipDate = member_list.get(i).getStartMembershipDate();


                writable_String += "               Member " + (i+1) +
                                   "\n\n        Membership Number : " + membershipNumber +
                                   "\n        Membership Name : " + name +
                                   "\n        Category : " + category +
                                   "\n        StartMembershipDate : " + StartMembershipDate.getDay() + "-"+StartMembershipDate.getMonth() +"-"+StartMembershipDate.getYear()+
                                   "\n        NIC : " + NIC +
                                   "\n        Email : " + Email +
                                   "\n        Height : " + Height + " cm"+
                                   "\n        Weight : " + Weight + " cm"+
                                   "\n";

                if(member_list.get(i).getCategory().equals("Student Member")){


                    StudentMember studentMember = (StudentMember)member_list.get(i);
                    writable_String += ("        School Name : " + studentMember.getSchoolName() + "\n        " + "Emergency Contact number : " + studentMember.getEmergencyContactNumber());

                }

                if(member_list.get(i).getCategory().equals("Over 60 Member")){

                    Over60Member over60Member = (Over60Member)member_list.get(i);
                    writable_String += ("        Age : " + over60Member.getAge() + "\n");

                }
                writable_String +="\n\n";



            }// Insert one by one to the File

            try {
                FileWriter myWriter = new FileWriter("Members.txt");
                myWriter.write(writable_String);
                myWriter.close();

                System.out.println("Successfully Saved\n");

            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }


            return true;

        }

        catch (IOException e) {

            e.printStackTrace();
            return false;
        }


    }


    @Override
    public Stage openGui() {

        TableView<TableContent> table = new TableView<TableContent>();
        table.setMaxWidth(1150);
        table.setMinWidth(1150);
        table.setMaxHeight(300);
        table.setMinHeight(300);

        ObservableList<TableContent> data = FXCollections.observableArrayList();



        for(int i =0;i<member_list.size();i++){

            TableContent content = new TableContent();

            content.setId(member_list.get(i).getMembershipNumber());
            content.setName(member_list.get(i).getName());
            content.setCategory(member_list.get(i).getCategory());
            content.setDate(member_list.get(i).getStartMembershipDate().getDay()+"-"+member_list.get(i).getStartMembershipDate().getMonth()+"-"+member_list.get(i).getStartMembershipDate().getYear());
            content.setNic(member_list.get(i).getNic());
            content.setEmail(member_list.get(i).getEmail());
            content.setHeight(member_list.get(i).getHeight());
            content.setWeight(member_list.get(i).getWeight());

            if(member_list.get(i).getCategory().equals("Student Member")){

                StudentMember studentMember = (StudentMember)member_list.get(i);
                content.setSchoolName(studentMember.getSchoolName());
                content.setContactNumber(studentMember.getEmergencyContactNumber());


            }
            else if(member_list.get(i).getCategory().equals("Over 60 Member")){

                Over60Member over60Member = (Over60Member)member_list.get(i);
                content.setAge(over60Member.getAge());

            }


            data.add(content);

        }

        Label address_lable = new Label("Gym Management System (V.1)");
        address_lable.setFont(Font.font("Verdana" , FontWeight.BOLD , 35));
        address_lable.setTextFill(Color.web("#ffffff"));
        address_lable.setLayoutX(95);
        address_lable.setLayoutY(25);


        TextField text1 = new TextField();
        text1.setAlignment(Pos.CENTER_RIGHT);


        text1.setPrefWidth(300);
        Button search = new Button("Search By ID");
        search.setPrefWidth(200);
        Button reset = new Button("Reset");
        reset.setPrefWidth(150);

        HBox hBox =  new HBox(text1,search,reset);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(0,0,0,330));
        VBox vBox = new VBox(hBox,table);
        vBox.setPadding(new Insets(100,50,50,50));
        vBox.setSpacing(20);

        table.setEditable(true);



        TableColumn id = new TableColumn("Member ID");
        id.setCellValueFactory(new PropertyValueFactory<TableContent, Integer>("id"));
        id.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<TableContent, String>("name"));
        name.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn category = new TableColumn("Category");
        category.setCellValueFactory(new PropertyValueFactory<TableContent, String>("category"));
        category.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn date = new TableColumn("Start Date");
        date.setCellValueFactory(new PropertyValueFactory<TableContent, String>("date"));
        date.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn nic = new TableColumn("NIC");
        nic.setCellValueFactory(new PropertyValueFactory<TableContent, String>("Nic"));
        nic.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn email = new TableColumn("Email");
        email.setCellValueFactory(new PropertyValueFactory<TableContent, String>("Email"));
        email.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn height = new TableColumn("Height");
        height.setCellValueFactory(new PropertyValueFactory<TableContent, String>("Height"));
        height.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn weight = new TableColumn("Weight");
        weight.setCellValueFactory(new PropertyValueFactory<TableContent, String>("Weight"));
        weight.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn age = new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory<TableContent, Integer>("age"));
        age.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn schoolName = new TableColumn("School");
        schoolName.setCellValueFactory(new PropertyValueFactory<TableContent, String>("SchoolName"));
        schoolName.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn contactNumber = new TableColumn("Contact");
        contactNumber.setCellValueFactory(new PropertyValueFactory<TableContent, String>("ContactNumber"));
        contactNumber.setStyle("-fx-alignment: CENTER-RIGHT;");

        id.setMaxWidth(75);
        name.setMaxWidth(125);
        category.setMaxWidth(125);
        date.setMaxWidth(125);
        nic.setMaxWidth(125);
        email.setMaxWidth(125);
        height.setMaxWidth(50);
        weight.setMaxWidth(50);
        age.setMaxWidth(50);
        schoolName.setMaxWidth(125);
        contactNumber.setMaxWidth(125);

        id.setMinWidth(75);
        name.setMinWidth(150);
        category.setMinWidth(125);
        date.setMinWidth(125);
        nic.setMinWidth(125);
        email.setMinWidth(150);
        height.setMinWidth(50);
        weight.setMinWidth(50);
        age.setMinWidth(50);
        schoolName.setMinWidth(125);
        contactNumber.setMinWidth(125);


        table.setItems(data);
        System.out.println(data);
        table.getColumns().addAll(id, name, category,date,nic,email,height,weight,age,schoolName,contactNumber);

        Image image =  new Image("imgs/image.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1300);


        Pane root = new Pane();
        root.getChildren().addAll(imageView,address_lable,vBox);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setMaxWidth(1300);
        stage.setMinWidth(1300);
        stage.setMaxHeight(600);
        stage.setMinHeight(600);

        stage.setScene(scene);

        search.setOnAction(event -> {

            table.getItems().clear();

            try{

                int search_id = Integer.parseInt(text1.getText());
                ObservableList<TableContent> searched_data = FXCollections.observableArrayList();

                for(int i =0;i<member_list.size();i++){

                    if(member_list.get(i).getMembershipNumber() == search_id){

                        TableContent found = new TableContent();
                        found.setId(member_list.get(i).getMembershipNumber());
                        found.setName(member_list.get(i).getName());
                        found.setCategory(member_list.get(i).getCategory());
                        found.setDate(member_list.get(i).getStartMembershipDate().getDay()+"-"+member_list.get(i).getStartMembershipDate().getMonth()+"-"+member_list.get(i).getStartMembershipDate().getYear());
                        found.setNic(member_list.get(i).getNic());
                        found.setEmail(member_list.get(i).getEmail());
                        found.setHeight(member_list.get(i).getHeight());
                        found.setWeight(member_list.get(i).getWeight());

                        if(member_list.get(i).getCategory().equals("Student Member")){

                            StudentMember studentMember = (StudentMember)member_list.get(i);
                            found.setSchoolName(studentMember.getSchoolName());
                            found.setContactNumber(studentMember.getEmergencyContactNumber());

                        }
                        else if(member_list.get(i).getCategory().equals("Over 60 Member")){

                            Over60Member over60Member = (Over60Member)member_list.get(i);
                            found.setAge(over60Member.getAge());

                        }

                        searched_data.add(found);
                        table.setItems(searched_data);
                        break;

                    }

                }

            }catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error!");
                alert.setContentText(ex.toString());
                alert.show();
            }


        });

        reset.setOnAction(event -> {

            table.getItems().clear();

            for(int i =0;i<member_list.size();i++){

                TableContent content = new TableContent();

                content.setId(member_list.get(i).getMembershipNumber());
                content.setName(member_list.get(i).getName());
                content.setCategory(member_list.get(i).getCategory());
                content.setDate(member_list.get(i).getStartMembershipDate().getDay()+"-"+member_list.get(i).getStartMembershipDate().getMonth()+"-"+member_list.get(i).getStartMembershipDate().getYear());
                content.setNic(member_list.get(i).getNic());
                content.setEmail(member_list.get(i).getEmail());
                content.setHeight(member_list.get(i).getHeight());
                content.setWeight(member_list.get(i).getWeight());

                if(member_list.get(i).getCategory().equals("Student Member")){

                    StudentMember studentMember = (StudentMember)member_list.get(i);
                    content.setSchoolName(studentMember.getSchoolName());
                    content.setContactNumber(studentMember.getEmergencyContactNumber());


                }
                else if(member_list.get(i).getCategory().equals("Over 60 Member")){

                    Over60Member over60Member = (Over60Member)member_list.get(i);
                    content.setAge(over60Member.getAge());

                }


                data.add(content);

            }
            table.setItems(data);


        });

        return stage;

    }


    boolean isEmpty(){
        if(member_list.size() + temp_member_list.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    boolean isFull(){
        if(member_list.size() + temp_member_list.size() == 100){
            return true;
        }
        else{
            return false;
        }
    }

    int[] isAlreadyIn(int Membership_number){

        int array[] = new int[10];

        for(int i = 0;i < member_list.size() ;i++){
            if(Membership_number == member_list.get(i).getMembershipNumber()){

                array[0] = 1;
                array[1] = i;
                return array;
            }
        }

        for(int i = 0;i < temp_member_list.size() ;i++){
            if(Membership_number == temp_member_list.get(i).getMembershipNumber()){

                array[0] = 2;
                array[1] = i;
                return array;
            }
        }

        array[0] = 0;
        array[1] = 0;
        return array;
    }
}
