package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP_RANGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.TemperatureRange;


/**
 * Search a patient or a list of patient according to a name or a range of temperature.
 */
public class SearchPatientCommand extends Command {

    public static final String COMMAND_WORD = "searchpatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Search a patient or a list of patients with only one specific criteria in Covigent. "
            + "Parameters: "
            + PREFIX_NAME + "NAME || "
            + PREFIX_TEMP_RANGE + "TEMPERATURE RANGE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEMP_RANGE + "36.1-37.9 ";

    public static final String MESSAGE_SEARCH_PATIENT_SUCCESS = "Patient(s) found: %1$s";
    public static final String MESSAGE_NOT_FOUND = "At least one field to edit must be provided.";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "The patient you entered is not in the list.";
    public static final String MESSAGE_SEARCH_PATIENT_LIST_SUCCESS = "Patient match your criteria found: \n";

    private final SearchPatientDescriptor searchPatientDescriptor;

    /**
     * Constructs an SearchPatientCommand to edit the patient with the name {@code String}.
     *
     * @param searchPatientDescriptor Description of the searchPatient command.
     */
    public SearchPatientCommand(SearchPatientDescriptor searchPatientDescriptor) {
        requireNonNull(searchPatientDescriptor);
        this.searchPatientDescriptor = searchPatientDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        final SearchCriteria criteriaToSearch = this.confirmCriteria(searchPatientDescriptor);
        List<Patient> patientList = model.getFilteredPatientList();

        if (criteriaToSearch == SearchCriteria.CRITERIA_NOT_FOUND) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        } else if (criteriaToSearch == SearchCriteria.CRITERIA_IS_NAME) {
            return findPatientWithName(searchPatientDescriptor, patientList);
        } else if (criteriaToSearch == SearchCriteria.CRITERIA_IS_TEMPERATURE) {
            return findPatientWithTemperature(searchPatientDescriptor, patientList);
        } else if (criteriaToSearch == SearchCriteria.TOO_MANY_CRITERIA) {
            return new CommandResult(Messages.MESSAGE_TOO_MANY_COMMANDS);
        }
        throw new CommandException(MESSAGE_NOT_FOUND);
    }

    /**
     * Return the patient's detail with specific name
     * @param searchPatientDescriptor the patient's name in the descriptor.
     * @param patientList the patient list stored.
     * @return a CommandResult of the patients' details.
     * @throws CommandException if patient is not found.
     */
    public CommandResult findPatientWithName(SearchPatientDescriptor searchPatientDescriptor,
                                             List<Patient> patientList) throws CommandException {
        String nameToSearch = searchPatientDescriptor.getName().toString().trim().toLowerCase();

        for (Patient patient : patientList) {
            String patientName = patient.getName().toString().trim().toLowerCase();
            if (patientName.equals(nameToSearch)) {
                new CommandResult(String.format(MESSAGE_SEARCH_PATIENT_SUCCESS, patient));
            }
        }
        throw new CommandException(MESSAGE_PATIENT_NOT_FOUND);
    }

    /**
     * Return the patient's detail with specific Temperature Range.
     * @param searchPatientDescriptor the patient's name in the descriptor.
     * @param patientList the patient list stored.
     * @return a CommandResult of the patients' details.
     * @throws CommandException if no patient matching the criteria.
     */
    public CommandResult findPatientWithTemperature(SearchPatientDescriptor searchPatientDescriptor,
                                             List<Patient> patientList) throws CommandException {
        double startingTemperature = searchPatientDescriptor.getTemperatureRange().getStartingTemperature();
        double endingTemperature = searchPatientDescriptor.getTemperatureRange().getEndingTemperature();
        ArrayList<Patient> patientWithinTemperatureRange = new ArrayList<>();
        for (Patient patient : patientList) {
            if (patient.getTemperature().getValue() >= startingTemperature
                    && patient.getTemperature().getValue() <= endingTemperature) {
                patientWithinTemperatureRange.add(patient);
            }
        }

        if (patientWithinTemperatureRange.isEmpty()) {
            throw new CommandException(MESSAGE_PATIENT_NOT_FOUND);
        } else {
            return new CommandResult(MESSAGE_SEARCH_PATIENT_LIST_SUCCESS
                    + getListOutput(patientWithinTemperatureRange));
        }
    }

    /**
     * Return the list of patients' details
     * @param list a list that stores the filtered patients.
     * @return a String output of the patients' details.
     */
    public String getListOutput(ArrayList<Patient> list) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            outputString.append(String.format("%d.%s\n", i + 1, list.get(i)));
        }
        return outputString.toString();
    }

    /**
     * Confirm the criteria to look for.
     * @param searchPatientDescriptor Details of the searchPatient Command.
     * @return criteria to look for.
     */
    public SearchCriteria confirmCriteria(SearchPatientDescriptor searchPatientDescriptor) {
        if (searchPatientDescriptor.getOptionalName().isEmpty()
                && searchPatientDescriptor.getOptionalTemperatureRange().isEmpty()) {
            return SearchCriteria.CRITERIA_NOT_FOUND;
        } else if (searchPatientDescriptor.getOptionalName().isPresent()
                && searchPatientDescriptor.getOptionalTemperatureRange().isEmpty()) {
            return SearchCriteria.CRITERIA_IS_NAME;
        } else if (searchPatientDescriptor.getOptionalName().isEmpty()
                && searchPatientDescriptor.getOptionalTemperatureRange().isPresent()) {
            return SearchCriteria.CRITERIA_IS_TEMPERATURE;
        } else {
            return SearchCriteria.TOO_MANY_CRITERIA;
        }

    }

    private enum SearchCriteria {
        CRITERIA_IS_NAME,
        CRITERIA_IS_TEMPERATURE,
        TOO_MANY_CRITERIA,
        CRITERIA_NOT_FOUND
    }

    /**
     * Stores the details to search the patient .
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
