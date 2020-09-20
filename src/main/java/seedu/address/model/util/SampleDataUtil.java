package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Age;
import seedu.address.model.person.Name;
import seedu.address.model.person.PeriodOfStay;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Temperature;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Temperature("36.7"), new PeriodOfStay("20200908-20200918"),
                    new Phone("87438807"), new Age("23")),
            new Person(new Name("Bernice Yu"), new Temperature("37.0"), new PeriodOfStay("20200808-20200819"),
                    new Phone("99272758"), new Age("37")),
            new Person(new Name("Charlotte Oliveiro"), new Temperature("38.0"), new PeriodOfStay("20200301-20200309"),
                    new Phone("93210283"), new Age("87")),
            new Person(new Name("David Li"), new Temperature("35.8"), new PeriodOfStay("20201001-20201014"),
                    new Phone("91031282"), new Age("13")),
            new Person(new Name("Irfan Ibrahim"), new Temperature("37.7"), new PeriodOfStay("20200901-20200915"),
                    new Phone("92492021"), new Age("65"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
