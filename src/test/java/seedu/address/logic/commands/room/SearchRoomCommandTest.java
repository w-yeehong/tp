package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATIENT_NAME_INPUT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROOM_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENT_NO_ROOM;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.room.SearchRoomCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.ROOM7_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM8_PATIENT_BENSON_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_NUMBER_7;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.SearchRoomDescriptorBuilder;


/**
 * Contains unit tests for SearchRoomCommand.
 */
public class SearchRoomCommandTest {
    //patient records -> [ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE]
    //room list -> [room 7, Alice; room 8, Benson; room 10, null]
    private Model model = new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList());
    private Model expectedModel = new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList());

    @Test
    public void constructor_nullRoomNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SearchRoomCommand(null));
    }

    @Test
    public void execute_findInvalidRoomNumber_failure() {
        SearchRoomDescriptorBuilder descriptorRoomNum = new SearchRoomDescriptorBuilder();
        descriptorRoomNum.setRoomNumber(9999);
        SearchRoomCommand searchRoomCommand = new SearchRoomCommand(descriptorRoomNum.build());
        assertCommandFailure(searchRoomCommand, model, MESSAGE_INVALID_ROOM_NOT_FOUND);
    }

    @Test
    public void execute_findInvalidPatientInRoom_failure() {
        //patient name not in Covigent
        SearchRoomDescriptorBuilder descriptorNameNotInApp = new SearchRoomDescriptorBuilder();
        descriptorNameNotInApp.setPatientName(VALID_NAME_BOB);
        SearchRoomCommand noPatientSearchRoomCommand = new SearchRoomCommand(descriptorNameNotInApp.build());
        assertCommandFailure(noPatientSearchRoomCommand, model, MESSAGE_INVALID_PATIENT_NAME_INPUT);

        //patient is not in any room
        SearchRoomDescriptorBuilder descriptorPatientNotInRoom = new SearchRoomDescriptorBuilder();
        descriptorPatientNotInRoom.setPatientName(FIONA.getName().toString());
        SearchRoomCommand patientNotInRoomSearchRoomCommand = new SearchRoomCommand(descriptorPatientNotInRoom.build());
        assertCommandFailure(patientNotInRoomSearchRoomCommand, model, MESSAGE_PATIENT_NO_ROOM);
    }

    @Test
    public void execute_findValidRoomNumber_success() {
        SearchRoomDescriptorBuilder descriptorRoomNum = new SearchRoomDescriptorBuilder();
        descriptorRoomNum.setRoomNumber(ROOM_NUMBER_7);
        SearchRoomCommand searchRoomCommand = new SearchRoomCommand(descriptorRoomNum.build());
        expectedModel.updateFilteredRoomList(room -> room.getRoomNumber() == ROOM_NUMBER_7);
        assertCommandSuccess(searchRoomCommand, model, MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(ROOM7_PATIENT_ALICE_NO_TASK), model.getFilteredRoomList());
    }

    @Test
    public void execute_findValidPatientRoom_success() {
        SearchRoomDescriptorBuilder descriptorPatientName = new SearchRoomDescriptorBuilder();
        descriptorPatientName.setPatientName(BENSON.getName().toString());
        SearchRoomCommand searchRoomCommand = new SearchRoomCommand(descriptorPatientName.build());
        expectedModel.updateFilteredRoomList(room -> room.isOccupied()
                && room.getPatient().getName().equals(BENSON.getName()));
        assertCommandSuccess(searchRoomCommand, model, MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(ROOM8_PATIENT_BENSON_NO_TASK), model.getFilteredRoomList());
    }

    @Test
    public void equals() {
        SearchRoomDescriptorBuilder descriptor1 = new SearchRoomDescriptorBuilder();
        descriptor1.setPatientName(VALID_NAME_BOB);

        SearchRoomCommand searchRoomCommand = new SearchRoomCommand(descriptor1.build());

        SearchRoomDescriptorBuilder descriptor2 = new SearchRoomDescriptorBuilder();
        descriptor2.setRoomNumber(3);

        // same object -> returns true
        assertTrue(searchRoomCommand.equals(searchRoomCommand));

        // same values -> returns true
        SearchRoomCommand searchRoomCommandCopy = new SearchRoomCommand(descriptor1.build());
        assertTrue(searchRoomCommand.equals(searchRoomCommandCopy));

        // different types -> returns false
        assertFalse(searchRoomCommand.equals(1));

        // null -> returns false
        assertFalse(searchRoomCommand.equals(null));

        // different attributes to edit
        assertFalse(searchRoomCommand.equals(new SearchRoomCommand(descriptor2.build())));
    }
}
