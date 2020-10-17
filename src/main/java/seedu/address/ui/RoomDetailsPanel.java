package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;

/**
 * Panel containing the room details.
 */
public class RoomDetailsPanel extends UiPart<Region> {
    private static final String FXML = "RoomDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomDetailsPanel.class);

    @FXML
    private Label roomNumber;

    @FXML
    private Label patientDetails;

    @FXML
    private Label taskDetails;

    /**
     * Creates a {@code RoomDetailsPanel} with the given {@code Room}.
     */
    public RoomDetailsPanel(Room room) {
        super(FXML);
        setRoomDetails(room);
    }

    /**
     * Sets the room patient and task details.
     *
     * @param room To set the details for.
     */
    private void setRoomDetails(Room room) {
        if (room.getPatient() != null) {
            patientDetails.setText(room.getPatient().toString());
        } else {
            patientDetails.setText("No Patient Present.");
        }
        roomNumber.setText("Room #" + room.getRoomNumber());
        taskDetails.setText(room.getTaskList().toString());
    }
}
