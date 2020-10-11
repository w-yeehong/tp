package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;


public interface ReadOnlyRoomList {

    ObservableList<Room> getRoomObservableList();

}