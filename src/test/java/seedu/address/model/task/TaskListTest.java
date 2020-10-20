package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(REMIND_PATIENT));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(REMIND_PATIENT);
        assertTrue(taskList.contains(REMIND_PATIENT));
    }

    @Test
    public void contains_sameTaskInList_returnsTrue() {
        taskList.add(REMIND_PATIENT);
        Task editedRemindPatient = new TaskBuilder(REMIND_PATIENT).build();
        assertTrue(taskList.contains(editedRemindPatient));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, REMIND_PATIENT));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(REMIND_PATIENT, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(REMIND_PATIENT, REMIND_PATIENT));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.add(REMIND_PATIENT);
        taskList.setTask(REMIND_PATIENT, REMIND_PATIENT);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(REMIND_PATIENT);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskIsDifferentTask_success() {
        taskList.add(REMIND_PATIENT);
        taskList.setTask(REMIND_PATIENT, RESTOCK_SUPPLY);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(RESTOCK_SUPPLY);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(REMIND_PATIENT));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(REMIND_PATIENT);
        taskList.remove(REMIND_PATIENT);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(REMIND_PATIENT);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(RESTOCK_SUPPLY);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(REMIND_PATIENT);
        List<Task> singleTaskList = Collections.singletonList(RESTOCK_SUPPLY);
        taskList.setTasks(singleTaskList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(RESTOCK_SUPPLY);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toString_emptyTaskList_success() {
        String expectedString = "-";
        assertEquals(taskList.toString(), expectedString);
    }

    @Test
    public void toString_taskListWithTasks_success() {
        taskList.add(REMIND_PATIENT);
        taskList.add(RESTOCK_SUPPLY);

        String expectedString = "1. " + REMIND_PATIENT.toString();
        expectedString += "\n\n";
        expectedString += "2. " + RESTOCK_SUPPLY.toString();
        assertEquals(taskList.toString(), expectedString);
    }
}
