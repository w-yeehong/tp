package seedu.address.testutil;

import seedu.address.model.task.DateTimeCreated;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Remind John to clean bedsheets.";
    public static final String DEFAULT_DATETIME_DUE = "20201014 2359";

    private Description description;
    private DateTimeDue dueAt;
    private DateTimeCreated createdAt;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        dueAt = new DateTimeDue(DEFAULT_DATETIME_DUE);
        createdAt = new DateTimeCreated();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        dueAt = taskToCopy.getDueAt();
        createdAt = taskToCopy.getCreatedAt();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code DateTimeDue} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateTimeDue(String dueAt) {
        this.dueAt = new DateTimeDue(dueAt);
        return this;
    }

    public Task build() {
        return new Task(description, dueAt, createdAt);
    }
}
