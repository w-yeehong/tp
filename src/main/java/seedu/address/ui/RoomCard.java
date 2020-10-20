package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.room.Room;

/**
 * An UI component that displays information of a {@code Room}.
 */
public class RoomCard extends UiPart<Region> {

    private static final String FXML = "RoomListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CovigentApp level 4</a>
     */

    public final Room room;
    private Image unoccupied = new Image(this.getClass().getResourceAsStream("/images/green_circle.png"));
    private Image occupied = new Image(this.getClass().getResourceAsStream("/images/red_circle.png"));

    @FXML
    private HBox cardPane;
    @FXML
    private Label roomNumber;
    @FXML
    private ImageView showOccupancy;

    /**
     * Creates a {@code RoomCode} with the given {@code Room} and index to display.
     */
    public RoomCard(Room room) {
        super(FXML);
        this.room = room;
        roomNumber.setText(String.format("Room #%d", room.getRoomNumber()));
        if (room.isOccupied()) {
            showOccupancy.setImage(occupied);
        } else {
            showOccupancy.setImage(unoccupied);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoomCard)) {
            return false;
        }

        // state check
        RoomCard card = (RoomCard) other;
        return roomNumber.getText().equals(card.roomNumber.getText())
                && room.equals(card.room);
    }
}
