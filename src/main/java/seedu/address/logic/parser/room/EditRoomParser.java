package seedu.address.logic.parser.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_PATIENT_NAME;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_OCCUPIED;

import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditRoomCommand object.
 */
public class EditRoomParser implements Parser<EditRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPatientCommand
     * and returns an EditPatientCommand object for execution.
     *
     * @param args that are inputted by the user.
     * @return EditPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOM_NUMBER, PREFIX_ROOM_OCCUPIED, PREFIX_PATIENT_NAME);

        Integer roomToBeEdited;

        try {
            roomToBeEdited = ParserUtil.parseRoomNumber(argMultimap.getPreamble().trim());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE), pe);
        }

        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE));
        }
        EditRoomCommand.EditRoomDescriptor editRoomDescriptor = new EditRoomCommand.EditRoomDescriptor();
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            editRoomDescriptor.setRoomNumber(
                    ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOM_OCCUPIED).isPresent()) {
            editRoomDescriptor.setOccupied(
                    ParserUtil.parseOccupancy(argMultimap.getValue(PREFIX_ROOM_OCCUPIED).get()));
        }
        if (argMultimap.getValue(PREFIX_PATIENT_NAME).isPresent()) {
            editRoomDescriptor.setPatient(ParserUtil.parsePatient(argMultimap.getValue(PREFIX_PATIENT_NAME).get()));
        }

        if (!editRoomDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRoomCommand.MESSAGE_ROOM_NOT_EDITED);
        }

        return new EditRoomCommand(roomToBeEdited, editRoomDescriptor);
    }
}
