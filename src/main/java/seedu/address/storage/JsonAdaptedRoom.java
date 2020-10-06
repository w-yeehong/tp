package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.Room;

public class JsonAdaptedRoom {

    private int roomNumber;
    private boolean isOccupied;
    private String patient;
    private String task;

    /**
     * Creates JsonAdaptedRoom based on the inputs given by the user of roomNumber and isOccupied
     */
    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("room number") int roomNumber,
                           @JsonProperty("is occupied?") boolean isOccupied) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
    }

    /**
     * Creates JsonAdaptedRoom from Room object given
     */
    public JsonAdaptedRoom(Room source) {
        this.roomNumber = source.getRoomNumber();
        this.isOccupied = source.isOccupied();
    }

    public Room toModelType() throws IllegalValueException {
        return new Room(roomNumber, isOccupied);
    }
}
