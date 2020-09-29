package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import seedu.address.model.RoomList;
import seedu.address.model.hotel.Room;

class RoomOccupancyStorageTest {
    @Test
    public void readAndSaveRoomList_allInOrder_success() throws Exception {
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        Room[] roomsInArray = new Room[10];
        for (int i = 0; i < 10; i++) {
            Room room = new Room(i + 1);
            rooms.add(room);
            roomsInArray[i] = room;
        }
        RoomList roomList = new RoomList(rooms, roomsInArray, 10);
        Path numberOfRooms = Paths.get("numberOfRooms");
        Path roomsOccupied = Paths.get("roomsOccupied");
        RoomOccupancyStorage roomOccupancyStorage = new RoomOccupancyStorage(numberOfRooms, roomsOccupied);
        roomOccupancyStorage.saveNumberOfRooms(roomList, numberOfRooms);
        roomOccupancyStorage.saveOccupiedRooms(roomList, roomsOccupied);
        RoomList roomList1 = roomOccupancyStorage.readOnlyRoomOccupancy();
        assertEquals(roomList, roomList1);

        rooms = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            Room room = new Room(i + 1);
            if (i % 2 == 0) {
                room.setOccupied(true);
            }
            rooms.add(room);
            roomsInArray[i] = room;
        }
        roomList = new RoomList(rooms, roomsInArray, 10);
        numberOfRooms = Paths.get("numberOfRooms");
        roomsOccupied = Paths.get("roomsOccupied");
        roomOccupancyStorage = new RoomOccupancyStorage(numberOfRooms, roomsOccupied);
        roomOccupancyStorage.saveNumberOfRooms(roomList, numberOfRooms);
        roomOccupancyStorage.saveOccupiedRooms(roomList, roomsOccupied);
        roomList1 = roomOccupancyStorage.readOnlyRoomOccupancy();
        assertEquals(roomList, roomList1);
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListNumberOfRooms(null, "SomeFile.txt",
                "random.txt"));
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(null, "SomeFile.txt",
                "random.txt"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveRoomListRoomsOccupied(RoomList roomList, String filePath, String roomsOccuppied) {
        try {
            new RoomOccupancyStorage(Paths.get(filePath), Paths.get(roomsOccuppied))
                    .saveOccupiedRooms(roomList, Paths.get(roomsOccuppied));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    private void saveRoomListNumberOfRooms(RoomList roomList, String filePath, String roomsOccuppied) {
        try {
            new RoomOccupancyStorage(Paths.get(filePath), Paths.get(roomsOccuppied))
                    .saveOccupiedRooms(roomList, Paths.get(roomsOccuppied));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListNumberOfRooms(new RoomList(),
                null, null));
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(new RoomList(),
                null, null));
    }
}
