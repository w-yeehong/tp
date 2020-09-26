package seedu.address.testutil;

import seedu.address.logic.commands.SearchPatientCommand.SearchPatientDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.person.TemperatureRange;

/**
 * A utility class to help with building SearchPatientDescriptor objects.
 */
public class SearchPatientDescriptorBuilder {

    private SearchPatientDescriptor descriptor;

    public SearchPatientDescriptorBuilder() {
        descriptor = new SearchPatientDescriptor();
    }


    /**
     * Sets the {@code Name} of the {@code SearchPatientDescriptor} that we are building.
     */
    public SearchPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Temperature} of the {@code SearchPatientDescriptor} that we are building.
     */
    public SearchPatientDescriptorBuilder withTemperatureRange(String temperatureRange) {
        descriptor.setTemperatureRange(new TemperatureRange(temperatureRange));
        return this;
    }

    public SearchPatientDescriptor build() {
        return descriptor;
    }
}
