package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTasks.CALL_EMBASSY;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import org.junit.jupiter.api.Test;

class JsonAdaptedTaskTest {
    @Test
    public void toModelType_returnsTask_remindPatient() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(REMIND_PATIENT);
        task.toModelType();
        assertEquals(REMIND_PATIENT, task.toModelType());
    }

    @Test
    public void toModelType_returnsTask_restockSupply() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(RESTOCK_SUPPLY);
        assertEquals(RESTOCK_SUPPLY, task.toModelType());
    }

    @Test
    public void toModelType_returnsTask_callEmbassy() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CALL_EMBASSY);
        assertEquals(CALL_EMBASSY, task.toModelType());
    }

}
