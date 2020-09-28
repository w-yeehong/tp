package seedu.address.logic.commands;

import java.util.PriorityQueue;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Room;

/**
 * Finds the next empty room with the lowest room number
 */
public class FindRoomCommand extends Command {
    public static final String COMMAND_WORD = "findroom";
    public static final String MESSAGE_NO_EMPTY_ROOM = "There are unfortunately no more empty rooms!";
    public static final String NUMBER_OF_ROOMS_UNDEFINED = "Please define number of rooms";

    public FindRoomCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        PriorityQueue<Room> rooms = model.getRooms();
        if (model.getNumOfRooms() == 0) {
            throw new CommandException(NUMBER_OF_ROOMS_UNDEFINED);
        }
        Room room = rooms.peek();
        assert room != null;
        if (room.isOccupied()) {
            throw new CommandException(MESSAGE_NO_EMPTY_ROOM);
        }
        return new CommandResult(room.toString() + " is empty");
    }
}
