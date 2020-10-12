package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyPatientRecords;

/**
 * Represents a storage for {@link PatientRecords}.
 */
public interface PatientRecordsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPatientRecordsFilePath();

    /**
     * Returns CovigentApp data as a {@link ReadOnlyPatientRecords}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPatientRecords> readPatientRecords() throws DataConversionException, IOException;

    /**
     * @see #getPatientRecordsFilePath()
     */
    Optional<ReadOnlyPatientRecords> readPatientRecords(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPatientRecords} to the storage.
     * @param patientRecords cannot be null.
     *
     * @throws IOException if there was any problem writing to the file.
     */
    void savePatientRecords(ReadOnlyPatientRecords patientRecords) throws IOException;

    /**
     * @see #savePatientRecords(ReadOnlyPatientRecords)
     */
    void savePatientRecords(ReadOnlyPatientRecords patientRecords, Path filePath) throws IOException;

}
