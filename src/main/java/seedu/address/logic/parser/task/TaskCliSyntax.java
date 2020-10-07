package seedu.address.logic.parser.task;

import seedu.address.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple task-related commands
 */
public class TaskCliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_ROOM_NUMBER = new Prefix("r/");
    public static final Prefix PREFIX_DUE_DATE = new Prefix("dd/");

}
