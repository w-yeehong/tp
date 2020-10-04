package seedu.address.logic.parser.room;

import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_OCCUPIED;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_PATIENT;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_TASK;

/**
 * Parses input arguments and creates a new EditRoomCommand object.
 */
public class EditRoomParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPatientCommand
     * and returns an EditPatientCommand object for execution.
     *
     * @param args that are inputted by the user.
     * @return EditPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditRoomParser parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOM_NUMBER, PREFIX_OCCUPIED, PREFIX_ROOM_PATIENT,
                        PREFIX_ROOM_TASK);

        String roomToBeEdited = argMultimap.getPreamble().trim().toLowerCase();
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE));
        }
        EditPatientCommand.EditPatientDescriptor editPatientDescriptor = new EditPatientCommand.EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_TEMP).isPresent()) {
            editPatientDescriptor.setTemperature(ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMP).get()));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPatientDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERIOD_OF_STAY).isPresent()) {
            editPatientDescriptor.setPeriodOfStay(ParserUtil
                    .parsePeriodOfStay(argMultimap.getValue(PREFIX_PERIOD_OF_STAY).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMENTS).isPresent()) {
            editPatientDescriptor.setComment(ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENTS).get()));
        }

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPatientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPatientCommand(patientTobeEdited, editPatientDescriptor);
    }
}
