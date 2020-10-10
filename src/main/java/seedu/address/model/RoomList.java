package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.PriorityQueue;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;
import seedu.address.model.task.Task;

/**
 * Wraps all data at the app level
 * Duplicate patients are not allowed (by .isSamePatient comparison)
 */
public class RoomList implements ReadOnlyRoomList {
    private final UniqueRoomList rooms;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        rooms = new UniqueRoomList();
    }

    public RoomList() {}

    /**
     * Creates a room record using the rooms in the {@code toBeCopied}
     */
    public RoomList(ReadOnlyRoomList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public int getNumOfRooms() {
        return rooms.getNumOfRooms();
    }

    public void addTaskToRoom(Task task, Room room) {
        rooms.addTaskToRoom(task, room);
    }

    /**
     * Replaces the contents of the room list with {@code rooms}.
     */
    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    public void setNumOfRooms(int numOfRooms) {
        rooms.setNumOfRooms(numOfRooms);
    }

    /// list overwrite operations
    /**
     * Resets the existing data of this {@code RoomList} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomList newData) {
        requireNonNull(newData);
        setRooms(newData.getRoomList());
    }


    public PriorityQueue<Room> getRooms() {
        return rooms.getRooms();
    }

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    /**
     * Returns true if the list contains an equivalent room as the given argument.
     * @param toCheck
     * @return if app contains room or not
     */
    public boolean containsRoom(Room toCheck) {
        requireNonNull(toCheck);
        return rooms.containsRoom(toCheck);
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
        rooms.setSingleRoom(target, editedRoom);
    }

    /**
     * Checks if the given room number is present in the application.
     * @param roomNumber to check if it is in the application.
     * @return Index Of room that is found.
     */
    public Index checkIfRoomPresent(Integer roomNumber) {
        return rooms.checkIfRoomPresent(roomNumber);
    }

    /**
     * Adds the number of the rooms in a hotel
     *
     * @param numOfRooms is the number of rooms to be added
     */
    public void addRooms(int numOfRooms) {
        rooms.addRooms(numOfRooms);
    }

    /**
     * Adds this room to the RoomList
     * @param room is added to RoomList
     */
    public void addRooms(Room room) {
        rooms.addRooms(room);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomList // instanceof handles nulls
                && rooms.equals(((RoomList) other).rooms));
    }

    @Override
    public int hashCode() {
        return rooms.hashCode();
    }
}

