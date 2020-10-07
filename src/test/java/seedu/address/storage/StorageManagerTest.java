package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPatients.getTypicalCovigentApp;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CovigentApp;
import seedu.address.model.ReadOnlyCovigentApp;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.ReadOnlyRoomList;
import seedu.address.model.room.RoomList;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCovigentAppStorage covigentAppStorage = new JsonCovigentAppStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonRoomOccupancyStorage roomOccupancyStorage = new JsonRoomOccupancyStorage(getTempFilePath("ro"));
        //files nr short for numberOfRooms and ro is short form for roomsOccupied.
        storageManager = new StorageManager(covigentAppStorage, userPrefsStorage, roomOccupancyStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void CovigentAppReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCovigentAppStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCovigentAppStorageTest} class.
         */
        CovigentApp original = getTypicalCovigentApp();
        storageManager.saveCovigentApp(original);
        ReadOnlyCovigentApp retrieved = storageManager.readCovigentApp().get();
        assertEquals(original, new CovigentApp(retrieved));
    }

    @Test
    public void getCovigentAppFilePath() {
        assertNotNull(storageManager.getCovigentAppFilePath());
    }

    @Test
    public void saveRoomOccupancyStorage() throws IOException, DataConversionException {
        RoomList original = getTypicalRoomList();
        storageManager.saveRoomList(original);
        ReadOnlyRoomList readOnlyRoomList = storageManager.readRoomOccupancyStorage().get();
        assertEquals(original, readOnlyRoomList);
    }
}
