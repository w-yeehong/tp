package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalCovigentApp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.testutil.PatientBuilder;

public class CovigentAppTest {

    private final CovigentApp covigentApp = new CovigentApp();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), covigentApp.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> covigentApp.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCovigentApp_replacesData() {
        CovigentApp newData = getTypicalCovigentApp();
        covigentApp.resetData(newData);
        assertEquals(newData, covigentApp);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        CovigentAppStub newData = new CovigentAppStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> covigentApp.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> covigentApp.hasPatient(null));
    }

    @Test
    public void hasPatient_patientNotInCovigentApp_returnsFalse() {
        assertFalse(covigentApp.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInCovigentApp_returnsTrue() {
        covigentApp.addPatient(ALICE);
        assertTrue(covigentApp.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInCovigentApp_returnsTrue() {
        covigentApp.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).build();
        assertTrue(covigentApp.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> covigentApp.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyCovigentApp whose patients list can violate interface constraints.
     */
    private static class CovigentAppStub implements ReadOnlyCovigentApp {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        CovigentAppStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }
    }

}
