package seedu.address.model.room;

import seedu.address.model.task.Task;

/**
 * An association class between {@code Room} and {@code Task}. This class serves 2 primary purposes:
 * i) stores critical information related to {@code Room} (e.g. room number) to be displayed on the
 * Task user interface without creating a direct association between {@code Room} and {@code Task}.
 * ii) provides a convenient way to retrieve the index of a task in room in constant time.
 */
public class RoomTaskAssociation {

    private final Room room;
    private final Task task;
    private final int taskIndex;

    /**
     * Creates an association between {@code room} and {@code task}.
     *
     * @param room The room in which the task is found.
     * @param task The task.
     * @param taskIndex The index of the task in the room.
     */
    public RoomTaskAssociation(Room room, Task task, int taskIndex) {
        this.room = room;
        this.task = task;
        this.taskIndex = taskIndex;
    }

    public Task getTask() {
        return task;
    }

    public int getRoomNumber() {
        return room.getRoomNumber();
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Returns the total number of tasks in the room.
     */
    public int getTotalTasksInRoom() {
        return room.getReadOnlyTasks().size();
    }
}
