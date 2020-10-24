package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROOM_NUMBER;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENT_ALREADY_ASSIGNED;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.room.AllocateRoomCommand.MESSAGE_ALLOCATE_ROOM_SUCCESS;
import static seedu.address.logic.commands.room.AllocateRoomCommand.MESSAGE_DUPLICATE_ROOM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BOB;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.task.TaskList;
import seedu.address.testutil.EditRoomDescriptorBuilder;
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
        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder().build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(-100, descriptor);
        assertCommandFailure(allocateRoomCommand, model, MESSAGE_INVALID_ROOM_NUMBER);
    }

    @Test
    public void execute_changeRoomNumber_success() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToEdit).withRoomNumber(roomNumberToEdit + 10).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(roomNumberToEdit + 10).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_ALLOCATE_ROOM_SUCCESS, editedRoom);
        Model expectedModel = new ModelManager(getTypicalPatientRecords(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()), new TaskList());
        expectedModel.setSingleRoom(roomToEdit, editedRoom);

        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_changePatientInRoom_success() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToEdit).withPatient(CARL).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withPatient(CARL.getName()).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_ALLOCATE_ROOM_SUCCESS, editedRoom);
        Model expectedModel = new ModelManager(getTypicalPatientRecords(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()), new TaskList());
        expectedModel.setSingleRoom(roomToEdit, editedRoom);

        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearRoom_success() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToEdit).withIsOccupied(false).withPatient(null).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withOccupancy(false).build();

        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_ALLOCATE_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(getTypicalPatientRecords(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()), new TaskList());
        expectedModel.setSingleRoom(roomToEdit, editedRoom);

        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allocatePatientAlreadyInAnotherRoom_failure() {
        Room roomToEdit = model.getRoomList().get(2); //the empty room
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Patient patientInAnotherRoom = model.getRoomList().get(0).getPatient();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withPatient(patientInAnotherRoom.getName()).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberToEdit, descriptor);

        assertCommandFailure(allocateRoomCommand, model, MESSAGE_PATIENT_ALREADY_ASSIGNED);
    }

    @Test
    public void execute_invalidPatientAllocated_failure() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withPatient(BOB.getName()).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberToEdit, descriptor);

        assertCommandFailure(allocateRoomCommand, model, Messages.MESSAGE_INVALID_PATIENT_NAME);
    }

    @Test
    public void execute_changeRoomNumberToDuplicateRoomNumber_failure() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Integer duplicateRoomNumber = model.getRoomList().get(1).getRoomNumber();

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(duplicateRoomNumber).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(roomNumberToEdit, descriptor);

        assertCommandFailure(allocateRoomCommand, model, MESSAGE_DUPLICATE_ROOM);
    }

    @Test
    public void equals() {
        Room editRoom7 = model.getRoomList().get(0);
        AllocateRoomCommand.AllocateRoomDescriptor descriptor1 = new EditRoomDescriptorBuilder(editRoom7)
                .withRoomNumber(20).build();
        AllocateRoomCommand editRoomNumberCommand = new AllocateRoomCommand(editRoom7.getRoomNumber(), descriptor1);

        Room editRoom8 = model.getRoomList().get(1);
        AllocateRoomCommand.AllocateRoomDescriptor descriptor2 = new EditRoomDescriptorBuilder(editRoom8)
                .withPatient(CARL.getName()).build();
        AllocateRoomCommand editPatientInRoomCommand = new AllocateRoomCommand(editRoom8.getRoomNumber(), descriptor2);

        // same object -> returns true
        assertTrue(editRoomNumberCommand.equals(editRoomNumberCommand));

        // same values -> returns true
        AllocateRoomCommand editRoomNumberCommandCopy = new AllocateRoomCommand(editRoom7.getRoomNumber(), descriptor1);
        assertTrue(editRoomNumberCommand.equals(editRoomNumberCommandCopy));

        // different types -> returns false
        assertFalse(editRoomNumberCommand.equals(1));

        // null -> returns false
        assertFalse(editRoomNumberCommand.equals(null));

        // different attributes to edit
        assertFalse(editRoomNumberCommand.equals(editPatientInRoomCommand));
    }
}
