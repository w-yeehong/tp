package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRoomList;

public interface RoomRecordsStorage {

    Path getRoomsOccupied();

    Optional<ReadOnlyRoomList> readOnlyRoomOccupancy() throws DataConversionException;

    Optional<ReadOnlyRoomList> readOnlyRoomOccupancy(Path filePath) throws DataConversionException;

    void saveOccupiedRooms(ReadOnlyRoomList roomList) throws IOException;

    void saveOccupiedRooms(ReadOnlyRoomList roomList, Path fileRoomsOccupied) throws IOException;
}
