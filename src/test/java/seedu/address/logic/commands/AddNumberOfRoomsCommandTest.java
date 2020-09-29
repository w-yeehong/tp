package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddNumberOfRoomsCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.AddNumberOfRoomsCommand.ZERO_CANNOT_BE_AN_INPUT;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomBook;
import seedu.address.model.UserPrefs;



class AddNumberOfRoomsCommandTest {

    @Test
    void execute_addNumberOfRooms_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomBook());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new RoomBook());
        expectedModel.addNumberOfRooms(100);
        String expectedResult = String.format(MESSAGE_SUCCESS, 100);
        assertCommandSuccess(new AddNumberOfRoomsCommand(100), model, expectedResult, expectedModel);
    }

    @Test
    void execute_addNumberOfRooms_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomBook());
        model.addNumberOfRooms(0);
        assertCommandFailure(new AddNumberOfRoomsCommand(0), model, ZERO_CANNOT_BE_AN_INPUT);
    }
}
