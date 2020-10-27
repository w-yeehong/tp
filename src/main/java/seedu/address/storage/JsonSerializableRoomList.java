package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRoomList;
import seedu.address.model.RoomList;
import seedu.address.model.room.Room;

@JsonRootName(value = "roomList")
public class JsonSerializableRoomList {

    @JsonProperty("rooms")
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRoomList} with the given rooms.
     */
    @JsonCreator
    public JsonSerializableRoomList(@JsonProperty("rooms") List<JsonAdaptedRoom> rooms) {
        this.rooms.addAll(rooms);
    }

    /**
     * Converts a given {@code RoomList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRoomList}.
     */
    public JsonSerializableRoomList(ReadOnlyRoomList source) {
        rooms.addAll(source.getRoomObservableList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task list into the model's {@code RoomList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RoomList toModelType() throws IllegalValueException {
        RoomList roomList = new RoomList();
        for (JsonAdaptedRoom jsonAdaptedRoom : rooms) {
            Room room = jsonAdaptedRoom.toModelType();
            roomList.addRooms(room);
        }
        return roomList;
    }

}
