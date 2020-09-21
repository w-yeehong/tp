package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Age;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Name;
import seedu.address.model.person.PeriodOfStay;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Temperature;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String temperature;
    private final String periodOfStay;
    private final String phone;
    private final String age;
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("temperature") String temperature,
            @JsonProperty("periodOfStay") String periodOfStay, @JsonProperty("phone") String phone,
            @JsonProperty("age") String age, @JsonProperty("comment") String comment) {
        this.name = name;
        this.temperature = temperature;
        this.periodOfStay = periodOfStay;
        this.phone = phone;
        this.age = age;
        this.comment = comment;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        temperature = source.getTemperature().toString();
        periodOfStay = source.getPeriodOfStay().toString();
        phone = source.getPhone().value;
        age = source.getAge().toString();
        comment = source.getComment().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (temperature == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Temperature.class.getSimpleName()));
        }

        if (!Temperature.isValidTemperature(temperature)) {
            throw new IllegalValueException(Temperature.MESSAGE_CONSTRAINTS);
        }

        final Temperature modelTemp = new Temperature(temperature);

        if (periodOfStay == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PeriodOfStay.class.getSimpleName()));
        }

        if (!PeriodOfStay.isValidPeriodOfStay(periodOfStay)) {
            throw new IllegalValueException(PeriodOfStay.MESSAGE_CONSTRAINTS);
        }

        final PeriodOfStay modelPeriod = new PeriodOfStay(periodOfStay);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (comment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Comment.class.getSimpleName()));
        }

        final Comment modelComment = new Comment(comment);

        return new Person(modelName, modelTemp, modelPeriod, modelPhone, modelAge, modelComment);
    }

}
