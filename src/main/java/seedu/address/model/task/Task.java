package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task that can be assigned to a room.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Data fields (i.e. values entered by user).
    private final Description description;
    private final DateTimeDue dueAt;

    // Metadata (i.e. values generated upon creation).
    private final DateTimeCreated createdAt;

    /**
     * Every field apart from {@code DateTimeCreated} must be present and not null.
     *
     * Creation time of the {@code Task} is set to current system time.
     */
    public Task(Description description, DateTimeDue dueAt) {
        requireAllNonNull(description, dueAt);
        this.description = description;
        this.dueAt = dueAt;
        this.createdAt = new DateTimeCreated();
    }

    /**
     * Every field apart must be present and not null.
     */
    public Task(Description description, DateTimeDue dueAt, DateTimeCreated createdAt) {
        requireAllNonNull(description, dueAt);
        this.description = description;
        this.dueAt = dueAt;
        this.createdAt = createdAt;
    }

    public Description getDescription() {
        return description;
    }

    public DateTimeDue getDueAt() {
        return dueAt;
    }

    public DateTimeCreated getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns true if both tasks have the same data and metadata fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getDueAt().equals(getDueAt())
                && otherTask.getCreatedAt().equals(getCreatedAt());
    }

    @Override
    public int hashCode() {
        // Use this method for custom fields hashing instead of implementing your own.
        return Objects.hash(description, dueAt, createdAt);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Description: ")
                .append(getDescription())
                .append(" Due Date: ")
                .append(getDueAt())
                .append(" Date Created: ")
                .append(getCreatedAt());
        return builder.toString();
    }

}
