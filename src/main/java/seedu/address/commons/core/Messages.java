package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    // common

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_TOO_MANY_COMMANDS = "Too many commands entered!";

    // patients

    public static final String MESSAGE_INVALID_PATIENT_NAME_INPUT = "The patient name provided "
            + "cannot be found in Covigent.";
    public static final String MESSAGE_PATIENT_LISTED_OVERVIEW = "%1$d patient listed!";

    // rooms

    public static final String MESSAGE_INVALID_ROOM_NOT_FOUND = "The room number provided is not found.";
    public static final String MESSAGE_INVALID_ROOM_INDEX = "The room number provided is invalid";
    public static final String NUMBER_OF_ROOMS_GIVEN_IN_DIGITS = "Please give the number of digits in numbers\n"
            + "Example: addRooms 200";
    public static final String MESSAGE_PATIENT_ALREADY_ASSIGNED = "Patient has already been assigned a room.\nPlease"
            + " clear patient from the room first.";
    public static final String MESSAGE_PATIENT_NO_ROOM = "Patient is not assigned to any room yet.";

    // tasks

    public static final String MESSAGE_INVALID_TASK_INDEX = "The task number provided is invalid";
    public static final String MESSAGE_TASK_NOT_EDITED = "Task has not been edited.\nPlease ensure that at least"
            + " one valid and different value has been provided.";

}
