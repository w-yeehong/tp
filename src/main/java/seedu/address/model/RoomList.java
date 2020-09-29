package seedu.address.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hotel.Room;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Contains information regarding the Room information
 */
public class RoomList {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private int numOfRooms;
    private PriorityQueue<Room> rooms = new PriorityQueue<>();
    private Room[] roomsInArray = new Room[0];


    /** Creates default RoomList() object where all fields are null**/
    public RoomList() {}

    /**
     * Creates a RoomList object using the information given in files containing information about
     * which rooms are occupied and number of rooms
     */
    public RoomList(PriorityQueue<Room> rooms, Room[] roomsInArray, int numOfRooms) {
        this.rooms = rooms;
        this.roomsInArray = roomsInArray;
        this.numOfRooms = numOfRooms;
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

    private void addRooms() {
        roomsInArray = new Room[numOfRooms];
        rooms = new PriorityQueue<>();
        for (int i = 0; i < numOfRooms; i++) {
            Room room = new Room(i + 1);
            rooms.add(room);
            roomsInArray[i] = room;
        }
    }

    /**
     * Adds the number of the rooms in a hotel
     *
     * @param numOfRooms is the number of rooms to be added
     */
    public void addNumberOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
        addRooms();
    }
    public void addRoom(int roomNum) {

    }
    public Room[] getRoomsInArray() {
        return this.roomsInArray;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomList roomList = (RoomList) o;
        if (rooms != null && roomList.rooms != null) {
            PriorityQueue<Room> copy = new PriorityQueue<>(rooms);
            PriorityQueue<Room> copy1 = new PriorityQueue<>(roomList.rooms);
            return numOfRooms == roomList.numOfRooms
                    && equals(copy, copy1)
                    && Arrays.equals(roomsInArray, roomList.roomsInArray);
        } else {
            return numOfRooms == roomList.numOfRooms
                    && Arrays.equals(roomsInArray, roomList.roomsInArray);
        }
    }

    /**
     * Tests whether 2 PriorityQueues are equal by checking whether at each relative position they contain the equal
     * rooms
     */
    public boolean equals(PriorityQueue<Room> rooms1, PriorityQueue<Room> rooms2) {
        if (rooms1.size() != rooms2.size()) {
            return false;
        } else {
            int size = rooms1.size();
            for (int i = 0; i < size; i++) {
                if (!rooms1.poll().equals(rooms2.poll())) {
                    return false;
                }
            }
            return true;
        }
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(numOfRooms, rooms);
        result = 31 * result + Arrays.hashCode(roomsInArray);
        return result;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public void setRooms(PriorityQueue<Room> rooms) {
        this.rooms = rooms;
    }

    public void setRoomsInArray(Room[] roomsInArray) {
        this.roomsInArray = roomsInArray;
    }

}
