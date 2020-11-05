package seedu.address.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

//@@author chiamyunqing
/**
 * The RoomTaskRecords class collects and updates all the Tasks from the rooms.
 * It exists mainly for the purpose of Task tab UI.
 */
public class RoomTaskRecords implements ReadOnlyList<Task> {

  //  private final ObservableList<Room> roomList;
    private final ObservableList<Task> internalList;
    private final ObservableList<Task> internalUnmodifiableList;

    /**
     * Constructs a listener to roomList so that task list is updated whenever
     * there's a change in roomList
     * @param taskList stores all the tasks in the rooms.
     * @param roomList stores all the rooms.
     */
    public RoomTaskRecords(ObservableList<Task> taskList, ObservableList<Room> roomList) {
    //    this.roomList = roomList;
        updateTaskListIfChanged(roomList);
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
                    if (change.wasRemoved()) {
                        //caters to initroom
                        ArrayList<Task> updatedTaskList = new ArrayList<>();
                        for (Room room : roomList) {
                            updatedTaskList.addAll(room.getReadOnlyTasks());
                        }
                        internalList.setAll(updatedTaskList);
                    } else if (change.wasUpdated()) {
                        //when there's a change, the changes in tasks room is added last
                        //tasks are not sorted according to room num (we can do that too)
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
