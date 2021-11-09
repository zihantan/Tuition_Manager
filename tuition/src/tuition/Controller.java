package tuition;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This is the controller class. This controls the GUI and manages user inputs.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class Controller {

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField credits;
    @FXML
    private TextArea console;
    @FXML
    private RadioButton instate;
    @FXML
    private RadioButton outstate;
    @FXML
    private RadioButton international;
    @FXML
    private CheckBox tri;
    @FXML
    private CheckBox exchange;
    @FXML
    private CheckBox funds_Check;
    @FXML
    private TextField funds;

    StudentList tuition_List = new StudentList();
    private String console_Text = "";

    /**
     * Add student to the list. This method is called when the Add button is clicked in the GUI.
     */
    public void add() {
        String first_Name = fname.getText();
        String last_Name = lname.getText();
        int number_Of_Credits;
        // No first name input
        if (first_Name.equals("")) {
            console_Text += "Must have a first-name!\n";
            update_Console();
            return;
        }
        // No last name input
        if (last_Name.equals("")) {
            console_Text += "Must have a last-name!\n";
            update_Console();
            return;
        }
        // Parsing int to credit. Also catches exceptions when no number entered.
        try {
            number_Of_Credits = Integer.parseInt(credits.getText());
        } catch (NumberFormatException e) {
            console_Text += "Please type a number for number of credits!\n";
            update_Console();
            return;
        }
        // Student type is not selected
        if (!international.isSelected() && !outstate.isSelected() && !instate.isSelected()) {
            console_Text += "Please select a student type!\n";
            update_Console();
            return;
        }
        // If instate student
        if (instate.isSelected()) {
            int funds_Value = 0;
            if (funds_Check.isSelected()) {
                try {
                    funds_Value = Integer.parseInt(funds.getText());
                } catch (NumberFormatException e) {
                    console_Text += "Please type a number for funds!\n";
                    update_Console();
                    return;
                }
            }
            if (!(Instate.isValid(number_Of_Credits, funds_Value))) {
                console_Text += "Instate student is invalid!\n";
                update_Console();
                return;
            }
            Instate new_Instate_Student = new Instate(first_Name, last_Name, number_Of_Credits, funds_Value);
            if (tuition_List.contains(new_Instate_Student)) {
                console_Text += "Student already exists!\n";
                update_Console();
                return;
            } else {
                tuition_List.add(new_Instate_Student);
                console_Text += "Added\n";
                update_Console();
                return;
            }
        }
        // If outstate student
        if (outstate.isSelected()) {
            Outstate new_Outstate_Student = new Outstate(first_Name, last_Name, number_Of_Credits, tri.isSelected());
            if (!Outstate.isValid(number_Of_Credits)) {
                console_Text += "Outstate student is invalid!\n";
                update_Console();
                return;
            }
            if (tuition_List.contains(new_Outstate_Student)) {
                console_Text += "Student already exists!\n";
                update_Console();
                return;
            } else {
                tuition_List.add(new_Outstate_Student);
                console_Text += "Added\n";
                update_Console();
                return;
            }
        }
        // If international student
        if (international.isSelected()) {
            International new_International_Student = new International(first_Name, last_Name, number_Of_Credits, exchange.isSelected());
            if (!International.isValid(number_Of_Credits)) {
                console_Text += "International student is invalid!\n";
                update_Console();
                return;
            }
            if (tuition_List.contains(new_International_Student)) {
                console_Text += "Student already exists!\n";
                update_Console();
                return;
            } else {
                tuition_List.add(new_International_Student);
                console_Text += "Added\n";
                update_Console();
                return;
            }
        }
    }

    /**
     * Remove student from the list if they are in the list. Method is called when Remove button is clicked in the GUI.
     */
    public void remove() {
        String first_Name = fname.getText();
        String last_Name = lname.getText();
        Instate instate = new Instate(first_Name, last_Name, 0, 0);
        // No first name input
        if (first_Name.equals("")) {
            console_Text += "Must have a first-name!\n";
            update_Console();
            return;
        }
        // No last name input
        if (last_Name.equals("")) {
            console_Text += "Must have a last-name!\n";
            update_Console();
            return;
        }
        if (tuition_List.remove(instate)) {
            console_Text += "removed\n";
        } else {
            console_Text += "Student not exist\n";
        }
        update_Console();
    }

    /**
     * Print the student list in the GUI console. Method is called when Print button is clicked in the GUI.
     */
    public void print() {
        if (tuition_List.isEmpty()) {
            console_Text += "Empty List!\n";
        } else {
            console_Text += tuition_List.toString();
        }
        update_Console();
    }

    /**
     * Prints the console text in the GUI console.
     */
    private void update_Console() {
        console.setText(console_Text);
        console.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * Initialize the GUI for initial start up. This method is only called once when the program starts.
     */
    public void initialize() {
        tri.setDisable(true);
        exchange.setDisable(true);
        funds_Check.setDisable(true);
        funds.setDisable(true);

        // Add a listener for the instate radio button.
        instate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    outstate.setSelected(false);
                    international.setSelected(false);
                    tri.setSelected(false);
                    tri.setDisable(true);
                    exchange.setSelected(false);
                    exchange.setDisable(true);
                    funds_Check.setDisable(false);
                }
            }
        });

        // Add a listener for the instate funds_Check CheckBox.
        funds_Check.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    funds.setDisable(false);
                } else {
                    funds.setText("");
                    funds.setDisable(true);
                }
            }
        });

        // Add a listener for the outstate radio button.
        outstate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    instate.setSelected(false);
                    international.setSelected(false);
                    funds_Check.setSelected(false);
                    funds_Check.setDisable(true);
                    funds.setText("");
                    funds.setDisable(true);
                    exchange.setSelected(false);
                    exchange.setDisable(true);
                    tri.setDisable(false);
                }
            }
        });

        // Add a listener for the international radio button.
        international.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    instate.setSelected(false);
                    outstate.setSelected(false);
                    funds_Check.setSelected(false);
                    funds_Check.setDisable(true);
                    funds.setText("");
                    funds.setDisable(true);
                    tri.setSelected(false);
                    tri.setDisable(true);
                    exchange.setDisable(false);
                }
            }
        });
    }
}
