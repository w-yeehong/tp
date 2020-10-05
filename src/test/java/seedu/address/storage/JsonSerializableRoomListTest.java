package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.room.RoomList;
import seedu.address.testutil.TypicalPatients;
import seedu.address.testutil.TypicalRooms;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializableRoomListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRoomListTest");
    private static final Path TYPICAL_ROOM_LIST = TEST_DATA_FOLDER.resolve("typicalRoomsInRoomList.json");

    @Test
    public void toModelType_typicalRoomFile_success() throws Exception {
        JsonSerializableRoomList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ROOM_LIST,
                JsonSerializableRoomList.class).get();
        RoomList addressBookFromFile = dataFromFile.toModelType();
        RoomList typicalRoomList = TypicalRooms.getTypicalRoomList();
        assertEquals(addressBookFromFile, typicalRoomList);
    }


}