package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROOM_NOT_FOUND;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Searches a room according to the given room number.
 */
public class SearchRoomCommand extends Command {

    public static final String COMMAND_WORD = "searchroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the room with the given room number.\n"
            + "Parameters: ROOM NUMBER\n"
            + "Example: " + COMMAND_WORD + " 130";

    public static final String MESSAGE_SUCCESS = "Room %1$s has been found and listed.";

    private final Integer roomNumber;

    /**
     * Creates a SearchRoomCommand to look for the specified room.
     * @param roomNumber the room to find.
     */
    public SearchRoomCommand(Integer roomNumber) {
        requireNonNull(roomNumber);
        this.roomNumber = roomNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Index index = model.checkIfRoomPresent(roomNumber);
        if (index.getZeroBased() == 0) {
            throw new CommandException(MESSAGE_INVALID_ROOM_NOT_FOUND);
        }

        model.updateFilteredRoomList(room -> room.getRoomNumber() == roomNumber);
        return new CommandResult(String.format(MESSAGE_SUCCESS, roomNumber.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchRoomCommand // instanceof handles nulls
                && roomNumber.equals(((SearchRoomCommand) other).roomNumber));
    }
}
