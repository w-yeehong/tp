package seedu.address.logic.commands;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the number of hotel rooms in a hotel
 */
public class AddNumberOfRoomsCommand extends Command {
    public static final String COMMAND_WORD = "addnumberofrooms";
    private int numOfRooms;

    /**
     * Creates an AddCommand to add the number of rooms available in a hotel
     */
    public AddNumberOfRoomsCommand(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addNumberOfRooms(numOfRooms);
        return new CommandResult(numOfRooms + " rooms are added in a hotel");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddNumberOfRoomsCommand that = (AddNumberOfRoomsCommand) o;
        return numOfRooms == that.numOfRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfRooms);
    }
}
