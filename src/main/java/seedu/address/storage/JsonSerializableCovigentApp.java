package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CovigentApp;
import seedu.address.model.ReadOnlyCovigentApp;
import seedu.address.model.patient.Patient;

/**
 * An Immutable CovigentApp that is serializable to JSON format.
 */
@JsonRootName(value = "covigentapp")
class JsonSerializableCovigentApp {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCovigentApp} with the given patients.
     */
    @JsonCreator
    public JsonSerializableCovigentApp(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyCovigentApp} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCovigentApp}.
     */
    public JsonSerializableCovigentApp(ReadOnlyCovigentApp source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code CovigentApp} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CovigentApp toModelType() throws IllegalValueException {
        CovigentApp covigentApp = new CovigentApp();
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
