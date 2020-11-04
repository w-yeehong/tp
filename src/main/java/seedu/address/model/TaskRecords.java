package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskNotFoundException;


/**
 * The TaskRecords class collects and updates all the Tasks from the rooms.
 * It exists mainly for the purpose of Task tab UI.
 */
public class TaskRecords implements ReadOnlyList<Task> {

    private final ObservableList<Task> internalList;
    private final ObservableList<Task> internalUnmodifiableList;

    public TaskRecords(ObservableList<Task> taskList) {
        internalList = taskList;
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    public void deleteTask(Task task) {
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

    public ObservableList<Task> getReadOnlyList() {
        return internalUnmodifiableList;
    }
}
