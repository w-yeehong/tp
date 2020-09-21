package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Age;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Name;
import seedu.address.model.person.PeriodOfStay;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Temperature;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddPersonCommandParser implements Parser<AddPersonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns an AddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TEMP, PREFIX_PERIOD_OF_STAY,
                        PREFIX_PHONE, PREFIX_AGE, PREFIX_COMMENTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TEMP, PREFIX_PERIOD_OF_STAY,
                PREFIX_PHONE, PREFIX_AGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Temperature temp = ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMP).get());
        PeriodOfStay periodOfStay = ParserUtil.parsePeriodOfStay(argMultimap.getValue(PREFIX_PERIOD_OF_STAY).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Comment comment = new Comment("-");
        try {
            comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENTS).get());
        } catch (NoSuchElementException e){
            //unhandled because comment will be initialised already
        }

        Person person = new Person(name, temp, periodOfStay, phone, age, comment);
        return new AddPersonCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
