package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENT_ALREADY_ASSIGNED;
import static seedu.address.logic.commands.room.AllocateRoomCommand.MESSAGE_ALLOCATE_ROOM_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BOB;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.task.TaskList;
import seedu.address.testutil.AllocateRoomDescriptorBuilder;
import seedu.address.testutil.RoomBuilder;

/**
 * Contains integration tests and unit tests for AllocateRoomCommand.
 */
class AllocateRoomCommandTest {

    //patient records -> [ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE]
    //room list -> [room 7, Alice; room 8, Benson; room 10, null]
    private Model model =
            new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList(), new TaskList());


    @Test
    public void constructor_nullInputs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AllocateRoomCommand(null, null));
    }

    @Test
    public void execute_invalidRoomNumber_failure() {
        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder().build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(-100, descriptor);
        assertThrows(AssertionError.class, () -> allocateRoomCommand.execute(model));
    }

    @Test
    public void execute_changePatientInRoom_success() {
        Room roomToAllocatePatient = model.getRoomList().get(0);
        Integer roomNumberForAllocation = roomToAllocatePatient.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToAllocatePatient).withPatient(CARL).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
                .withPatient(CARL.getName()).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberForAllocation, descriptor);

        String expectedMessage = String.format(MESSAGE_ALLOCATE_ROOM_SUCCESS, editedRoom);
        Model expectedModel = new ModelManager(getTypicalPatientRecords(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()), new TaskList());
        expectedModel.setSingleRoom(roomToAllocatePatient, editedRoom);

        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearRoom_success() {
        Room roomToAllocatePatient = model.getRoomList().get(0);
        Integer roomNumberForAllocation = roomToAllocatePatient.getRoomNumber();
        Room allocatedRoom = new RoomBuilder(roomToAllocatePatient).withIsOccupied(false).withPatient(null).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
                .withOccupancy(false).build();

        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberForAllocation, descriptor);

        String expectedMessage = String.format(MESSAGE_ALLOCATE_ROOM_SUCCESS, allocatedRoom);

        Model expectedModel = new ModelManager(getTypicalPatientRecords(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()), new TaskList());
        expectedModel.setSingleRoom(roomToAllocatePatient, allocatedRoom);

        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allocatePatientAlreadyInAnotherRoom_failure() {
        Room roomToAllocatePatient = model.getRoomList().get(2); //the empty room
        Integer roomNumberForAllocation = roomToAllocatePatient.getRoomNumber();
        Patient patientInAnotherRoom = model.getRoomList().get(0).getPatient();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
                .withPatient(patientInAnotherRoom.getName()).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberForAllocation, descriptor);

        assertCommandFailure(allocateRoomCommand, model, MESSAGE_PATIENT_ALREADY_ASSIGNED);
    }

    @Test
    public void execute_invalidPatientAllocated_failure() {
        Room roomToAllocatePatient = model.getRoomList().get(0);
        Integer roomNumberForAllocation = roomToAllocatePatient.getRoomNumber();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
                .withPatient(BOB.getName()).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberForAllocation, descriptor);

        assertCommandFailure(allocateRoomCommand, model, Messages.MESSAGE_INVALID_PATIENT_NAME);
    }

    @Test
    public void equals() {
        Room allocateRoom7 = model.getRoomList().get(0);
        AllocateRoomCommand.AllocateRoomDescriptor descriptor1 = new AllocateRoomDescriptorBuilder(allocateRoom7)
                .withRoomNumber(20).build();
        AllocateRoomCommand allocateRoomNumberCommand = new AllocateRoomCommand(allocateRoom7.getRoomNumber(),
            descriptor1);

        Room allocateRoom8 = model.getRoomList().get(1);
        AllocateRoomCommand.AllocateRoomDescriptor descriptor2 = new AllocateRoomDescriptorBuilder(allocateRoom8)
                .withPatient(CARL.getName()).build();
        AllocateRoomCommand allocatePatientInRoomCommand = new AllocateRoomCommand(allocateRoom8.getRoomNumber(),
            descriptor2);

        // same object -> returns true
        assertTrue(allocateRoomNumberCommand.equals(allocateRoomNumberCommand));

        // same values -> returns true
        AllocateRoomCommand editRoomNumberCommandCopy = new AllocateRoomCommand(allocateRoom7.getRoomNumber(),
            descriptor1);
        assertTrue(allocateRoomNumberCommand.equals(editRoomNumberCommandCopy));

        // different types -> returns false
        assertFalse(allocateRoomNumberCommand.equals(1));

        // null -> returns false
        assertFalse(allocateRoomNumberCommand.equals(null));

        // different attributes to edit
        assertFalse(allocateRoomNumberCommand.equals(allocatePatientInRoomCommand));
    }
}
