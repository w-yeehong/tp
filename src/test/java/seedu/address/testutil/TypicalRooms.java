package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;

public class TypicalRooms {

    public static final Room ROOM_1 = new RoomBuilder().withRoomNumber(1).withIsOccupied(false).build();
    public static final Room ROOM_2 = new RoomBuilder().withRoomNumber(2).withIsOccupied(true).build();
    public static final Room ROOM_3 = new RoomBuilder().withRoomNumber(3).withIsOccupied(false).build();
    public static final Room ROOM_4 = new RoomBuilder().withRoomNumber(4).withIsOccupied(true).build();

    public static RoomList getTypicalRoomList() {
        RoomList roomList = new RoomList();
        for (Room room : getTypicalRooms()) {
            roomList.addRooms(room);
        }
        return roomList;
    }

    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(ROOM_1, ROOM_2, ROOM_3, ROOM_4));
    }
}
