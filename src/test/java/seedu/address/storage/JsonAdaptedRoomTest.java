package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_INVALID_PATIENT_PRESENT_OCCUPIED_FALSE;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

class JsonAdaptedRoomTest {

    @Test
    public void toModelType_returnsEmptyRoom() throws Exception {
        JsonAdaptedRoom room1 = new JsonAdaptedRoom(ROOM_NO_PATIENT_NO_TASK);
        assertEquals(ROOM_NO_PATIENT_NO_TASK, room1.toModelType());
    }

    @Test
    public void toModelType_returnsOccupiedRoom() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_PATIENT_ALICE_NO_TASK);
        assertEquals(ROOM_PATIENT_ALICE_NO_TASK, room.toModelType());
    }

    @Test
    public void toModelType_error() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_INVALID_PATIENT_PRESENT_OCCUPIED_FALSE);
        String expectedMessage = JsonAdaptedRoom.PATIENT_PRESENT_IS_OCCUPIED_FALSE;
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

}
