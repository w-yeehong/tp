package seedu.address.testutil;

import seedu.address.logic.commands.room.AllocateRoomCommand;
import seedu.address.logic.commands.room.AllocateRoomCommand.AllocateRoomDescriptor;
import seedu.address.model.patient.Name;
import seedu.address.model.room.Room;

public class EditRoomDescriptorBuilder {

    private AllocateRoomDescriptor descriptor;

    public EditRoomDescriptorBuilder() {
        descriptor = new AllocateRoomDescriptor();
    }

    public EditRoomDescriptorBuilder(AllocateRoomCommand.AllocateRoomDescriptor descriptor) {
        this.descriptor = new AllocateRoomCommand.AllocateRoomDescriptor(descriptor);
    }

    /**
     * Returns an {@code AllocateRoomDescriptor} with fields containing {@code room}'s details
     */
    public EditRoomDescriptorBuilder(Room room) {
        descriptor = new AllocateRoomDescriptor();
        descriptor.setRoomNumber(room.getRoomNumber());
        descriptor.setOccupied(room.isOccupied());
        descriptor.setPatientName(room.getPatient().getName());
    }

    /**
     * Sets the {@code roomNumber} of the {@code AllocateRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withRoomNumber(Integer roomNumber) {
        descriptor.setRoomNumber(roomNumber);
        return this;
    }

    /**
     * Sets the {@code isOccupied} of the {@code AllocateRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withOccupancy(Boolean isOccupied) {
        descriptor.setOccupied(isOccupied);
        return this;
    }

    /**
     * Sets the {@code patient} of the {@code AllocateRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withPatient(Name name) {
        descriptor.setPatientName(name);
        return this;
    }

    public AllocateRoomDescriptor build() {
        return descriptor;
    }

    @Override
    public String toString() {
        return "EditRoomDescriptorBuilder{"
            + "descriptor=" + descriptor
            + '}';
    }
}
