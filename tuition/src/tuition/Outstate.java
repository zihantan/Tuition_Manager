package tuition;

/**
 * This class is for creating Outstate type of Student. A subclass for Student.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class Outstate extends Student {
    private boolean tristate;
    private static final int tuition_Per_Credit = 756;
    private static final int discount = 200;

    /**
     * This is the constructor for creating Outstate student object.
     *
     * @param fname    first name of the student.
     * @param lname    last name of the student.
     * @param credit   credit of the student taking.
     * @param tristate whether the student is from tri-state area.
     */
    public Outstate(String fname, String lname, int credit, boolean tristate) {
        super(fname, lname, credit);
        this.tristate = tristate;
    }

    /**
     * This method is used to calculate the tuition of an Outstate student.
     *
     * @return the tuition of this student.
     */
    public int tuitionDue() {
        if (tristate) {
            if (this.credit >= credit_Max) {
                return credit_Max * (tuition_Per_Credit - discount) + full_Time_Fee;
            } else if (this.credit >= part_Time_Credit_Limit) {
                return this.credit * (tuition_Per_Credit - discount) + full_Time_Fee;
            } else {
                return this.credit * tuition_Per_Credit + part_Time_Fee;
            }
        } else {
            if (this.credit >= credit_Max) {
                return credit_Max * tuition_Per_Credit + full_Time_Fee;
            } else if (this.credit >= part_Time_Credit_Limit) {
                return this.credit * tuition_Per_Credit + full_Time_Fee;
            } else {
                return this.credit * tuition_Per_Credit + part_Time_Fee;
            }
        }
    }

    /**
     * This method is used to covert an Outstate student object to a string.
     *
     * @return String format of the object.
     */
    public String toString() {
        if (this.tristate) {
            return super.toString() + " Outstate with discount";
        } else {
            return super.toString() + " Outstate without discount";
        }
    }

    /**
     * This method is used to check if input is valid.
     *
     * @param credit (int) the input credit
     * @return true if input is valid; false if input is not valid.
     */
    public static boolean isValid(int credit) {
        if (credit <= 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Student zihan = new Outstate("ZIHAN", "TAN", 17, true);// test constructor
        System.out.println(zihan);// test toString()
        System.out.println(zihan.tuitionDue());// test tuitionDue() with full-time and tuition discount, should print
        // 9781
        Student jianyu = new Outstate("Jianyu", "Qiu", 8, true);
        System.out.println(jianyu.tuitionDue());// test tuitionDue() with part-time and tuition discount, should print
        // 6894
        Student may = new Outstate("May", "Anderson", 17, false);
        System.out.println(may.tuitionDue());// test tuitionDue() with full-time and no discount, should print 12781
        Student lauren = new Outstate("Lauren", "Brown", 5, false);
        System.out.println(lauren.tuitionDue());// test tuitionDue() with part-time and no discount, should print 4626

        /* testing for isValid method */
        Outstate good = new Outstate("Good", "Man", 0, true);
        System.out.println(isValid(good.credit));// test isValid() with 0 credit, should print false.
        Outstate peter = new Outstate("Peter", "Parker", -1, false);
        System.out.println(isValid(peter.credit));// test isValid() with negative credit, should print false.
        Outstate mary = new Outstate("Mary", "Yang", 5, true);
        System.out.println(isValid(mary.credit));// test isValid() with part-time and tuition discount, should print
        // true.
    }
}