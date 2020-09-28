package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import seedu.address.model.RoomBook;
import seedu.address.model.hotel.Room;

class RoomOccupancyStorageTest {
    @Test
    public void readAndSaveRoomBook_allInOrder_success() throws Exception {
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        Room[] roomsInArray = new Room[10];
        for (int i = 0; i < 10; i++) {
            Room room = new Room(i + 1);
            rooms.add(room);
            roomsInArray[i] = room;
        }
        RoomBook roomBook = new RoomBook(rooms, roomsInArray, 10);
        Path numberOfRooms = Paths.get("blah");
        Path roomsOccupied = Paths.get("lah");
        RoomOccupancyStorage roomOccupancyStorage = new RoomOccupancyStorage(numberOfRooms, roomsOccupied);
        roomOccupancyStorage.saveNumberOfRooms(roomBook, numberOfRooms);
        roomOccupancyStorage.saveOccupiedRooms(roomBook, roomsOccupied);
        RoomBook roomBook1 = roomOccupancyStorage.readOnlyRoomOccupancy();
        assertEquals(roomBook, roomBook1);

        rooms = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            Room room = new Room(i + 1);
            if (i % 2 == 0) {
                room.setOccupied(true);
            }
            rooms.add(room);
            roomsInArray[i] = room;
        }
        roomBook = new RoomBook(rooms, roomsInArray, 10);
        numberOfRooms = Paths.get("blah");
        roomsOccupied = Paths.get("lah");
        roomOccupancyStorage = new RoomOccupancyStorage(numberOfRooms, roomsOccupied);
        roomOccupancyStorage.saveNumberOfRooms(roomBook, numberOfRooms);
        roomOccupancyStorage.saveOccupiedRooms(roomBook, roomsOccupied);
        roomBook1 = roomOccupancyStorage.readOnlyRoomOccupancy();
        assertEquals(roomBook, roomBook1);
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomBookNumberOfRooms(null, "SomeFile.txt",
                "random.txt"));
        assertThrows(NullPointerException.class, () -> saveRoomBookRoomsOccupied(null, "SomeFile.txt",
                "random.txt"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveRoomBookRoomsOccupied(RoomBook roomBook, String filePath, String roomsOccuppied) {
        try {
            new RoomOccupancyStorage(Paths.get(filePath), Paths.get(roomsOccuppied))
                    .saveOccupiedRooms(roomBook, Paths.get(roomsOccuppied));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    private void saveRoomBookNumberOfRooms(RoomBook roomBook, String filePath, String roomsOccuppied) {
        try {
            new RoomOccupancyStorage(Paths.get(filePath), Paths.get(roomsOccuppied))
                    .saveOccupiedRooms(roomBook, Paths.get(roomsOccuppied));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomBookNumberOfRooms(new RoomBook(),
                null, null));
        assertThrows(NullPointerException.class, () -> saveRoomBookRoomsOccupied(new RoomBook(),
                null, null));
    }
}
