package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CovigentApp;
import seedu.address.testutil.TypicalPatients;

public class JsonSerializableCovigentAppTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCovigentAppTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsCovigentApp.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientCovigentApp.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientCovigentApp.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableCovigentApp dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializableCovigentApp.class).get();
        CovigentApp covigentAppFromFile = dataFromFile.toModelType();
        CovigentApp typicalPatientsCovigentApp = TypicalPatients.getTypicalCovigentApp();
        assertEquals(covigentAppFromFile, typicalPatientsCovigentApp);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCovigentApp dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableCovigentApp.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableCovigentApp dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializableCovigentApp.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCovigentApp.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
