package seedu.address.testutil;

import seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import seedu.address.model.patient.Name;
import seedu.address.model.room.Room;

public class EditRoomDescriptorBuilder {

    private EditRoomDescriptor descriptor;

    public EditRoomDescriptorBuilder() {
        descriptor = new EditRoomDescriptor();
    }

    public EditRoomDescriptorBuilder(EditRoomDescriptor descriptor) {
        this.descriptor = new EditRoomDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRoomDescriptor} with fields containing {@code room}'s details
     */
    public EditRoomDescriptorBuilder(Room room) {
        descriptor = new EditRoomDescriptor();
        descriptor.setRoomNumber(room.getRoomNumber());
        descriptor.setOccupied(room.isOccupied());
        descriptor.setPatientName(room.getPatient().getName());
    }

    /**
     * Sets the {@code roomNumber} of the {@code EditRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withName(Integer roomNumber) {
        descriptor.setRoomNumber(roomNumber);
        return this;
    }

    /**
     * Sets the {@code isOccupied} of the {@code EditRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withOccupancy(Boolean isOccupied) {
        descriptor.setOccupied(isOccupied);
        return this;
    }

    /**
     * Sets the {@code patient} of the {@code EditRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withPatient(Name name) {
        descriptor.setPatientName(name);
        return this;
    }

    public EditRoomDescriptor build() {
        return descriptor;
    }
}
