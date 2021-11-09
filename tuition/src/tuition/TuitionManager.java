package tuition;

import java.util.Scanner;

/**
 * This class is the user interface class that handles input commands and output
 * messages.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class TuitionManager {
    Scanner stdin = new Scanner(System.in);
    StudentList tuition_List = new StudentList();

    /**
     * Runs the program. Continually look for an input until the Q command is
     * executed.
     */
    public void run() {
        System.out.println("Let's start a new List!");
        boolean done = false;
        while (!done) {
            String command = stdin.next();
            switch (command) {
                case "I":// add an Instate student to the list
                case "O":// add an Outstate student to the list
                case "N":// add an International student to the list
                    String fname = stdin.next();
                    String lname = stdin.next();
                    String creditString = stdin.next();
                    String typeData = stdin.next();
                    int credit = Integer.parseInt(creditString);
                    if (!(Outstate.isValid(credit))) {
                        System.out.println("Cannot have 0 or negative credits!");
                        stdin.nextLine();
                        break;
                    }
                    if (command.equals("N") && !(International.isValid(credit))) {
                        System.out.println("International must have at least 9 credits!");
                        stdin.nextLine();
                        break;
                    }
                    if (command.equals("I")) {
                        if (!(Instate.isValid(credit, Integer.parseInt(typeData)))) {
                            System.out.println("Instate student is invalid!");
                            stdin.nextLine();
                            break;
                        }
                    }
                    add(fname, lname, credit, typeData, command);
                    break;

                case "R":// remove a student from the list
                    String fnameR = stdin.next();
                    String lnameR = stdin.next();
                    remove(fnameR, lnameR);
                    break;
                case "P":// print the list
                    print();
                    break;
                case "Q":// terminate the program
                    System.out.println("Program terminated!");
                    done = true;
                    break;
                default: // deal with bad command
                    System.out.println("Command '" + command + "' not supported!");
                    stdin.nextLine();
                    break;
            }
        }
        System.exit(0);
    } // run()

    /**
     * Add the student to the list.
     *
     * @param fname    first name of the student to add
     * @param lname    last name of the student to add
     * @param credit   credit of the student taking
     * @param typeData information of the student
     * @param type     type of the student
     */
    private void add(String fname, String lname, int credit, String typeData, String type) {
        // use for checking whether the student already exist in the list
        Instate contains = new Instate(fname, lname, 0, 0);
        if (tuition_List.contains(contains)) {
            System.out.println(fname + " " + lname + " is alreay in the List");
        } else {
            if (type.compareTo("I") == 0) {
                int fund = Integer.parseInt(typeData);
                Instate instate = new Instate(fname, lname, credit, fund);
                tuition_List.add(instate);
            } else if (type.compareTo("O") == 0) {
                boolean status;
                if (typeData.compareTo("T") == 0) {
                    status = true;
                } else {
                    status = false;
                }
                Outstate outstate = new Outstate(fname, lname, credit, status);
                tuition_List.add(outstate);
            } else {
                boolean status;
                if (typeData.compareTo("T") == 0) {
                    status = true;
                } else {
                    status = false;
                }
                International international = new International(fname, lname, credit, status);
                tuition_List.add(international);
            }
            System.out.println("Added");
        }
    }

    /**
     * Remove the student from the list.
     *
     * @param fname first name of the student to remove
     * @param lname last name of the student to remove
     */
    private void remove(String fname, String lname) {
        // any kind of student is OK
        Instate instate = new Instate(fname, lname, 0, 0);
        if (tuition_List.remove(instate)) {
            System.out.println("removed");
        } else {
            System.out.println("Student not exist");
        }
    }

    /**
     * Print out the list of students and the tuition amounts.
     */
    private void print() {
        if (tuition_List.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            tuition_List.print();
        }
    }

    public static void main(String[] args) {
        new TuitionManager().run();
    }
}
