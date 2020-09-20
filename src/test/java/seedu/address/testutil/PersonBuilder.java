package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PeriodOfStay;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Temperature;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_TEMPERATURE = "36.8";
    public static final String DEFAULT_PERIOD = "20201001-20201014";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_AGE = "37";

    private Name name;
    private Temperature temperature;
    private PeriodOfStay periodOfStay;
    private Phone phone;
    private Age age;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        temperature = new Temperature(DEFAULT_TEMPERATURE);
        periodOfStay = new PeriodOfStay(DEFAULT_PERIOD);
        phone = new Phone(DEFAULT_PHONE);
        age = new Age(DEFAULT_AGE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        temperature = personToCopy.getTemperature();
        periodOfStay = personToCopy.getPeriodOfStay();
        phone = personToCopy.getPhone();
        age = personToCopy.getAge();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Temperature} of the {@code Person} that we are building.
     */
    public PersonBuilder withTemperature(String temperature) {
        this.temperature = new Temperature(temperature);
        return this;
    }

    /**
     * Sets the {@code Period of stay} of the {@code Person} that we are building.
     */
    public PersonBuilder withPeriodOfStay(String periodOfStay) {
        this.periodOfStay = new PeriodOfStay(periodOfStay);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    public Person build() {
        return new Person(name, temperature, periodOfStay, phone, age);
    }

}
