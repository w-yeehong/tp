package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Temperature temperature;
    private final PeriodOfStay periodOfStay;
    private final Phone phone;
    private final Age age;
    private final Comment comment; //an optional field, if null is initialised to "-"

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Temperature temperature, PeriodOfStay periodOfStay,
                  Phone phone, Age age, Comment comment) {
        requireAllNonNull(name, temperature, periodOfStay, phone, age);
        this.name = name;
        this.temperature = temperature;
        this.periodOfStay = periodOfStay;
        this.phone = phone;
        this.age = age;
        this.comment = comment == null ? new Comment("-") : comment;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public PeriodOfStay getPeriodOfStay() {
        return periodOfStay;
    }

    public Age getAge() {
        return age;
    }

    public Comment getComment() {
        return comment;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getPeriodOfStay().equals(getPeriodOfStay())
                && otherPerson.getAge().equals(getAge());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getTemperature().equals(getTemperature())
                && otherPerson.getPeriodOfStay().equals(getPeriodOfStay())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getAge().equals(getAge());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, temperature, periodOfStay, phone, age);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Temperature: ")
                .append(getTemperature())
                .append(" Period of stay: ")
                .append(getPeriodOfStay())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Age: ")
                .append(getAge())
                .append(" Comment: ")
                .append(getComment());
        return builder.toString();
    }

}
