package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.TemperatureRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP_RANGE;

/**
 * Search a person or a list of person according to a name or a range of temperature.
 */
public class SearchPatientCommand extends Command {

    public static final String COMMAND_WORD = "searchpatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search a patient in Covigent. "
            + "Parameters: "
            + PREFIX_NAME + "NAME || "
            + PREFIX_TEMP_RANGE + "TEMPERATURERANGE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe ";

    public static final String MESSAGE_SEARCH_PERSON_SUCCESS = "Person found: %1$s";
    public static final String MESSAGE_NOT_FOUND = "At least one field to edit must be provided.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "The person you entered is not in the list.";
    public static final String MESSAGE_SEARCH_PERSON_LIST_SUCCESS = "Person match your criteria found: \n";

    private final SearchPatientDescriptor searchPatientDescriptor;
    private final int AREA_NOT_FOUND = 0;
    private final int AREA_IS_NAME = 1;
    private final int AREA_IS_TEMPERATURE = 2;
    private final int TOO_MANY_AREA = 3;

    /**
     * Constructs an SearchPatientCommand to edit the patient with the name {@code String}.
     *
     * @param searchPatientDescriptor Description of the searchPerson command.
     */
    public SearchPatientCommand(SearchPatientDescriptor searchPatientDescriptor) {
        requireNonNull(searchPatientDescriptor);
        this.searchPatientDescriptor = searchPatientDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        final int AREA_TO_SERACH = this.ConfirmArea(searchPatientDescriptor);
        ArrayList<Person> personWithinTemperatureRange = new ArrayList<>();
        List<Person> personList = model.getFilteredPersonList();

        if (AREA_TO_SERACH == AREA_NOT_FOUND) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        else if (AREA_TO_SERACH == AREA_IS_NAME) {
            String nameToSearch = searchPatientDescriptor.getName().toString().trim().toLowerCase();
            Person personFound = personList.get(0);
            boolean isPersonFound = false;
            
            for (Person person : personList) {
                String personName = person.getName().toString().trim().toLowerCase();
                if (personName.equals(nameToSearch)) {
                    personFound = person;
                    isPersonFound = true;

                }
            }
            if (!isPersonFound) {
                throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
            } else {
                return new CommandResult(String.format(MESSAGE_SEARCH_PERSON_SUCCESS, personFound));
            }
        }

        else if (AREA_TO_SERACH == AREA_IS_TEMPERATURE) {
            double startingTemperature = searchPatientDescriptor.getTemperatureRange().getStartingTemperature();
            double endingTemperature = searchPatientDescriptor.getTemperatureRange().getEndingTemperature();
            for (Person person : personList) {
                if (person.getTemperature().getValue() >= startingTemperature &&
                        person.getTemperature().getValue() <= endingTemperature) {
                    personWithinTemperatureRange.add(person);
                }
            }

            if (personWithinTemperatureRange.isEmpty()) {
                throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
            } else {
                return new CommandResult(MESSAGE_SEARCH_PERSON_LIST_SUCCESS +
                        getListOutput(personWithinTemperatureRange));
            }
        } else if (AREA_TO_SERACH == TOO_MANY_AREA) {
            return new CommandResult(Messages.MESSAGE_TOO_MANY_COMMANDS);
        }
        return null;
    }

    /**
     * Return the list of persons' details
     * @param list a list that stores the filtered persons.
     * @return a String output of the persons' details.
     */
    public String getListOutput(ArrayList<Person> list) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0 ; i < list.size(); i++) {
            outputString.append(String.format("%d.%s\n", i + 1, list.get(i)));
        }
        return outputString.toString();
    }

    /**
     * Confirm the area to look for.
     * @param searchPatientDescriptor Details of the searchPatient Command.
     * @return area to look for.
     */
    public int ConfirmArea(SearchPatientDescriptor searchPatientDescriptor) {
        if (searchPatientDescriptor.getOptionalName().isEmpty()
                && searchPatientDescriptor.getOptionalTemperatureRange().isEmpty()) {
            return AREA_NOT_FOUND;
        }
        else if (searchPatientDescriptor.getOptionalName().isPresent()
                && searchPatientDescriptor.getOptionalTemperatureRange().isEmpty()) {
            return AREA_IS_NAME;
        }
        else if (searchPatientDescriptor.getOptionalName().isEmpty()
                && searchPatientDescriptor.getOptionalTemperatureRange().isPresent()) {
            return AREA_IS_TEMPERATURE;
        }
        else {
            return TOO_MANY_AREA;
        }

    }

    /**
     * Stores the details to search the person .
     * The class is used with @ConfirmArea method to confirm the area to look for.
     */
    public static class SearchPatientDescriptor {
        private Name name;
        private TemperatureRange temperatureRange;

        public SearchPatientDescriptor() {
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getOptionalName() {
            return Optional.ofNullable(name);
        }

        public Name getName() {
            return name;
        }

        public void setTemperatureRange(TemperatureRange temperatureRange) {
            this.temperatureRange = temperatureRange;
        }

        public Optional<TemperatureRange> getOptionalTemperatureRange() {
            return Optional.ofNullable(temperatureRange);
        }

        public TemperatureRange getTemperatureRange() {
            return temperatureRange;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) { // short circuit if same object
                return true;
            }

            if (!(other instanceof SearchPatientDescriptor)) { // instanceof handles nulls
                return false;
            }

            SearchPatientDescriptor e = (SearchPatientDescriptor) other; // state check

            return getName().equals(e.getName())
                    && getTemperatureRange().equals(e.getTemperatureRange());
        }
    }

}
