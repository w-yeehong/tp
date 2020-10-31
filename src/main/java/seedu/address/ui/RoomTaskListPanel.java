package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.room.Room;

/**
 * Panel containing the list of room with tasks.
 */
public class RoomTaskListPanel extends UiPart<Region> {

    private static final String FXML = "RoomTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomTaskListPanel.class);

    @FXML
    private VBox roomBox;

    /**
     * Creates a {@code RoomTaskListPanel} with the given {@code ObservableList}.
     */
    public RoomTaskListPanel(ObservableList<Room> roomTaskList) {
        super(FXML);
        populatePanel(roomTaskList);
        updateDetailsIfChanged(roomTaskList);
    }

    /**
     * Populates the {@code RoomTaskListPanel} with the {@code Room} containing at least one {@code Task}.
     *
     * @param roomTaskList The list of rooms containing tasks with which to populate the panel.
     */
    private void populatePanel(ObservableList<Room> roomTaskList) {
        for (Room room : roomTaskList) {
            roomBox.getChildren().add(new RoomTaskDetailsPanel(room).getRoot());
        }
    }

    /**
     * Clears old data and repopulates the @code RoomTaskListPanel} with the {@code Room} containing at least
     * one {@code Task}.
     *
     * @param roomTaskList The list of rooms containing tasks with which to repopulate the panel.
     */
    private void resetPanel(ObservableList<Room> roomTaskList) {
        // Naive solution; For better performance, employ some form of caching
        roomBox.getChildren().clear();
        populatePanel(roomTaskList);
    }

    //@@author w-yeehong
    /**
     * Attaches a listener to {@code roomTaskList}, repopulating the panel whenever
     * there are removals in the list of rooms.
     *
     * Fixes the issue of the panel not refreshing when a room is removed from the
     * list of rooms.
     *
     * @param roomTaskList to listen for changes.
     */
    private void updateDetailsIfChanged(ObservableList<Room> roomTaskList) {
        roomTaskList.addListener(new ListChangeListener<Room>() {
            @Override
            public void onChanged(Change<? extends Room> change) {
                while (change.next()) {
                    int indexOfChange = change.getFrom();
                    Index index = Index.fromZeroBased(indexOfChange);
                    logger.info("Changes detected in Room " + index.getOneBased()
                            + ". Updating RoomTaskListPanel...");
                    resetPanel(roomTaskList);
                }
            }
        });
    }
    //@@author w-yeehong
}
