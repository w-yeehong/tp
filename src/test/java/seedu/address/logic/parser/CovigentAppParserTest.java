package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.TASK_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.commands.patient.DeletePatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.logic.commands.patient.ListPatientCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class CovigentAppParserTest {

    private final CovigentAppParser parser = new CovigentAppParser();

    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PatientUtil.getAddPatientCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                TaskUtil.getAddTaskCommand(task, VALID_ROOM_INDEX_ONE));
        assertEquals(new AddTaskCommand(task, VALID_ROOM_INDEX_ONE), command);
    }

    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " "
                + patient.getName() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(patient.getName(), descriptor), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        Task task = new TaskBuilder().build();

        EditTaskDescriptor descriptor = new EditTaskDescriptor();
        descriptor.setDescription(task.getDescription());
        descriptor.setDateTimeDue(task.getDueAt());

        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(
                TaskUtil.getEditTaskCommand(task, VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE));
        assertEquals(new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor), command);
    }

    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " " + "Alice Pauline");
        assertEquals(new DeletePatientCommand(new Name("Alice Pauline")), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE);
        assertEquals(new DeleteTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD) instanceof ListPatientCommand);
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD + " 3") instanceof ListPatientCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
