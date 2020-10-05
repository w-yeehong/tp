package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.tasks.Task;

public class JsonAdaptedRoom {

    private int roomNumber;
    private boolean isOccupied;
    private String patient;
    private String task;

   /* @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("room number") int roomNumber,
                           @JsonProperty("is occupied?") boolean isOccupied,
                           @JsonProperty("patient information") String patient,
                           @JsonProperty("current tasks") String task) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = patient;
        this.task = task;
    }*/

    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("room number") int roomNumber,
                           @JsonProperty("is occupied?") boolean isOccupied) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
    }

    public JsonAdaptedRoom(Room source) {
        this.roomNumber = source.getRoomNumber();
        this.isOccupied = source.isOccupied();
        //this.patient = source.getPatient().toString();
        //this.task = source.getTask().toString();
    }

    public Room toModelType() throws IllegalValueException {
        return new Room(roomNumber, isOccupied);
    }
}
