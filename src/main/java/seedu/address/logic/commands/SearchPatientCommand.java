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

import static seedu.address.logic.parser.CliSyntax.*;

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

    private final SearchPersonDescriptor searchPersonDescriptor;
    private final int AREA_NOT_FOUND = 0;
    private final int AREA_IS_NAME = 1;
    private final int AREA_IS_TEMPERATURE = 2;
    private final int TOO_MANY_AREA = 3;

    /**
     * Constructs an EditCommand to edit the patient with the name {@code String}.
     *
     * @param searchPersonDescriptor Description of the searchPerson command.
     */
    public SearchPatientCommand(SearchPersonDescriptor searchPersonDescriptor) {
        requireNonNull(searchPersonDescriptor);

        this.searchPersonDescriptor = searchPersonDescriptor;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int AREA_TO_SERACH = this.ConfirmArea(searchPersonDescriptor);
        ArrayList<Person> personWithinTemperatureRange = new ArrayList<>();
        List<Person> personList = model.getFilteredPersonList();

        switch (AREA_TO_SERACH) {
            case (AREA_NOT_FOUND):
                throw new CommandException(MESSAGE_NOT_FOUND);

            case (AREA_IS_NAME):
                String nameToSearch = searchPersonDescriptor.getName().toString().trim().toLowerCase();
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


            case (AREA_IS_TEMPERATURE):
                double startingTemperature = searchPersonDescriptor.getTemperatureRange().getStartingTemperature();
                double endingTemperature = searchPersonDescriptor.getTemperatureRange().getEndingTemperature();
                for (Person person : personList) {
                    if (person.getTemperature().getValue() >= startingTemperature &&
                            person.getTemperature().getValue() <= endingTemperature ) {
                        personWithinTemperatureRange.add(person);
                    }
                }

                if (personWithinTemperatureRange.isEmpty()) {
                    throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
                }
                else {
                    return new CommandResult(MESSAGE_SEARCH_PERSON_LIST_SUCCESS +
                            getListOutput(personWithinTemperatureRange));
                }

            case (TOO_MANY_AREA):
                return new CommandResult(Messages.MESSAGE_TOO_MANY_COMMANDS);


        }

        return null;
    }

    public String getListOutput(ArrayList<Person> list) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0 ; i < list.size(); i++) {
            outputString.append(String.format("%d.%s\n", i + 1, list.get(i)));
        }
        return outputString.toString();
    }

    public int ConfirmArea(SearchPersonDescriptor searchPersonDescriptor) {
        if (searchPersonDescriptor.getOptionalName().isEmpty() && searchPersonDescriptor.getOptionalTemperatureRange().isEmpty()) {
            return AREA_NOT_FOUND;
        }
        else if (searchPersonDescriptor.getOptionalName().isPresent() && searchPersonDescriptor.getOptionalTemperatureRange().isEmpty()) {
            return AREA_IS_NAME;
        }
        else if (searchPersonDescriptor.getOptionalName().isEmpty() && searchPersonDescriptor.getOptionalTemperatureRange().isPresent()) {
            return AREA_IS_TEMPERATURE;
        }
        else {
            return TOO_MANY_AREA;
        }

    }


    public static class SearchPersonDescriptor {
        private Name name;
        private TemperatureRange temperatureRange;


        public SearchPersonDescriptor() {

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

            if (!(other instanceof SearchPersonDescriptor)) { // instanceof handles nulls
                return false;
            }

            SearchPersonDescriptor e = (SearchPersonDescriptor) other; // state check

            return getName().equals(e.getName())
                    && getTemperatureRange().equals(e.getTemperatureRange());

        }
    }
}