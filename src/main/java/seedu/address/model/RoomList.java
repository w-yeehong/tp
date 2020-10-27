package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.patient.Name;
import seedu.address.model.room.Room;
import seedu.address.model.room.exceptions.RoomNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.storage.JsonPatientRecordsStorage;

/**
 * Contains information regarding the Room information
 */
public class RoomList implements ReadOnlyRoomList {
    private static final Logger logger = LogsCenter.getLogger(JsonPatientRecordsStorage.class);

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
     * Converts data from readOnlyRoomList to roomList
     */
    public RoomList(ReadOnlyRoomList readOnlyRoomList) {
        this();
        resetData(readOnlyRoomList);
    }

    public boolean canFit() {
        return rooms.canFit();
    }

    public int numOfOccupiedRooms() {
        return rooms.numOfOccupiedRooms();
    }
    /**
     * Resets the existing data of this {@code RoomList} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomList readOnlyRoomList) {
        rooms.resetData(readOnlyRoomList);
    }
    /**
     * Returns Priority Queue of rooms
     */
    public PriorityQueue<Room> getRooms() {
        return rooms.getRooms();
    }

    /**
     * Returns number of rooms in hotel
     */
    public int getNumOfRooms() {
        return rooms.getNumOfRooms();
    }

    public ObservableList<Room> getRoomObservableList() {
        return rooms.getRoomObservableList();
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

    /**
     * Returns the room with the provided {@code roomNumber}.
     * An empty optional is returned if such a room is not found in the {@code RoomList}.
     *
     * @param roomNumber The room number of the room.
     * @return the optional-wrapped room if found, otherwise an empty optional
     */
    public Optional<Room> getRoomWithRoomNumber(int roomNumber) {
        return rooms.getRoomWithRoomNumber(roomNumber);
    }

    // TODO: Move task-related methods into another class

    /**
     * Returns the task with the provided {@code taskIndex} from {@code room}.
     * An empty optional is returned if such a task is not found in the room.
     *
     * @param taskIndex The index of the task in the room.
     * @return the optional-wrapped task if found, otherwise an empty optional
     */
    public Optional<Task> getTaskFromRoomWithTaskIndex(Index taskIndex, Room room) {
        List<Task> tasks = room.getTaskList().asUnmodifiableObservableList();
        if (taskIndex.getZeroBased() >= tasks.size()) {
            return Optional.empty();
        }
        return Optional.of(tasks.get(taskIndex.getZeroBased()));
    }

    /**
     * Adds a task to a room.
     * The room must exist in the {@code RoomList}.
     *
     * @param task The task to add.
     * @param room The room to which the task should be added.
     * @throws RoomNotFoundException if {@code room} is not in {@code RoomList}.
     */
    public void addTaskToRoom(Task task, Room room) {
        requireAllNonNull(task, room);

        rooms.addTaskToRoom(task, room);
    }

    /**
     * Deletes a task from a room.
     * The room must exist in the {@code RoomList}.
     * The task must exist in the {@code TaskList} of the room.
     *
     * @param task The task to delete.
     * @param room The room to which the task should be deleted.
     * @throws RoomNotFoundException if {@code room} is not in {@code RoomList}.
     * @throws TaskNotFoundException if {@code task} is not in {@code room}.
     */
    public void deleteTaskFromRoom(Task task, Room room) {
        requireAllNonNull(task, room);

        rooms.deleteTaskFromRoom(task, room);
    }

    /**
     * Replaces a task {code target} in a room with {@code editedTask}.
     * The room must exist in the {@code RoomList}.
     * The {@code target} must exist in the {@code TaskList} of the room.
     *
     * @param target The task to be replaced.
     * @param editedTask The edited task to replace the target.
     * @param room The room in which the task should be replaced.
     * @throws RoomNotFoundException if {@code room} is not in {@code RoomList}.
     * @throws TaskNotFoundException if {@code target} is not in {@code room}.
     */
    public void setTaskToRoom(Task target, Task editedTask, Room room) {
        requireAllNonNull(target, editedTask, room);

        rooms.setTaskToRoom(target, editedTask, room);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Room> asUnmodifiableObservableList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomList // instanceof handles nulls
                && rooms.equals(((RoomList) other).rooms));
    }

    /**
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean containsRoom(Room toCheck) {
        requireNonNull(toCheck);
        return rooms.containsRoom(toCheck);
    }

    //@@author chiamyunqing
    /**
     * Removes the patient with the given name {@code patientName} from the room.
     */
    public void removePatientFromRoom(Name patientName) {
        requireNonNull(patientName);

        rooms.clearRoom(patientName);
    }
    //@@author chiamyunqing

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

    @Override
    public int hashCode() {
        return rooms.hashCode();
    }

    public void setNumOfRooms(int numOfRooms) {
        rooms.setNumOfRooms(numOfRooms);
    }

    public void setRooms(PriorityQueue<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

}
