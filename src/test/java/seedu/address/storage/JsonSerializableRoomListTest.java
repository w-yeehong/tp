package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.RoomList;
import seedu.address.testutil.TypicalRooms;

class JsonSerializableRoomListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRoomListTest");
    private static final Path TYPICAL_ROOM_LIST = TEST_DATA_FOLDER.resolve("typicalRoomsInRoomList.json");

    @Test
    public void toModelType_typicalRoomFile_success() throws Exception {
        JsonSerializableRoomList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ROOM_LIST,
                JsonSerializableRoomList.class).get();
        RoomList covigentAppFromFile = dataFromFile.toModelType();
        RoomList typicalRoomList = TypicalRooms.getTypicalRoomList();
        System.out.println(covigentAppFromFile.getRoomObservableList());
        System.out.println(typicalRoomList.getRoomObservableList());
        assertEquals(covigentAppFromFile, typicalRoomList);
    }

}


