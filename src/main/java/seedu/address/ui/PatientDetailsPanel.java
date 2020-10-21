package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.model.patient.Patient;

/**
 * Panel containing the details of the patient.
 */
public class PatientDetailsPanel extends UiPart<Region> {

    private static final String FXML = "PatientDetailsPanel.fxml";
    private Image imagePatient = new Image(getClass().getResourceAsStream("/images/patient_icon.png"));

    public final Patient patient;

    @FXML
    private Label name;
    @FXML
    private Label patientHeader;
    @FXML
    private Label phone;
    @FXML
    private Label temperature;
    @FXML
    private Label periodOfStay;
    @FXML
    private Label age;
    @FXML
    private Label comment;

    /**
     * Creates a {@code PatientDetailsPanel} with the given {@code Patient}.
     */
    public PatientDetailsPanel(Patient patient) {
        super(FXML);
        this.patient = patient;
        setPatientDetails();
    }

    /**
     * Sets the details of the patient.
     *
     */
    private void setPatientDetails() {
        //sets up the image
        ImageView imageView = new ImageView(imagePatient);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        name.setGraphic(imageView);
        //fill in the details
        name.setText(patient.getName().toString());
        patientHeader.setUnderline(true);
        temperature.setText("Temperature: " + patient.getTemperature().toString());
        periodOfStay.setText("Period of stay: " + patient.getPeriodOfStay().readerFriendlyString());
        phone.setText("Phone number: " + patient.getPhone().toString());
        age.setText("Age: " + patient.getAge().toString());
        comment.setText("Comment: " + patient.getComment().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof PatientDetailsPanel)) {
            return false;
        }
        // state check
        PatientDetailsPanel panel = (PatientDetailsPanel) other;
        return patient.equals(panel.patient);
    }
}
