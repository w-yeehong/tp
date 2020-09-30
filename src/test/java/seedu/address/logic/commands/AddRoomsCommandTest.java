package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddRoomsCommand.MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT;
import static seedu.address.logic.commands.AddRoomsCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.AddRoomsCommand.MESSAGE_ZERO_CANNOT_BE_AN_INPUT;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;



class AddRoomsCommandTest {

    @Test
    void execute_addRooms_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomList());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new RoomList());
        expectedModel.addRooms(100);
        String expectedResult = String.format(MESSAGE_SUCCESS, 100);
        assertCommandSuccess(new AddRoomsCommand(100), model, expectedResult, expectedModel);
    }

    @Test
    void execute_addRooms_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomList());
        model.addRooms(0);
        assertCommandFailure(new AddRoomsCommand(0), model, MESSAGE_ZERO_CANNOT_BE_AN_INPUT);
        model.addRooms(-100);
        assertCommandFailure(new AddRoomsCommand(-100), model, MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
    }
}
