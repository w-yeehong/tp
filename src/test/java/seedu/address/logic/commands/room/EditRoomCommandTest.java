package seedu.address.logic.commands.room;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.testutil.EditRoomDescriptorBuilder;
import seedu.address.testutil.RoomBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROOM_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENT_ALREADY_ASSIGNED;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.room.EditRoomCommand.MESSAGE_DUPLICATE_ROOM;
import static seedu.address.logic.commands.room.EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BOB;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.getTypicalCovigentApp;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

/**
 * Contains integration tests and unit tests for EditRoomCommand.
 */
class EditRoomCommandTest {

    //patient records -> [ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE]
    //room list -> [room 7, Alice; room 8, Benson; room 10, null]
    private Model model = new ModelManager(getTypicalCovigentApp(), new UserPrefs(), getTypicalRoomList());


    @Test
    public void constructor_nullInputs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditRoomCommand(null, null));
    }

    @Test
    public void execute_invalidRoomNumber_failure() {
        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(-100, descriptor);
        assertCommandFailure(editRoomCommand, model, MESSAGE_INVALID_ROOM_NOT_FOUND);
    }

    @Test
    public void execute_changeRoomNumber_success() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToEdit).withRoomNumber(roomNumberToEdit + 10).build();

        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(roomNumberToEdit + 10).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(roomNumberToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);
        Model expectedModel = new ModelManager(getTypicalCovigentApp(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()));
        expectedModel.setSingleRoom(roomToEdit, editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_changePatientInRoom_success() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToEdit).withPatient(CARL).build();

        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withPatient(CARL.getName()).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(roomNumberToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);
        Model expectedModel = new ModelManager(getTypicalCovigentApp(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()));
        expectedModel.setSingleRoom(roomToEdit, editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearRoom_success() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Room editedRoom = new RoomBuilder(roomToEdit).withIsOccupied(false).withPatient(null).build();

        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withOccupancy(false).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(roomNumberToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(getTypicalCovigentApp(), new UserPrefs(),
                new RoomList(model.getModifiableRoomList()));

        expectedModel.setSingleRoom(roomToEdit, editedRoom);
        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allocatePatientAlreadyInAnotherRoom_failure() {
        Room roomToEdit = model.getRoomList().get(2); //the empty room
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Patient patientInAnotherRoom = model.getRoomList().get(0).getPatient();

        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withPatient(patientInAnotherRoom.getName()).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(roomNumberToEdit, descriptor);

        assertCommandFailure(editRoomCommand, model, MESSAGE_PATIENT_ALREADY_ASSIGNED);
    }

    @Test
    public void execute_invalidPatientAllocated_failure() {
        Room roomToEdit =  model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();

        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withPatient(BOB.getName()).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(roomNumberToEdit, descriptor);

        assertCommandFailure(editRoomCommand, model, Messages.MESSAGE_INVALID_PATIENT_NAME_INPUT);
    }

    @Test
    public void execute_changeRoomNumberToDuplicateRoomNumber_failure() {
        Room roomToEdit = model.getRoomList().get(0);
        Integer roomNumberToEdit = roomToEdit.getRoomNumber();
        Integer duplicateRoomNumber = model.getRoomList().get(1).getRoomNumber();

        EditRoomCommand.EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(duplicateRoomNumber).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(roomNumberToEdit, descriptor);

        assertCommandFailure(editRoomCommand, model, MESSAGE_DUPLICATE_ROOM);
    }

    @Test
    public void equals() {
        Room editRoom7 = model.getRoomList().get(0);
        EditRoomCommand.EditRoomDescriptor descriptor1 = new EditRoomDescriptorBuilder(editRoom7)
                .withRoomNumber(20).build();
        EditRoomCommand editRoomNumberCommand = new EditRoomCommand(editRoom7.getRoomNumber(), descriptor1);

        Room editRoom8 = model.getRoomList().get(1);
        EditRoomCommand.EditRoomDescriptor descriptor2 = new EditRoomDescriptorBuilder(editRoom8)
                .withPatient(CARL.getName()).build();
        EditRoomCommand editPatientInRoomCommand = new EditRoomCommand(editRoom8.getRoomNumber(), descriptor2);

        // same object -> returns true
        assertTrue(editRoomNumberCommand.equals(editRoomNumberCommand));

        // same values -> returns true
        EditRoomCommand editRoomNumberCommandCopy = new EditRoomCommand(editRoom7.getRoomNumber(), descriptor1);
        assertTrue(editRoomNumberCommand.equals(editRoomNumberCommandCopy));

        // different types -> returns false
        assertFalse(editRoomNumberCommand.equals(1));

        // null -> returns false
        assertFalse(editRoomNumberCommand.equals(null));

        // different attributes to edit
        assertFalse(editRoomNumberCommand.equals(editPatientInRoomCommand));
    }
}
