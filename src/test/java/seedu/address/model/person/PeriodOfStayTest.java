package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PeriodOfStayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PeriodOfStay(null));
    }

    @Test
    public void constructor_invalidPeriodOfStay_throwsIllegalArgumentException() {
        String invalidPeriodOfStay = "";
        assertThrows(IllegalArgumentException.class, () -> new PeriodOfStay(invalidPeriodOfStay));
    }

    @Test
    public void isValidPeriodOfStay() {
        // null period of stay
        assertThrows(NullPointerException.class, () -> PeriodOfStay.isValidPeriodOfStay(null));

        // invalid period of stay
        assertFalse(PeriodOfStay.isValidPeriodOfStay("")); // empty string
        assertFalse(PeriodOfStay.isValidPeriodOfStay(" ")); // spaces only
        assertFalse(PeriodOfStay.isValidPeriodOfStay("stay")); //non-numeric
        assertFalse(PeriodOfStay.isValidPeriodOfStay("20200709")); //no end date
        assertFalse(PeriodOfStay.isValidPeriodOfStay("2020090p-20201009")); //alphabets within digits
        assertFalse(PeriodOfStay.isValidPeriodOfStay("20200101 - 20200202")); //spaces in input
        assertFalse(PeriodOfStay.isValidPeriodOfStay("2020090120201009")); //missing dash
        assertFalse(PeriodOfStay.isValidPeriodOfStay("20200901-20201038")); //no such date
        assertFalse(PeriodOfStay.isValidPeriodOfStay("20201801-20201001")); //no such month
        assertFalse(PeriodOfStay.isValidPeriodOfStay("20200901-20200801")); //end date before start date

       //valid period of stay
        assertTrue(PeriodOfStay.isValidPeriodOfStay("20190308-20190506"));
        assertTrue(PeriodOfStay.isValidPeriodOfStay("20200913-20200914"));
    }
}
