package seedu.address.logic.commands.room;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.room.InitRoomsCommand.MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT;
import static seedu.address.logic.commands.room.InitRoomsCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.room.InitRoomsCommand.MESSAGE_ZERO_CANNOT_BE_AN_INPUT;

import org.junit.jupiter.api.Test;

import seedu.address.model.CovigentApp;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.RoomList;

class AddRoomsCommandTest {

    @Test
    void execute_addRooms_success() {
        Model model = new ModelManager(new CovigentApp(), new UserPrefs(), new RoomList());
        Model expectedModel = new ModelManager(model.getCovigentApp(), new UserPrefs(), new RoomList());
        expectedModel.addRooms(100);
        String expectedResult = String.format(MESSAGE_SUCCESS, 100);
        assertCommandSuccess(new InitRoomsCommand(100), model, expectedResult, expectedModel);
    }

    @Test
    void execute_addRooms_throwsCommandException() {
        Model model = new ModelManager(new CovigentApp(), new UserPrefs(), new RoomList());
        model.addRooms(0);
        assertCommandFailure(new InitRoomsCommand(0), model, MESSAGE_ZERO_CANNOT_BE_AN_INPUT);
        model.addRooms(-100);
        assertCommandFailure(new InitRoomsCommand(-100), model, MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
    }
}
