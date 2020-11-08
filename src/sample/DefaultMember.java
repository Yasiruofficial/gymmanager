package sample;

public class DefaultMember {


    private int MembershipNumber;
    private String Name;
    private Date StartMembershipDate;
    private String category;
    private String Nic;
    private String Email;
    private String Height;
    private String Weight;


    public int getMembershipNumber() {
        return MembershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        MembershipNumber = membershipNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getStartMembershipDate() {
        return StartMembershipDate;
    }

    public void setStartMembershipDate(Date startMembershipDate) {
        StartMembershipDate = startMembershipDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}
