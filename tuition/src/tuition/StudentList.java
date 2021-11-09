package tuition;
/**
 * This is an array-based growable class that has functions such as adding,
 * removing, printing.
 * 
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class StudentList {
    private final int NOT_FOUND = -1;
    private final int GROW_SIZE = 4;
    private Student[] studentList;
    private int numStudent;

    /**
     * This is a studentList constructor.
     */
    public StudentList() {
        // this is the default constructor
        this.studentList = new Student[GROW_SIZE];
        this.numStudent = 0;
    }

    /**
     * Grow the list by GROW_SIZE
     */
    private void grow() {
        Student[] temp = new Student[studentList.length + GROW_SIZE];
        for (int i = 0; i < numStudent; i++) {
            temp[i] = studentList[i];
        }
        studentList = temp;
    }

    /**
     * Checks if the studentList is empty or not.
     * 
     * @return true if the list is empty; false if it is not empty.
     */
    public boolean isEmpty() {
        if (numStudent == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Finds the index of the team member.
     * 
     * @param s the Student to be found
     * @return the index of the student to be found in the list
     */
    private int find(Student s) {
        for (int index = 0; index < this.numStudent; index++) {
            if (s.compareTo(this.studentList[index]) == 0) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Checks if the student is in the studentList or not.
     * 
     * @return true if the list contains the person; false if the list does not
     *         contain this person.
     */
    public boolean contains(Student s) {
        for (int index = 0; index < this.numStudent; index++) {
            if (s.compareTo(this.studentList[index]) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add the student to the list.
     * 
     * @param s the Student to add
     */
    public void add(Student s) {
        if (numStudent % GROW_SIZE == 0 && numStudent != 0) {
            grow();
        }
        studentList[numStudent] = s;
        numStudent++;
    }

    /**
     * Remove the student from the list.
     * 
     * @param s the Student to remove
     * @return true if the student is removed; false if the student is not found
     */
    public boolean remove(Student s) {
        int index = find(s);
        if (index == NOT_FOUND) {
            return false;
        }
        if (index == numStudent - 1) {
            studentList[index] = null;
            numStudent--;
            return true;
        }
        studentList[index] = studentList[numStudent - 1];
        studentList[numStudent - 1] = null;
        numStudent--;
        return true;
    }

    /**
     * Prints out the list of students and the tuition amounts.
     */
    public void print() {
        for (int index = 0; index < this.numStudent; index++) {
            System.out.println(studentList[index].toString() + " tuition due: $ " + studentList[index].tuitionDue());
        }
    }

    /**
     * Return the list of students and their tuition amount in String.
     * @return String the list of students and their tuition.
     */
    public String toString(){
        String returnString = "";
        for (int index = 0; index < this.numStudent; index++) {
            returnString += studentList[index].toString() + " tuition due: $ " + studentList[index].tuitionDue() + "\n";
        }
        return returnString;
    }
}
