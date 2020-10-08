package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_ROOM_NUMBER;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_ROOM_NUMBER, PREFIX_DUE_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_ROOM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Description description = TaskParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        // TODO: TaskParserUtil can extend from ParserUtil (patient-related methods can be moved to ParserUtil)
        Index roomNumber = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        DateTimeDue dueAt = TaskParserUtil.parseDateTimeDue(argMultimap.getValue(PREFIX_DUE_DATE)); // optional

        Task task = new Task(description, dueAt);

        return new AddTaskCommand(task, roomNumber);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
