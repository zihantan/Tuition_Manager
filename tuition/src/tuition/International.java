package tuition;

/**
 * This class is for creating International type of Student. A subclass for
 * Student.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class International extends Student {
    private boolean exchange;
    private static final int tuition_Per_Credit = 945;
    private static final int student_Fee = 350;

    /**
     * This is constructor which creates International student object.
     *
     * @param fname    (string) first name of the student
     * @param lname    (string) last name of the student
     * @param credit   (int) credit of the student taking
     * @param exchange (boolean) whether the student is exchange or not.
     */
    public International(String fname, String lname, int credit, boolean exchange) {
        super(fname, lname, credit);
        this.exchange = exchange;
    }

    /**
     * This method is used to calculate the tuition of an International student.
     *
     * @return the tuition of this student.
     */
    public int tuitionDue() {
        // International Student must enroll at least 9 credits
        if (exchange) {
            return full_Time_Fee + student_Fee;
        } else {
            if (this.credit >= credit_Max) {
                return credit_Max * tuition_Per_Credit + full_Time_Fee + student_Fee;
            } else if (this.credit >= part_Time_Credit_Limit) {
                return this.credit * tuition_Per_Credit + full_Time_Fee + student_Fee;
            } else {
                return this.credit * tuition_Per_Credit + part_Time_Fee + student_Fee;
            }
        }
    }

    /**
     * This method is used to covert an International student object to a string.
     *
     * @return String format of the object.
     */
    public String toString() {
        if (this.exchange) {
            return super.toString() + " International exchange student";
        } else {
            return super.toString() + " International student";
        }
    }

    /**
     * This method is used to check if input is valid.
     *
     * @param credit (int) the input credit
     * @return true if input is valid; false if input is not valid.
     */
    public static boolean isValid(int credit) {
        if (credit < 9) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Student zihan = new International("ZIHAN", "TAN", 12, true);// test constructor
        System.out.println(zihan.toString());// test toString()
        System.out.println(zihan.tuitionDue());// test tuitionDue() with full-time and exchange, should print 1791
        Student jianyu = new International("Jianyu", "Qiu", 12, false);
        System.out.println(jianyu.tuitionDue());// test tuitionDue() with full-time and not exchange, should print 13131
        Student may = new International("May", "Anderson", 9, false);
        System.out.println(may.tuitionDue());// test tuitionDue() with part-time and not exchange, should print 9701
        Student lauren = new International("Lauren", "Brown", 10, true);
        System.out.println(lauren.tuitionDue());// test tuitionDue with part-time and exchange, should print 1791

        /* testing for isValid method */
        International good = new International("Good", "Man", 0, true);
        System.out.println(isValid(good.credit));// test isValid() with 0 credit, should print false.
        International peter = new International("Peter", "Parker", -1, false);
        System.out.println(isValid(peter.credit));// test isValid() with negative credit, should print false.
        International mary = new International("Mary", "Yang", 5, true);
        System.out.println(isValid(mary.credit));// test isValid() with credit less than 9, should print false.
        International harry = new International("Harry", "Porter", 9, true);
        System.out.println(isValid(harry.credit));// test isValid() with valid credit, should print true.
    }
}