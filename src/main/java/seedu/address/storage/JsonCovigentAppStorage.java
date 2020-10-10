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
import seedu.address.model.ReadOnlyCovigentApp;

/**
 * A class to access CovigentApp data stored as a json file on the hard disk.
 */
public class JsonCovigentAppStorage implements CovigentAppStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCovigentAppStorage.class);

    private Path filePath;

    public JsonCovigentAppStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCovigentAppFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCovigentApp> readCovigentApp() throws DataConversionException {
        return readCovigentApp(filePath);
    }

    /**
     * Similar to {@link #readCovigentApp()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCovigentApp> readCovigentApp(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCovigentApp> jsonCovigentApp = JsonUtil.readJsonFile(
                filePath, JsonSerializableCovigentApp.class);
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
    public void saveCovigentApp(ReadOnlyCovigentApp covigentApp) throws IOException {
        saveCovigentApp(covigentApp, filePath);
    }

    /**
     * Similar to {@link #saveCovigentApp(ReadOnlyCovigentApp)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCovigentApp(ReadOnlyCovigentApp covigentApp, Path filePath) throws IOException {
        requireNonNull(covigentApp);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCovigentApp(covigentApp), filePath);
    }

}
