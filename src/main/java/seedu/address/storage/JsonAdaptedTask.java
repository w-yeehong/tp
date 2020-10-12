package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    private String description;
    private String dueAt;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given Task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                           @JsonProperty("dueAt") String dueAt) throws IllegalValueException {
        this.description = description;
        this.dueAt = dueAt;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        this.description = source.getDescription().value;
        this.dueAt = source.getDueAt().getVal();
    }

    /**
     * Converts this Jackson-friendly adapted Task object into the model's {@code Room} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Task.
     */
    public Task toModelType() throws IllegalValueException {
        System.out.println(dueAt);
        return new Task(new Description(description), new DateTimeDue(dueAt));
    }
}
