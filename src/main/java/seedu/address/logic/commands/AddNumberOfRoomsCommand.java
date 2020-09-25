package seedu.address.logic.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the number of hotel rooms in a hotel
 */
public class AddNumberOfRoomsCommand extends Command {
    private int numOfRooms;
    private String numOfRoomsInString;
    public static final String COMMAND_WORD = "addnumberofrooms";

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

        }
        return new CommandResult(numOfRooms + " rooms are added in a hotel");
    }
}
