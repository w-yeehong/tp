package seedu.address.logic.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the number of hotel rooms in a hotel
 */
public class AddNumberOfRoomsCommand extends Command {
    public static final String COMMAND_WORD = "addnumberofrooms";
    private int numOfRooms;
    private String numOfRoomsInString;

    /**
     * Creates an AddCommand to add the number of rooms available in a hotel
     */
    public AddNumberOfRoomsCommand(int numOfRooms) {
        this.numOfRooms = numOfRooms;
        this.numOfRoomsInString = "" + numOfRooms;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Path path = model.getPathOfNumberOfRooms();
        File file = path.toFile();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(numOfRoomsInString);
            fileWriter.close();
        } catch (IOException e) {
            //Would not happen because the path to numberOfRooms.txt is always present
        }
        return new CommandResult(numOfRooms + " rooms are added in a hotel");
    }
}
