package tuition;

/**
 * This class is for creating Instate type of Student. A subclass for Student.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class Instate extends Student {
    private int funds;
    private static final int tuition_Per_Credit = 433;

    /**
     * This is constructor which creates Instate student object.
     *
     * @param fname  (string) first name of the student
     * @param lname  (string) last name of the student
     * @param credit (int) credit of the student taking
     * @param funds  (int) funds of the student receiving
     */
    public Instate(String fname, String lname, int credit, int funds) {
        super(fname, lname, credit);
        this.funds = funds;
    }

    /**
     * This method is used to calculate the tuition of an Instate student.
     *
     * @return the tuition of this student.
     */
    public int tuitionDue() {
        if (this.credit < part_Time_Credit_Limit) {// part time
            return tuition_Per_Credit * this.credit + part_Time_Fee;
        }
        if (this.credit >= credit_Max) {
            return credit_Max * tuition_Per_Credit + full_Time_Fee - this.funds;
        } else {
            return this.credit * tuition_Per_Credit + full_Time_Fee - this.funds;
        }
    }

    /**
     * This method is used to covert an Instate student object to a string.
     *
     * @return String format of the object.
     */
    public String toString() {
        return super.toString() + " Instate with " + this.funds + " funds";
    }

    /**
     * This method is used to check if input is valid.
     *
     * @param credit (int) the input credit
     * @param funds  (int) the input funds
     * @return true if input is valid; false if input is not valid.
     */
    public static boolean isValid(int credit, int funds) {
        if (credit <= 0) {
            return false;
        }
        if (funds < 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Student zihan = new Instate("ZIHAN", "TAN", 9, 0);// test constructor
        System.out.println(zihan);// test toString()
        System.out.println(zihan.tuitionDue());// test tuitionDue with part-time and no funds, should print 4743
        Student jianyu = new Instate("Jianyu", "Qiu", 17, 1000);
        System.out.println(jianyu.tuitionDue());// test tuitionDue with full-time and $1000 funds, should print 6936
        Student may = new Instate("May", "Anderson", 17, 0);
        System.out.println(may.tuitionDue());// test tuitionDue with full-time and no funds, should print 7936
        Student zoe = new Instate("Zoe", "Tan", 8, 1000);
        System.out.println(zoe.tuitionDue());// test tuitionDue with part-time and funds, should print 4310

        /* testing for isValid method */
        Instate lauren = new Instate("Lauren", "Brown", 5, 1000);
        System.out.println(isValid(lauren.credit, lauren.funds));// test isValid() with part-time and $1000 funds,
        // should print false.
        Instate peter = new Instate("Peter", "Parker", -1, 0);
        System.out.println(isValid(peter.credit, peter.funds));// test isValid() with negative credit, should print
        // false.
        Instate david = new Instate("David", "Lee", 10, -3);
        System.out.println(isValid(david.credit, david.funds));// test isValid() with negative funds, should print
        // false.
        Instate joe = new Instate("Joe", "Kim", 0, 0);
        System.out.println(isValid(joe.credit, joe.funds));// test isValid() with 0 credit, should print false.
        Instate mary = new Instate("Mary", "Yang", 5, 0);
        System.out.println(isValid(mary.credit, mary.funds));// test isValid() with part-time and 0 funds, should print
        // true.
        Instate jack = new Instate("Jack", "Chan", 8, 1000);
        System.out.println(isValid(jack.credit, jack.funds));// test isValid() with part-time and 1000 funds, should
        // print true.
    }
}