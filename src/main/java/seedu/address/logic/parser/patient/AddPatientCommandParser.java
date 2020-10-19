package seedu.address.logic.parser.patient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.patient.AddPatientCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;

/**
 * Parses input arguments and creates a new AddPatientCommand object.
 */
public class AddPatientCommandParser implements Parser<AddPatientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPatientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TEMP, PREFIX_PERIOD_OF_STAY,
                        PREFIX_PHONE, PREFIX_AGE, PREFIX_COMMENTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TEMP, PREFIX_PERIOD_OF_STAY,
                PREFIX_PHONE, PREFIX_AGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Temperature temp = ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMP).get());
        PeriodOfStay periodOfStay = ParserUtil.parsePeriodOfStay(argMultimap.getValue(PREFIX_PERIOD_OF_STAY).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Comment comment = new Comment("-");
        try {
            comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENTS).get());
        } catch (NoSuchElementException e) {
            //unhandled because comment will be initialised already
        }

        Patient patient = new Patient(name, temp, periodOfStay, phone, age, comment);
        return new AddPatientCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
