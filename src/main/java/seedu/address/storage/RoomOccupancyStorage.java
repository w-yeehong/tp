package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.Scanner;

import seedu.address.model.RoomBook;
import seedu.address.model.hotel.Room;

/**
 * Reads data from storage data files and imports them into RoomBook
 */
public class RoomOccupancyStorage {
    private Path fileNumOfRooms;
    private Path roomsOccupied;

    public RoomOccupancyStorage() {}
    /**
     * Creates RoomOccupancyStorage object that reads the number of rooms a hotel has and the rooms which are
     * occupied
     */
    public RoomOccupancyStorage(Path fileNumOfRooms, Path roomsOccupied) {
        this.fileNumOfRooms = fileNumOfRooms;
        this.roomsOccupied = roomsOccupied;
    }

    /**
     * Reads the 2 files responsible for number rooms and which rooms are occupied
     *
     * @return RoomBook containing information of which rooms are occupied and number of rooms present
     * @throws IOException
     */
    public RoomBook readOnlyRoomOccupancy() throws IOException {
        Scanner scanner = new Scanner(fileNumOfRooms);
        Scanner roomsOccupiedFile = new Scanner(roomsOccupied);
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        Room[] roomsInArray;
        int numOfRooms;
        if (scanner.hasNextLine()) {
            numOfRooms = scanner.nextInt(); //number of rooms in hotel
            roomsInArray = new Room[numOfRooms];
            for (int i = 0; i < numOfRooms; i++) {
                Room room = new Room(i + 1);
                rooms.add(room);
                roomsInArray[i] = room;
            }
            while (roomsOccupiedFile.hasNextLine()) {
                int roomNumber = roomsOccupiedFile.nextInt(); //room number that is currently occupied
                Room room = roomsInArray[roomNumber - 1];
                rooms.remove(room);
                room.setOccupied(true);
                roomsInArray[roomNumber - 1] = room;
                rooms.add(room);
            }
        } else {
            roomsInArray = null;
            numOfRooms = 0;
        }
        return new RoomBook(rooms, roomsInArray, numOfRooms, fileNumOfRooms, roomsOccupied);
    }
}
