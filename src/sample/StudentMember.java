package sample;

public class StudentMember extends DefaultMember{

    private String SchoolName;
    private String EmergencyContactNumber;


    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        EmergencyContactNumber = emergencyContactNumber;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    @Override
    public int getMembershipNumber() {
        return super.getMembershipNumber();
    }

    @Override
    public void setMembershipNumber(int membershipNumber) {
        super.setMembershipNumber(membershipNumber);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Date getStartMembershipDate() {
        return super.getStartMembershipDate();
    }

    @Override
    public void setStartMembershipDate(Date startMembershipDate) {
        super.setStartMembershipDate(startMembershipDate);
    }

    @Override
    public String getCategory() {
        return super.getCategory();
    }

    @Override
    public void setCategory(String category) {
        super.setCategory(category);
    }

    @Override
    public String getNic() {
        return super.getNic();
    }

    @Override
    public void setNic(String nic) {
        super.setNic(nic);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getHeight() {
        return super.getHeight();
    }

    @Override
    public void setHeight(String height) {
        super.setHeight(height);
    }

    @Override
    public String getWeight() {
        return super.getWeight();
    }

    @Override
    public void setWeight(String weight) {
        super.setWeight(weight);
    }
}
