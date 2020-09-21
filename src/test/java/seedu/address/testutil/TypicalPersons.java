package seedu.address.testutil;

import static seedu.address.logic.commands.NewCommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PERIOD_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PERIOD_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TEMP_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TEMP_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTemperature("36.8").withPeriodOfStay("20200911-20200918")
            .withPhone("94351253").withAge("31").withComment("Vegan").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTemperature("38.0").withPeriodOfStay("20200810-20200824")
            .withPhone("98765432").withAge("67").withComment("-").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withTemperature("38.3").withPeriodOfStay("20200910-20200917")
            .withPhone("95352563").withAge("17").withComment("asthmatic").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withTemperature("36.3").withPeriodOfStay("20200808-20200809")
            .withPhone("87652533").withAge("55").withComment("-").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withTemperature("37.4").withPeriodOfStay("20200707-20200719")
            .withPhone("9482224").withAge("81").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withTemperature("36.1").withPeriodOfStay("20201004-20201019")
            .withPhone("9482427").withAge("11").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withTemperature("36.4").withPeriodOfStay("20201101-20201114")
            .withPhone("9482442").withAge("28").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withTemperature("37.0").withPeriodOfStay("20200913-20200920")
            .withPhone("8482424").withAge("90").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withTemperature("37.6").withPeriodOfStay("20200404-20200414")
            .withPhone("8482131").withAge("54").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTemperature(VALID_TEMP_AMY).withPeriodOfStay(VALID_PERIOD_AMY)
            .withAge(VALID_AGE_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTemperature(VALID_TEMP_BOB).withPeriodOfStay(VALID_PERIOD_BOB)
            .withAge(VALID_AGE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
