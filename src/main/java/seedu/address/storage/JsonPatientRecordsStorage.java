package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyPatientRecords;

/**
 * A class to access CovigentApp data stored as a json file on the hard disk.
 */
public class JsonPatientRecordsStorage implements PatientRecordsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPatientRecordsStorage.class);

    private Path filePath;

    public JsonPatientRecordsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPatientRecordsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPatientRecords> readPatientRecords()
            throws DataConversionException {
        return readPatientRecords(filePath);
    }

    /**
     * Similar to {@link #readPatientRecords()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPatientRecords> readPatientRecords(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePatientRecords> jsonCovigentApp = JsonUtil.readJsonFile(
                filePath, JsonSerializablePatientRecords.class);
        if (!jsonCovigentApp.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCovigentApp.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePatientRecords(ReadOnlyPatientRecords covigentApp) throws IOException {
        savePatientRecords(covigentApp, filePath);
    }

    /**
     * Similar to {@link #savePatientRecords(ReadOnlyPatientRecords)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePatientRecords(ReadOnlyPatientRecords covigentApp, Path filePath) throws IOException {
        requireNonNull(covigentApp);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePatientRecords(covigentApp), filePath);
    }

}
