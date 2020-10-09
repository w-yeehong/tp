package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyCovigentApp;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.ReadOnlyRoomList;
import seedu.address.model.room.RoomList;

/**
 * Manages storage of CovigentApp data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CovigentAppStorage covigentAppStorage;
    private UserPrefsStorage userPrefsStorage;
    private JsonRoomOccupancyStorage roomOccupancyStorage;
    /**
     * Creates a {@code StorageManager} with the given {@code CovigentAppStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CovigentAppStorage covigentAppStorage, UserPrefsStorage userPrefsStorage,
                          JsonRoomOccupancyStorage roomOccupancyStorage) {
        super();
        this.covigentAppStorage = covigentAppStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.roomOccupancyStorage = roomOccupancyStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ CovigentApp methods ==============================

    @Override
    public Path getCovigentAppFilePath() {
        return covigentAppStorage.getCovigentAppFilePath();
    }

    @Override
    public Optional<ReadOnlyCovigentApp> readCovigentApp() throws DataConversionException, IOException {
        return readCovigentApp(covigentAppStorage.getCovigentAppFilePath());
    }

    @Override
    public Optional<ReadOnlyCovigentApp> readCovigentApp(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return covigentAppStorage.readCovigentApp(filePath);
    }

    @Override
    public void saveCovigentApp(ReadOnlyCovigentApp covigentApp) throws IOException {
        saveCovigentApp(covigentApp, covigentAppStorage.getCovigentAppFilePath());
    }

    @Override
    public void saveCovigentApp(ReadOnlyCovigentApp covigentApp, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        covigentAppStorage.saveCovigentApp(covigentApp, filePath);
    }


    @Override
    public void saveRoomList(RoomList roomList) throws IOException {
        roomOccupancyStorage.saveOccupiedRooms(roomList);
    }

    @Override
    public Optional<ReadOnlyRoomList> readRoomOccupancyStorage() throws DataConversionException, IOException {
        return roomOccupancyStorage.readOnlyRoomOccupancy();
    }

}
