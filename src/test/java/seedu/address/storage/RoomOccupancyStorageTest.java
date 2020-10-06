package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.room.ReadOnlyRoomList;
import seedu.address.model.room.RoomList;

class RoomOccupancyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonRoomOccupancyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRoomList(null));
    }
    private java.util.Optional<ReadOnlyRoomList> readRoomList(String filePath) throws Exception {
        return new JasonRoomOccupancyStorage(Paths.get(filePath)).readOnlyRoomOccupancy(addToTestDataPathIfNotNull(filePath));
    }
    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }
    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRoomList("NonExistentFile.json").isPresent());
    }
    @Test
    public void readAddressBook_invalidPatientAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoomList("invalidRoomInRoomList.json"));
    }
    @Test
    public void readAddressBook_invalidAndValidPatientAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoomList("invalidAndValidRoomInRoomList.json"));
    }
    @Test
    public void saveRoomList_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(null,"roomsOccupied.txt"));
    }

    @Test
    public void saveRoomList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(new RoomList(), null));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveRoomListRoomsOccupied(RoomList roomList, String roomsOccupied) {
        try {
            new JasonRoomOccupancyStorage(Paths.get(roomsOccupied))
                    .saveOccupiedRooms(roomList, Paths.get(roomsOccupied));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }


}
