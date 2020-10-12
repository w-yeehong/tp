package seedu.address.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalTasks.*;

class JsonAdaptedTaskTest {
    @Test
    public void toModelType_returnsTask_RemindPatient() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(REMIND_PATIENT);
        assertEquals(REMIND_PATIENT, task.toModelType());
    }

    @Test
    public void toModelType_returnsTask_RestockSupply() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(RESTOCK_SUPPLY);
        assertEquals(RESTOCK_SUPPLY, task.toModelType());
    }

    @Test
    public void toModelType_returnsTask_CallEmbassy() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CALL_EMBASSY);
        assertEquals(CALL_EMBASSY, task.toModelType());
    }

}