package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

public class JsonSerializableTaskList {

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    @JsonCreator
    public JsonSerializableTaskList(@JsonProperty("internalList") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    public JsonSerializableTaskList(TaskList source) {
        tasks.addAll(source.getInternalList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    public TaskList toModelType() throws IllegalValueException {
        TaskList taskList = new TaskList();
        taskList.getInternalList().clear();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            taskList.add(task);
        }
        return taskList;
    }
}
