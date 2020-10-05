package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;

@JsonRootName(value = "roomList")
public class JsonSerializableRoomList {
    private final List<JsonAdaptedRoom> patients = new ArrayList<>();

    @JsonCreator
    public JsonSerializableRoomList(@JsonProperty("patients") List<JsonAdaptedRoom> patients) {
        this.patients.addAll(patients);
    }

    public JsonSerializableRoomList(RoomList source) {
        patients.addAll(source.getRoomObservableList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
    }

    public RoomList toModelType() throws IllegalValueException {
        RoomList roomList = new RoomList();
        for (JsonAdaptedRoom jsonAdaptedRoom : patients) {
            Room room = jsonAdaptedRoom.toModelType();
            roomList.addRooms(room);
        }
        return roomList;
    }

}
