package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

import java.util.ArrayList;

//@@author chiamyunqing
/**
 * The RoomTaskRecords class collects and updates all the Tasks from the rooms.
 * It exists mainly for the purpose of Task tab UI.
 */
public class RoomTaskRecords implements ReadOnlyList<Task> {

    private final ObservableList<Task> internalList;
    private final ObservableList<Task> internalUnmodifiableList;

    public RoomTaskRecords(ObservableList<Task> taskList, ObservableList<Room> roomList) {
        updateTaskListIfChanged(roomList); //attaches a listener to roomList
        internalList = taskList;
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Whenever there's a change in room list, we will force update of tasklist
     * to reload the tasks in that room.
     */
    public void updateTaskListIfChanged(ObservableList<Room> roomList) {
        roomList.addListener(new ListChangeListener<Room>() {
            @Override
            public void onChanged(Change<? extends Room> change) {
                while (change.next()) {
                    if (change.wasUpdated()) {
                        int indexToChange = change.getFrom();
                        Room changedRoom = change.getList().get(indexToChange);
                        int roomNumber = changedRoom.getRoomNumber();
                        ArrayList<Task> updatedTaskList = new ArrayList<>();
                        for (Task task : internalList) {
                            if (task.getTaskRoomNumber() != roomNumber) {
                                updatedTaskList.add(task);
                            }
                        }
                        updatedTaskList.addAll(changedRoom.getReadOnlyTasks());
                        internalList.setAll(updatedTaskList);
                    }
                }
            }
        });
    }

    public ObservableList<Task> getReadOnlyList() {
        return internalUnmodifiableList;
    }
}
