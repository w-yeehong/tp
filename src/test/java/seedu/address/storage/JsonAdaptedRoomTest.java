package seedu.address.storage;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.TypicalPatients.BENSON;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalRooms.ROOM_1;
import static seedu.address.testutil.TypicalRooms.ROOM_2;
import static seedu.address.testutil.TypicalRooms.ROOM_3;
import static seedu.address.testutil.TypicalRooms.ROOM_4;

class JsonAdaptedRoomTest {
    @Test
    public void toModelType_returnsRoom() throws Exception {
        JsonAdaptedRoom room1 = new JsonAdaptedRoom(ROOM_1);
        JsonAdaptedRoom room2 = new JsonAdaptedRoom(ROOM_2);
        JsonAdaptedRoom room3 = new JsonAdaptedRoom(ROOM_3);
        JsonAdaptedRoom room4 = new JsonAdaptedRoom(ROOM_4);

        assertEquals(ROOM_1, room1.toModelType());
        assertEquals(ROOM_2, room2.toModelType());
        assertEquals(ROOM_3, room3.toModelType());
        assertEquals(ROOM_4, room4.toModelType());
    }

}