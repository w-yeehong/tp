package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.TEMP_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.RoomParserUtil.MESSAGE_INVALID_NUMBER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.SearchRoomCommand;
import seedu.address.model.patient.Name;
import seedu.address.testutil.SearchRoomDescriptorBuilder;

public class SearchRoomCommandParserTest {

    private SearchRoomCommandParser parser = new SearchRoomCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchRoomCommand.MESSAGE_USAGE));

        //name and room number prefixes not found
        assertParseFailure(parser, TEMP_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchRoomCommand.MESSAGE_USAGE));

        //multiple prefixes found
        assertParseFailure(parser, VALID_NAME_AMY_DESC + ROOM_NUMBER_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, TEMP_DESC_AMY + ROOM_NUMBER_DESC_ONE + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        //invalid patient name prefix
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        //invalid room number prefix
        assertParseFailure(parser, INVALID_NON_NUMBER_ROOM_NUMBER_DESC, MESSAGE_INVALID_NUMBER);
    }

    @Test
    public void parse_validInput_success() {
        SearchRoomDescriptorBuilder descriptorName = new SearchRoomDescriptorBuilder();
        descriptorName.setPatientName(VALID_NAME_AMY);
        assertParseSuccess(parser, NAME_DESC_AMY, new SearchRoomCommand(descriptorName.build()));

        SearchRoomDescriptorBuilder descriptorRoomNum = new SearchRoomDescriptorBuilder();
        descriptorRoomNum.setRoomNumber(Integer.parseInt(VALID_ROOM_NUMBER_ONE));
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE, new SearchRoomCommand(descriptorRoomNum.build()));
    }
}
