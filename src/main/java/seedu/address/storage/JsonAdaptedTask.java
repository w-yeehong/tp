package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class JsonAdaptedTask {
    private String description;
    private String dueAt;

    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                           @JsonProperty("dueAt") String dueAt) throws IllegalValueException {
        this.description = description;
        this.dueAt = dueAt;
    }

    public JsonAdaptedTask(Task source) {
        this.description = source.getDescription().value;
        this.dueAt = source.getDueAt().value.toString();
    }

    public Task toModelType() throws IllegalValueException {
        return new Task(new Description(description), new DateTimeDue(dueAt));
    }
}
