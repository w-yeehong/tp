package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP;

import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditPatientCommandParser {


    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPatientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_COMMENTS, PREFIX_TEMP, PREFIX_AGE, PREFIX_PERIOD_OF_STAY);

        String personTobeEdited = argMultimap.getPreamble().trim().toLowerCase();

        EditPatientCommand.EditPersonDescriptor editPersonDescriptor = new EditPatientCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_TEMP).isPresent()) {
            editPersonDescriptor.setTemperature(ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMP).get()));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPersonDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERIOD_OF_STAY).isPresent()) {
            editPersonDescriptor.setPeriodOfStay(ParserUtil
                    .parsePeriodOfStay(argMultimap.getValue(PREFIX_PERIOD_OF_STAY).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMENTS).isPresent()) {
            editPersonDescriptor.setComment(ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENTS).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPatientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPatientCommand(personTobeEdited, editPersonDescriptor);
    }

}


