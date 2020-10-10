package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_NO_TASK;

import org.junit.jupiter.api.Test;

class JsonAdaptedRoomTest {

    @Test
    public void toModelType_returnsRoom() throws Exception {
        JsonAdaptedRoom room1 = new JsonAdaptedRoom(ROOM_NO_PATIENT_NO_TASK);

        assertEquals(ROOM_NO_PATIENT_NO_TASK, room1.toModelType());
    }

}
