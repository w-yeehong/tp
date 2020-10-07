package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;
import static seedu.address.testutil.TypicalPatients.getTypicalCovigentApp;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CovigentApp;
import seedu.address.model.ReadOnlyCovigentApp;

public class JsonCovigentAppStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCovigentAppStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCovigentApp_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCovigentApp(null));
    }

    private java.util.Optional<ReadOnlyCovigentApp> readCovigentApp(String filePath) throws Exception {
        return new JsonCovigentAppStorage(Paths.get(filePath)).readCovigentApp(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCovigentApp("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCovigentApp("notJsonFormatCovigentApp.json"));
    }

    @Test
    public void readCovigentApp_invalidPatientCovigentApp_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCovigentApp("invalidPatientCovigentApp.json"));
    }

    @Test
    public void readCovigentApp_invalidAndValidPatientCovigentApp_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCovigentApp("invalidAndValidPatientCovigentApp.json"));
    }

    @Test
    public void readAndSaveCovigentApp_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCovigentApp.json");
        CovigentApp original = getTypicalCovigentApp();
        JsonCovigentAppStorage jsonCovigentAppStorage = new JsonCovigentAppStorage(filePath);

        // Save in new file and read back
        jsonCovigentAppStorage.saveCovigentApp(original, filePath);
        ReadOnlyCovigentApp readBack = jsonCovigentAppStorage.readCovigentApp(filePath).get();
        assertEquals(original, new CovigentApp(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonCovigentAppStorage.saveCovigentApp(original, filePath);
        readBack = jsonCovigentAppStorage.readCovigentApp(filePath).get();
        assertEquals(original, new CovigentApp(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonCovigentAppStorage.saveCovigentApp(original); // file path not specified
        readBack = jsonCovigentAppStorage.readCovigentApp().get(); // file path not specified
        assertEquals(original, new CovigentApp(readBack));

    }

    @Test
    public void saveCovigentApp_nullCovigentApp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCovigentApp(null, "SomeFile.json"));
    }

    /**
     * Saves {@code covigentApp} at the specified {@code filePath}.
     */
    private void saveCovigentApp(ReadOnlyCovigentApp covigentApp, String filePath) {
        try {
            new JsonCovigentAppStorage(Paths.get(filePath))
                    .saveCovigentApp(covigentApp, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCovigentApp_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCovigentApp(new CovigentApp(), null));
    }
}
