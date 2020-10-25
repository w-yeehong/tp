package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Reads data from storage data files and imports them into TaskList
 */
public class JsonTaskStorage {
    private Path task;

    /**
     * Creates the TaskOccupancy object
     */
    public JsonTaskStorage() {}

    /**
     * Creates the TaskOccupancy object
     */
    public JsonTaskStorage(Path task) {
        this.task = task;
    }

    public Path getTask() {
        return task;
    }

    public Optional<ReadOnlyTaskList> readOnlyTask() throws IOException, DataConversionException {
        return readOnlyTask(task);
    }
    /**
     * Returns TaskList data as a {@link ReadOnlyTaskList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    public Optional<ReadOnlyTaskList> readOnlyTask(Path filePath) throws IOException, DataConversionException {
        Optional<JsonSerializableTaskList> jsonCovigentApp = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);
        if (!jsonCovigentApp.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonCovigentApp.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataConversionException(ive);
        }
    }

    /**
     * Save the task to the tasklist.
     * @param taskList
     * @throws IOException
     */
    public void saveTask(TaskList taskList) throws IOException {
        saveTasks(taskList, task);
    }

    /**
     * Save the task to the tasklist with the path.
     * @param taskList
     * @param fileTask
     * @throws IOException
     */
    public void saveTasks(TaskList taskList, Path fileTask) throws IOException {
        FileUtil.createIfMissing(fileTask);
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(taskList.getTaskObservableList());
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(taskList), fileTask);
    }
}
