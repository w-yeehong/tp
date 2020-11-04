package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 * Task room number is not allowed to change since task is tied to room.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Remind John to clean bedsheets.";
    public static final String DEFAULT_DATETIME_DUE = "20201014 2359";
    public static final int DEFAULT_TASK_ROOM_NUM = 3;

    private Description description;
    private DateTimeDue dueAt;
    private int taskRoomNumber;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        dueAt = new DateTimeDue(DEFAULT_DATETIME_DUE);
        taskRoomNumber = DEFAULT_TASK_ROOM_NUM;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        dueAt = taskToCopy.getDueAt();
        taskRoomNumber = taskToCopy.getTaskRoomNumber();
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
    public TaskBuilder withDateTimeDue(Optional<String> optionalDueAt) {
        this.dueAt = new DateTimeDue(optionalDueAt);
        return this;
    }

    public Task build() {
        return new Task(description, dueAt, taskRoomNumber);
    }
}
