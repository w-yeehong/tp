package seedu.address.model;

import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.logging.Logger;
import seedu.address.commons.core.LogsCenter;

import seedu.address.model.hotel.Room;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Contains information regarding the Room information
 */
public class RoomBook {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private int numOfRooms;
    private PriorityQueue<Room> rooms = new PriorityQueue<>();
    private Room[] roomsInArray;
    private Path fileNumOfRooms;
    private Path roomsOccupied;

    public RoomBook() {}

    public RoomBook(PriorityQueue<Room> rooms, Room[] roomsInArray, int numOfRooms, Path fileNumOfRooms,
                    Path roomsOccupied) {
        this.rooms = rooms;
        this.roomsInArray = roomsInArray;
        this.numOfRooms = numOfRooms;
        this.fileNumOfRooms = fileNumOfRooms;
        this.roomsOccupied = roomsOccupied;
    }

    /**
     * Returns Priority Queue of rooms
     */
    public PriorityQueue<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Returns number of rooms in hotel
     */
    public int getNumOfRooms() {
        return numOfRooms;
    }

    /**
     * Returns Path of file containing number of rooms
     */
    public Path getFileNumOfRooms() {
        return fileNumOfRooms;
    }

    /**
     * Returns Path of file containing information on which rooms are occupied
     */
    public Path getRoomsOccupied() {
        return roomsOccupied;
    }
}
