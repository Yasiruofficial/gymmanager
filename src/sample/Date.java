package sample;

public class Date {

    private int year;
    private int month;
    private int day;
    private int[] allMonths = {31,28,31,30,31,30,31,31,30,31,30,31};



    public int getYear() {

        return year;
    }

    public void setYear(int year) {

        this.year = year;

    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {

        return day;
    }

    public void setDay(int day) {

        if(day > allMonths[this.month-1]){
            throw new IllegalArgumentException("Invalid Date");
        }
        else{
            this.day = day;
        }

    }
}
