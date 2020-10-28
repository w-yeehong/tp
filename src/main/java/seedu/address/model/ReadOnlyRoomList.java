package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;

//@@author itssodium
public interface ReadOnlyRoomList {

    ObservableList<Room> getRoomObservableList();

}
