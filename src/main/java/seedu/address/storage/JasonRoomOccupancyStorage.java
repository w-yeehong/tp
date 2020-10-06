package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.room.ReadOnlyRoomList;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;

/**
 * Reads data from storage data files and imports them into RoomList
 */
public class JasonRoomOccupancyStorage {
    private Path roomsOccupied;

    public JasonRoomOccupancyStorage() {}
    /**
     * Creates RoomOccupancyStorage object that reads the number of rooms a hotel has and the rooms which are
     * occupied
     */
    public JasonRoomOccupancyStorage(Path roomsOccupied) {
        this.roomsOccupied = roomsOccupied;
    }

    public Path getRoomsOccupied() {
        return roomsOccupied;
    }

    /**
     * Returns RoomList data as a {@link ReadOnlyRoomList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    public Optional<ReadOnlyRoomList> readOnlyRoomOccupancy(Path filePath) throws IOException, DataConversionException {
        Optional<JsonSerializableRoomList> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoomList.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataConversionException(ive);
        }
    }

    /**
     * Function saves the room numbers of occupied rooms
     *
     * @param roomList contains information of which rooms are occupied
     * @param fileRoomsOccupied Path to where to write the room numbers of occupied rooms
     */
    public void saveOccupiedRooms(RoomList roomList, Path fileRoomsOccupied) throws IOException {
        FileUtil.createIfMissing(fileRoomsOccupied);
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(roomList.getRoomObservableList());
        JsonUtil.saveJsonFile(new JsonSerializableRoomList(roomList), fileRoomsOccupied);
    }
}
