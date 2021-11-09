package tuition;

/**
 * This is an abstract class for Student.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public abstract class Student implements Comparable {
    private String fname;
    private String lname;
    protected int credit;
    protected static final int part_Time_Fee = 846;
    protected static final int full_Time_Fee = 1441;
    protected static final int part_Time_Credit_Limit = 12;
    protected static final int credit_Max = 15;

    /**
     * This is constructor which creates Student object.
     *
     * @param fname  (string) first name of the student
     * @param lname  (string) last name of the student
     * @param credit (int) credit of the student taking
     */
    public Student(String fname, String lname, int credit) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    } // constructor

    /**
     * This method is interface method, used to compare two Student objects.
     *
     * @param obj student to compare with
     * @return int of comparison values
     */
    // must implement compareTo method because Student class implements the
    // Comparable Interface
    // return 0 if fname and lname are equal, -1 if this < obj, 1 if this > obj
    public int compareTo(Object obj) {
        Student s = (Student) obj;
        if (this.fname.equals(s.fname) && this.lname.equals(s.lname)) {
            return 0;
        } else if (this.fname.compareTo(this.fname) < 0
                || (this.fname.equals(s.fname) && this.lname.compareTo(s.lname) < 0)) { // Questions?
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * This method is used to covert a Student object to a string.
     *
     * @return String format of the object.
     */
    // return a string with fname, lname and credit hours; subclasses will be using
    // this method.
    public String toString() {
        return this.fname + " " + this.lname + " " + this.credit;
    }

    /**
     * This is an abstract method to compute the tuition fee of student.
     *
     * @return the tuition fee.
     */
    // compute the tuition due; concrete implementation is required in the
    // subclasses.
    public abstract int tuitionDue();

}
