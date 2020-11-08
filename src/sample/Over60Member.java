package sample;

public class Over60Member extends DefaultMember{

    private int Age;



    public int getAge() {
        return Age;
    }

    public void setAge(int age) {

        if(age < 60){

            throw new IllegalArgumentException("Member is not Over 60");
        }
        else{
            Age = age;
        }

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
