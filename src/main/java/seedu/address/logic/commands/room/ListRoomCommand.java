package seedu.address.logic.commands.room;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ListRoomCommand extends Command {
    public static final String COMMAND_WORD = "listroom";
    public static final String MESSAGE_SUCCESS = "Here are the list of rooms";
    public static final String NUMBER_OF_ROOMS_UNDEFINED = "Please define number of rooms";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getRooms().size() == 0) {
            throw new CommandException(NUMBER_OF_ROOMS_UNDEFINED);
        }
        model.displayAllRoom();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
