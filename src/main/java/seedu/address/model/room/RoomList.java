package seedu.address.model.room;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;
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
        this.numOfRooms++;
        rooms.add(room);
        roomObservableList.add(room);
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
        Room[] roomsForPQ = this.rooms.toArray(new Room[0]);
        Room[] rooms1ForPQ = roomList.rooms.toArray(new Room[0]);

        Room[] roomsForObservableList = roomObservableList.toArray(new Room[0]);
        Room[] rooms1FOrObservableList = roomList.roomObservableList.toArray(new Room[0]);
        return numOfRooms == roomList.numOfRooms
                && Arrays.equals(roomsForPQ, rooms1ForPQ)
                && Arrays.equals(roomsForObservableList, rooms1FOrObservableList);
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
    /**
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean containsRoom(Room toCheck) {
        requireNonNull(toCheck);
        return roomObservableList.stream().anyMatch(toCheck::isSameRoom);
    }

    /**
     * Replaces the room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the list.
     *
     * @param target Room to be changed.
     * @param editedRoom Room that has been changed.
     */
    public void setSingleRoom(Room target, Room editedRoom) {
        int index = roomObservableList.indexOf(target);
        if (index == -1) {
            throw new RoomNotFoundException();
        }

        if (!target.isSameRoom(editedRoom) && containsRoom(editedRoom)) {
            throw new DuplicateRoomException();
        }
        rooms.remove(target); // this and the next LOC is to replace the room in the priority queue
        rooms.add(editedRoom);
        roomObservableList.set(index, editedRoom);
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

    /**
     * Checks if the given room number is present in the application.
     * @param roomNumber to check if it is in the application.
     * @return Index Of room that is found.
     */
    public Index checkIfRoomPresent(Integer roomNumber) {
        Index index = Index.fromZeroBased(0);
        for (int i = 1; i <= roomObservableList.size(); i++) {
            int roomNum = roomObservableList.get(i - 1).getRoomNumber();
            boolean isValidRoom = (Integer.valueOf(roomNum)).equals(roomNumber);
            if (isValidRoom) {
                index = Index.fromZeroBased(i);
                break;
            }
        }
        return index;
    }
}
