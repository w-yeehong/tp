package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Age;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Name;
import seedu.address.model.person.PeriodOfStay;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Temperature;

public class EditPatientCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the person's name used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NAME (must match exactly with the name of the person to be edited in the person list) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TEMP + "TEMPERATURE] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_PERIOD_OF_STAY + "PERIOD OF STAY] "
            + "[" + PREFIX_COMMENTS + "COMMENT] "
            + "Example: " + COMMAND_WORD + " john "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_TEMP + "36.5";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final String personToBeEdited;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Constructs an EditCommand.
     *
     * @param personToBeEdited name in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditPatientCommand(String personToBeEdited, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToBeEdited);
        requireNonNull(editPersonDescriptor);

        this.personToBeEdited = personToBeEdited;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Index index = Index.fromZeroBased(0);

        List<Person> lastShownList = model.getFilteredPersonList();

        for (int i = 1; i <= lastShownList.size(); i++) {
            String personName = lastShownList.get(i-1).getName().toString();
            boolean isValidPerson = personName.trim().toLowerCase().equals(personToBeEdited);
            if (isValidPerson) {
                index = Index.fromZeroBased(i);
                break;
            }
        }

        if (index.getZeroBased() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased() - 1);
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Temperature updatedTemperature = editPersonDescriptor.getTemperature().orElse(personToEdit.getTemperature());
        Age updatedAge = editPersonDescriptor.getAge().orElse(personToEdit.getAge());
        PeriodOfStay updatedPeriodOfStay = editPersonDescriptor
                .getPeriodOfStay()
                .orElse(personToEdit.getPeriodOfStay());
        Comment updatedComment = editPersonDescriptor.getComment().orElse(personToEdit.getComment());
        return new Person(updatedName, updatedTemperature, updatedPeriodOfStay,
                updatedPhone, updatedAge, updatedComment);
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        // state check
        EditPatientCommand e = (EditPatientCommand) other;
        return personToBeEdited.equals(e.personToBeEdited)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }



    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Temperature temperature;
        private Age age;
        private PeriodOfStay periodOfStay;
        private Comment comment;

        public EditPersonDescriptor() {}
        
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setTemperature(toCopy.temperature);
            setAge(toCopy.age);
            setPeriodOfStay(toCopy.periodOfStay);
            setComment(toCopy.comment);
        }
        
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, temperature, age, periodOfStay, comment);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }

        public Optional<Temperature> getTemperature() {
            return Optional.ofNullable(temperature);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setPeriodOfStay(PeriodOfStay periodOfStay) {
            this.periodOfStay = periodOfStay;
        }

        public Optional<PeriodOfStay> getPeriodOfStay() {
            return Optional.ofNullable(periodOfStay);
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Optional<Comment> getComment() {
            return Optional.ofNullable(comment);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getTemperature().equals(e.getTemperature())
                    && getAge().equals(e.getAge())
                    && getPeriodOfStay().equals(e.getPeriodOfStay())
                    && getComment().equals(e.getComment());
        }
    }
}
