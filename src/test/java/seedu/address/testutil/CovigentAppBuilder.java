package seedu.address.testutil;

import seedu.address.model.CovigentApp;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building CovigentApp objects.
 * Example usage: <br>
 *     {@code CovigentApp ab = new CovigentAppBuilder().withPatient("John", "Doe").build();}
 */
public class CovigentAppBuilder {

    private CovigentApp covigentApp;

    public CovigentAppBuilder() {
        covigentApp = new CovigentApp();
    }

    public CovigentAppBuilder(CovigentApp covigentApp) {
        this.covigentApp = covigentApp;
    }

    /**
     * Adds a new {@code Patient} to the {@code CovigentApp} that we are building.
     */
    public CovigentAppBuilder withPatient(Patient patient) {
        covigentApp.addPatient(patient);
        return this;
    }

    public CovigentApp build() {
        return covigentApp;
    }
}
