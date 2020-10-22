package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.DueDatePredicate;
import seedu.address.model.task.Task;

/**
 * Searches a {@code Task} before a {@code DateTimeDue}.
 */
public class SearchTaskCommand extends Command {

    public static final String COMMAND_WORD = "searchtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches tasks in Covigent. "
            + "\nParameters: "
            + "[" + PREFIX_DUE_DATE + "DUE DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DUE_DATE + "20200928 2359";

    public static final String MESSAGE_SEARCH_TASK_SUCCESS = "Tasks before the due date found: \n";
    public static final String MESSAGE_TASK_NOT_FOUND = "There is no task that matches your criteria.";


    private final DateTimeDue duedate;
    private ObservableList<Task> tasks;
    private DueDatePredicate datePredicate;

    /**
     * Creates an SearchTaskCommand to Search the specified {@code Task} with {@code DateTimeDue}
     */
    public SearchTaskCommand(DateTimeDue duedate) {
        requireAllNonNull(duedate);
        this.duedate = duedate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Room> rooms = model.getRoomList();
        ArrayList<Task> taskListWithDesirableResult = new ArrayList<>();
        for (Room room : rooms) {
            tasks = room.getTaskList().asUnmodifiableObservableList();
            for (Task task : tasks) {
                if (task.getDueAt().compareTo(duedate) == 1) {
                    taskListWithDesirableResult.add(task);
                }
            }
        }

        if (taskListWithDesirableResult.size() < 1) {
            throw new CommandException(MESSAGE_TASK_NOT_FOUND);
        }
        assert taskListWithDesirableResult.size() >= 1;
        datePredicate = new DueDatePredicate(duedate);
        model.updateFilteredTaskList(datePredicate);
        return new CommandResult(String.format(MESSAGE_SEARCH_TASK_SUCCESS
                + getListOutput(taskListWithDesirableResult)));
    }

    /**
     * Returns the list of tasks' details
     * @param list a list that stores the tasks.
     * @return a String output of the tasks' details.
     */
    private String getListOutput(ArrayList<Task> list) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            outputString.append(String.format("%d. Description: %s\n", i + 1, list.get(i)));
        }
        return outputString.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SearchTaskCommand
                && duedate.equals(((SearchTaskCommand) other).duedate));
    }
}
