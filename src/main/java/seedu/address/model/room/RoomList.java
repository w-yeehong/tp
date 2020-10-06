package seedu.address.model.room;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Contains information regarding the Room information
 */
public class RoomList implements ReadOnlyRoomList {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private int numOfRooms;
    private PriorityQueue<Room> rooms = new PriorityQueue<>();
    private ObservableList<Room> roomObservableList = FXCollections.observableArrayList();

    /** Creates default RoomList() object where all fields are null**/
    public RoomList() {}

    /**
     * Converts data from readOnlyRoomList to roomList
     */
    public RoomList(ReadOnlyRoomList readOnlyRoomList) {
        this();
        resetData(readOnlyRoomList);
    }
    /**
     * Creates a RoomList object using the information given in files containing information about
     * which rooms are occupied and number of rooms
     */
    public RoomList(PriorityQueue<Room> rooms, int numOfRooms) {
        this.rooms = rooms;
        this.numOfRooms = numOfRooms;
        convertPriorityQueue(rooms);
    }

    private void resetData(ReadOnlyRoomList readOnlyRoomList) {
        ObservableList<Room> roomLists = readOnlyRoomList.getRoomObservableList();
        numOfRooms = roomLists.size();
        rooms.addAll(roomLists);
        roomObservableList.addAll(roomLists);
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

    public ObservableList<Room> getRoomObservableList() {
        return roomObservableList;
    }

    private void addRooms() {
        if (numOfRooms < 0) {
            return;
        }
        if (numOfRooms > roomObservableList.size()) {
            for (int i = roomObservableList.size(); i < numOfRooms; i++) {
                Room room = new Room(i + 1);
                roomObservableList.add(i, room);
                rooms.add(room);
            }
        } else if (numOfRooms < roomObservableList.size()) {
            for (int i = numOfRooms; i < roomObservableList.size(); i++) {
                Room room = roomObservableList.get(i);
                rooms.remove(room);
            }
            int size = roomObservableList.size();
            for (int i = numOfRooms; i < size; i++) {
                roomObservableList.remove(numOfRooms);
            }
        } else {

        }
    }

    /**
     * Adds the number of the rooms in a hotel
     *
     * @param numOfRooms is the number of rooms to be added
     */
    public void addRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
        addRooms();
    }

    /**
     * Adds this room to the RoomList
     * @param room is added to RoomList
     */
    public void addRooms(Room room) {
        rooms.add(room);
        roomObservableList.add(room);
    }
    /**
     * Sets the elements of {@code roomObservableList}.
     *
     * @param roomList PriorityQueue containing all the rooms.
     */
    private void convertPriorityQueue(PriorityQueue<Room> roomList) {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        Object[] arr = roomList.toArray();
        for (int k = 0; k < arr.length; k++) {
            roomArrayList.add((Room) arr[k]);
        }
        roomObservableList.setAll(roomArrayList);
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
                    && equals(copy, copy1);
        } else {
            return numOfRooms == roomList.numOfRooms;
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
        int result = Objects.hash(numOfRooms, rooms, roomObservableList);
        result = 31 * result;
        return result;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public void setRooms(PriorityQueue<Room> rooms) {
        this.rooms = rooms;
    }

}
