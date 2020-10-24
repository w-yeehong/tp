package seedu.address.logic.parser.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_PATIENT_NAME;

import seedu.address.logic.commands.room.AllocateRoomCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.patient.PatientParserUtil;

/**
 * Parses input arguments and creates a new AllocateRoomCommand object.
 */
public class AllocateRoomCommandParser implements Parser<AllocateRoomCommand> {
    private static final String INPUT_REMOVE_PATIENT = "-";

    /**
     * Parses the given {@code String} of arguments in the context of the EditPatientCommand
     * and returns an EditPatientCommand object for execution.
     *
     * @param args that are inputted by the user.
     * @return EditPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AllocateRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_NAME);

        Integer roomToBeAllocated;

        try {
            roomToBeAllocated = ParserUtil.parsePositiveInteger(argMultimap.getPreamble().trim());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateRoomCommand.MESSAGE_USAGE));
        }

        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateRoomCommand.MESSAGE_USAGE));
        }

        AllocateRoomCommand.AllocateRoomDescriptor
            allocateRoomDescriptor = new AllocateRoomCommand.AllocateRoomDescriptor();

        if (argMultimap.getValue(PREFIX_PATIENT_NAME).isPresent()) {
            String patientFieldInput = argMultimap.getValue(PREFIX_PATIENT_NAME).get();
            if (patientFieldInput.equals(INPUT_REMOVE_PATIENT)) {
                allocateRoomDescriptor.setOccupied(false); //empty the room
            } else {
                allocateRoomDescriptor.setPatientName(PatientParserUtil.parseName(patientFieldInput));
            }
        }

        if (!allocateRoomDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AllocateRoomCommand.MESSAGE_ROOM_NOT_EDITED);
        }

        return new AllocateRoomCommand(roomToBeAllocated, allocateRoomDescriptor);
    }
}
