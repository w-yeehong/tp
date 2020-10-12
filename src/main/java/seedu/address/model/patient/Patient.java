package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Patient in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    private final Name name; //identifier field
    private final Temperature temperature;
    private final PeriodOfStay periodOfStay;
    private final Phone phone; //identifier field
    private final Age age; //identifier field
    private final Comment comment; // an optional field, if null is initialised to "-"

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Temperature temperature, PeriodOfStay periodOfStay,
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
     * Formats the {@code patient} information.
     *
     * @param patient That is to be formatted.
     * @return A string containing the patient information.
     */
    public static String formatPatientDetails(Patient patient) {
        String name = patient.getName().toString();
        String temperature = patient.getTemperature().toString();
        String periodOfStay = patient.getPeriodOfStay().toString();
        String phone = patient.getPhone().toString();
        String age = patient.getAge().toString();
        String comment = patient.getComment().toString();
        return String.format(" Name: %s\n Temperature: %s\n Period Of Stay: %s\n Phone: %s\n Age: %s\n Comment: %s\n",
            name, temperature, periodOfStay, phone, age, comment);
    }
    /**
     * Returns true if both patients of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(getName())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getAge().equals(getAge());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getName().equals(getName())
                && otherPatient.getPeriodOfStay().equals(getPeriodOfStay())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getAge().equals(getAge());
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
