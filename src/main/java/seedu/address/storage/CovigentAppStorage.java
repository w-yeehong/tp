package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CovigentApp;
import seedu.address.model.ReadOnlyCovigentApp;

/**
 * Represents a storage for {@link CovigentApp}.
 */
public interface CovigentAppStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCovigentAppFilePath();

    /**
     * Returns CovigentApp data as a {@link ReadOnlyCovigentApp}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCovigentApp> readCovigentApp() throws DataConversionException, IOException;

    /**
     * @see #getCovigentAppFilePath()
     */
    Optional<ReadOnlyCovigentApp> readCovigentApp(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCovigentApp} to the storage.
     * @param covigentApp cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCovigentApp(ReadOnlyCovigentApp covigentApp) throws IOException;

    /**
     * @see #saveCovigentApp(ReadOnlyCovigentApp)
     */
    void saveCovigentApp(ReadOnlyCovigentApp covigentApp, Path filePath) throws IOException;

}
