package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;


/**
 * The RoomTaskRecords class collects and updates all the Tasks from the rooms.
 * It exists mainly for the purpose of Task tab UI.
 */
public class RoomTaskRecords implements ReadOnlyList<Task> {

    private final ObservableList<Task> internalList;
    private final ObservableList<Task> internalUnmodifiableList;

    public RoomTaskRecords(ObservableList<Task> taskList, ObservableList<Room> roomList) {
        updateTaskListIfChanged(roomList); //attaches a listener to roomList which will
        internalList = taskList;
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Whenever there's a change in room list, we will force update of tasklist
     * to reload the tasks in that room.
     */
    private void updateTaskListIfChanged(ObservableList<Room> roomList) {
        roomList.addListener(new ListChangeListener<Room>() {
            @Override
            public void onChanged(Change<? extends Room> change) {
                System.out.println("got change!");
                while (change.next()) {
                    if (change.wasAdded()) {
                        int indexToChange = change.getFrom();
                        Room changedRoom = change.getList().get(indexToChange);
                        int roomNumber = changedRoom.getRoomNumber();
                        System.out.println("before " + internalList.size());
                        //remove tasks in that room
                        for (Task task : internalList) {
                            if (task.getTaskRoomNumber() == roomNumber) {
                                internalList.remove(task);
                            }
                        }
                        //reload tasks into the room
                        ObservableList<Task> taskListInRoom = changedRoom.getReadOnlyTasks();
                        internalList.addAll(taskListInRoom);
                        System.out.println("after " + internalList.size());
                    }
                }
            }
        });
    }

    /*public void deleteTask(Task task) {
        internalList.remove(task);
    }

    public void addTask(Task task) {
        internalList.add(task);
    }

    public void setTask(Task target, Task editedTask) {
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        internalList.set(index, editedTask);
    }

     */

    public ObservableList<Task> getReadOnlyList() {
        return internalUnmodifiableList;
    }
}
