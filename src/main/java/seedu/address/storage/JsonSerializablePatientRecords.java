package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyPatientRecords;
import seedu.address.model.patient.Patient;

/**
 * An Immutable CovigentApp that is serializable to JSON format.
 */
@JsonRootName(value = "covigentapp")
class JsonSerializablePatientRecords {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientRecords} with the given patients.
     */
    @JsonCreator
    public JsonSerializablePatientRecords(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyCovigentApp} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientRecords}.
     */
    public JsonSerializablePatientRecords(ReadOnlyPatientRecords source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code CovigentApp} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PatientRecords toModelType() throws IllegalValueException {
        PatientRecords covigentApp = new PatientRecords();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (covigentApp.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            covigentApp.addPatient(patient);
        }
        return covigentApp;
    }

}
