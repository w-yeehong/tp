package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_TASK_NUMBER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

/**
 * Deletes a {@code Task} from a {@code Room}.
 * The {@code Room} is identified by its index in the {@code RoomList}
 * of {@code Model}.
 * The {@code Task} is identified by its index in the {@code TaskList}
 * of {@code Room}.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from Covigent. "
            + "\nParameters: "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER "
            + PREFIX_TASK_NUMBER + "TASK NUMBER "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "1 "
            + PREFIX_TASK_NUMBER + "3";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task %1$d deleted from Room %2$d. \nDescription: %3$s";

    private final Index roomIndex;
    private final Index taskIndex;

    /**
     * Creates a DeleteTaskCommand to remove the {@code Task} with {@code taskIndex} from
     * the {@code Room} with the {@code roomIndex}.
     */
    public DeleteTaskCommand(Index roomIndex, Index taskIndex) {
        requireAllNonNull(roomIndex, taskIndex);
        this.taskIndex = taskIndex;
        this.roomIndex = roomIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Get the room from which the user wants to delete the task
        List<Room> rooms = model.getRoomList();
        if (roomIndex.getZeroBased() >= rooms.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_INDEX);
        }
        Room targetRoom = rooms.get(roomIndex.getZeroBased());
        assert targetRoom != null : "Target room should never be null.";

        // Get the task which the user wants to delete
        List<Task> tasks = targetRoom.getTaskList().asUnmodifiableObservableList();
        if (taskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
        Task taskToDelete = tasks.get(taskIndex.getZeroBased());
        assert taskToDelete != null : "The task to delete should never be null.";

        model.deleteTaskFromRoom(taskToDelete, targetRoom);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS,
                taskIndex.getOneBased(), roomIndex.getOneBased(), taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && roomIndex.equals(((DeleteTaskCommand) other).roomIndex)
                && taskIndex.equals(((DeleteTaskCommand) other).taskIndex));
    }
}
