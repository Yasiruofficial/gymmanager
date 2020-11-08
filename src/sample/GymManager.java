package sample;

import javafx.stage.Stage;

import java.util.ArrayList;

public interface GymManager {

    boolean addMember(DefaultMember members);
    String dltMember(String id);
    boolean printMember(ArrayList<DefaultMember> members);
    boolean sortMember(ArrayList<DefaultMember> members);
    boolean saveMembers();
    Stage openGui();

}
