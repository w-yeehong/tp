package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.patient.Temperature;
import seedu.address.model.patient.TemperatureRange;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_AGE = "twenty-two";
    private static final String INVALID_TEMPERATURE = "37.h";
    private static final String INVALID_PERIOD_OF_STAY = "20201919-20191817";
    private static final String INVALID_TEMP_RANGE = "37.x-3";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_AGE = "22";
    private static final String VALID_TEMPERATURE = "36.7";
    private static final String VALID_PERIOD_OF_STAY = "20201001-20201014";
    private static final String VALID_TEMP_RANGE = "36.7-37.0";
    private static final String COMMENT = "Is asthmatic";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseAge_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAge((String) null));
    }

    @Test
    public void parseAge_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAge(INVALID_AGE));
    }

    @Test
    public void parseAge_validValueWithoutWhitespace_returnsAge() throws Exception {
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, ParserUtil.parseAge(VALID_AGE));
    }

    @Test
    public void parseAge_validValueWithWhitespace_returnsTrimmedAge() throws Exception {
        String ageWithWhitespace = WHITESPACE + VALID_AGE + WHITESPACE;
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, ParserUtil.parseAge(ageWithWhitespace));
    }

    @Test
    public void parseTempRange_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTemperatureRange((String) null));
    }

    @Test
    public void parseTempRange_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTemperatureRange(INVALID_TEMP_RANGE));
    }

    @Test
    public void parseTempRange_validValueWithoutWhitespace_returnsTempRange() throws Exception {
        TemperatureRange expectedTempRange = new TemperatureRange(VALID_TEMP_RANGE);
        assertEquals(expectedTempRange, ParserUtil.parseTemperatureRange(VALID_TEMP_RANGE));
    }

    @Test
    public void parseTempRange_validValueWithWhitespace_returnsTrimmedTempRange() throws Exception {
        String tempRangeWithWhitespace = WHITESPACE + VALID_TEMP_RANGE + WHITESPACE;
        TemperatureRange expectedTempRange = new TemperatureRange(VALID_TEMP_RANGE);
        assertEquals(expectedTempRange, ParserUtil.parseTemperatureRange(tempRangeWithWhitespace));
    }

    @Test
    public void parseTemperature_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTemperature(INVALID_TEMPERATURE));
    }

    @Test
    public void parseTemperature_validValueWithoutWhitespace_returnsTemperature() throws Exception {
        Temperature expectedTemperature = new Temperature(VALID_TEMPERATURE);
        assertEquals(expectedTemperature, ParserUtil.parseTemperature(VALID_TEMPERATURE));
    }

    @Test
    public void parseTemperature_validValueWithWhitespace_returnsTrimmedTemperature() throws Exception {
        String tempWithWhitespace = WHITESPACE + VALID_TEMPERATURE + WHITESPACE;
        Temperature expectedTemperature = new Temperature(VALID_TEMPERATURE);
        assertEquals(expectedTemperature, ParserUtil.parseTemperature(tempWithWhitespace));
    }

    @Test
    public void parseTemperature_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTemperature((String) null));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parsePeriodOfStay_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePeriodOfStay((String) null));
    }

    @Test
    public void parsePeriodOfStay_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePeriodOfStay(INVALID_PERIOD_OF_STAY));
    }

    @Test
    public void parsePeriodOfStay_validValueWithoutWhitespace_returnsPeriodOfStay() throws Exception {
        PeriodOfStay expectedPeriodOfStay = new PeriodOfStay(VALID_PERIOD_OF_STAY);
        assertEquals(expectedPeriodOfStay, ParserUtil.parsePeriodOfStay(VALID_PERIOD_OF_STAY));
    }

    @Test
    public void parsePeriodOfStay_validValueWithWhitespace_returnsTrimmedPeriodOfStay() throws Exception {
        String periodOfStayWithWhitespace = WHITESPACE + VALID_PERIOD_OF_STAY + WHITESPACE;
        PeriodOfStay expectedPeriodOfStay = new PeriodOfStay(VALID_PERIOD_OF_STAY);
        assertEquals(expectedPeriodOfStay, ParserUtil.parsePeriodOfStay(periodOfStayWithWhitespace));
    }

    @Test
    public void parseComment_null_returnsEmptyComment() {
        Comment emptyComment = new Comment("-");
        assertEquals(emptyComment, ParserUtil.parseComment(null));
    }

    public void parseComment_validComment_returnsComment() {
        Comment expectedComment = new Comment(COMMENT);
        assertEquals(expectedComment, ParserUtil.parseComment(COMMENT));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
