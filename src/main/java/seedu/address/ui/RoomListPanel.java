package seedu.address.ui;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;

/**
 * Panel containing the list of rooms.
 */
public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomListPanel.class);

    private RoomDetailsPanel roomDetailsPanel;

    @FXML
    private ListView<Room> roomListView;

    @FXML
    private AnchorPane roomDetailsPanelPlaceholder;


    /**
     * Handles mouse click event on the items
     *
     * @param mouseEvent
     */
    @FXML public void handleMouseClick(MouseEvent mouseEvent) {
        Room roomToDisplay = roomListView.getSelectionModel().getSelectedItem();
        roomDetailsPanel = new RoomDetailsPanel(roomToDisplay);
        roomDetailsPanelPlaceholder.getChildren().add(roomDetailsPanel.getRoot());
    }

    /**
     * Creates a {@code RoomListPanel} with the given {@code ObservableList}.
     */
    public RoomListPanel(PriorityQueue<Room> roomList) {
        super(FXML);
        ObservableList<Room> listToDisplayInUI = convertPriorityQueue(roomList);
        if (!listToDisplayInUI.isEmpty()) {
            roomDetailsPanel = new RoomDetailsPanel(listToDisplayInUI.get(0));
            roomDetailsPanelPlaceholder.getChildren().add(roomDetailsPanel.getRoot());
        }
        roomListView.setItems(listToDisplayInUI);
        roomListView.setCellFactory(listView -> new RoomListViewCell());
    }

    /**
     * Converts a {@code roomList} queue into an {@code ObservableList}.
     *
     * @param roomList PriorityQueue containing all the rooms.
     * @return A {@code ObservableList<Room>}.
     */
    private ObservableList<Room> convertPriorityQueue(PriorityQueue<Room> roomList) {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        while (roomList.size() > 0) {
            roomArrayList.add(roomList.poll());
        }
        ObservableList<Room> roomObservableList = FXCollections.observableArrayList(roomArrayList);
        return roomObservableList;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Room} using a {@code RoomCard}.
     */
    class RoomListViewCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean empty) {
            super.updateItem(room, empty);

            if (empty || room == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoomCard(room).getRoot());
            }
        }
    }

}
