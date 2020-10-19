package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_NUMBER;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ParserUtilTest {

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parsePositiveInteger_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePositiveInteger("10 a"));
    }

    @Test
    public void parsePositiveInteger_negativeInteger_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_NUMBER, () ->
                ParserUtil.parsePositiveInteger("-1"));
    }

    @Test
    public void parsePositiveInteger_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_NUMBER, () ->
                ParserUtil.parsePositiveInteger(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parsePositiveInteger_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(1, ParserUtil.parsePositiveInteger("1"));
        // Leading and trailing whitespaces
        assertEquals(1, ParserUtil.parsePositiveInteger("  1  "));
    }

    @Test
    public void arePrefixesPresent_prefixMissing_returnsFalse() {
        Prefix PREFIX_ONE = mock(Prefix.class);
        Prefix PREFIX_TWO = mock(Prefix.class);

        ArgumentMultimap argMultimap = mock(ArgumentMultimap.class);

        // No prefixes
        assertFalse(arePrefixesPresent(argMultimap, PREFIX_ONE, PREFIX_TWO));

        // One missing prefix
        when(argMultimap.getValue(PREFIX_ONE)).thenReturn(Optional.of("one"));
        when(argMultimap.getValue(PREFIX_TWO)).thenReturn(Optional.empty());
        assertFalse(arePrefixesPresent(argMultimap, PREFIX_ONE, PREFIX_TWO));
    }

    @Test
    public void arePrefixesPresent_allPrefixesPresent_returnsTrue() {
        Prefix PREFIX_ONE = mock(Prefix.class);
        Prefix PREFIX_TWO = mock(Prefix.class);

        ArgumentMultimap argMultimap = mock(ArgumentMultimap.class);
        when(argMultimap.getValue(PREFIX_ONE)).thenReturn(Optional.of("one"));
        when(argMultimap.getValue(PREFIX_TWO)).thenReturn(Optional.of("two"));

        assertTrue(arePrefixesPresent(argMultimap, PREFIX_ONE, PREFIX_TWO));
    }
}
