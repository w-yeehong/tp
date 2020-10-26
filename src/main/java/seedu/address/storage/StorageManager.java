package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPatientRecords;
import seedu.address.model.ReadOnlyRoomList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskList;

/**
 * Manages storage of CovigentApp data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PatientRecordsStorage patientRecordsStorage;
    private UserPrefsStorage userPrefsStorage;
    private JsonRoomOccupancyStorage roomOccupancyStorage;
    private JsonTaskStorage taskStorage;
    /**
     * Creates a {@code StorageManager} with the given {@code PatientRecordsStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PatientRecordsStorage patientRecordsStorage, UserPrefsStorage userPrefsStorage,
                          JsonRoomOccupancyStorage roomOccupancyStorage,
                          JsonTaskStorage taskStorage) {
        super();
        this.patientRecordsStorage = patientRecordsStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.roomOccupancyStorage = roomOccupancyStorage;
        this.taskStorage = taskStorage;
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


    // ================ Patient Records methods ==============================

    @Override
    public Path getPatientRecordsFilePath() {
        return patientRecordsStorage.getPatientRecordsFilePath();
    }

    @Override
    public Optional<ReadOnlyPatientRecords> readPatientRecords() throws DataConversionException, IOException {
        return readPatientRecords(patientRecordsStorage.getPatientRecordsFilePath());
    }

    @Override
    public Optional<ReadOnlyPatientRecords> readPatientRecords(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return patientRecordsStorage.readPatientRecords(filePath);
    }

    @Override
    public void savePatientRecords(ReadOnlyPatientRecords patientRecords) throws IOException {
        savePatientRecords(patientRecords, patientRecordsStorage.getPatientRecordsFilePath());
    }

    @Override
    public void savePatientRecords(ReadOnlyPatientRecords patientRecords, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        patientRecordsStorage.savePatientRecords(patientRecords, filePath);
    }


    @Override
    public void saveRoomList(RoomList roomList) throws IOException {
        roomOccupancyStorage.saveOccupiedRooms(roomList);
    }

    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        taskStorage.saveTask(taskList);
    }

    @Override
    public Optional<ReadOnlyRoomList> readRoomOccupancyStorage() throws DataConversionException, IOException {
        return roomOccupancyStorage.readOnlyRoomOccupancy();
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskStorage() throws DataConversionException, IOException {
        return taskStorage.readOnlyTask();
    }

}
