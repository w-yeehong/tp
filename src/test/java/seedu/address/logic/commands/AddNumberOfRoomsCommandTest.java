package seedu.address.logic.commands;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomBook;
import seedu.address.model.UserPrefs;



class AddNumberOfRoomsCommandTest {

    @Test
    void execute() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomBook());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new RoomBook());
        expectedModel.addNumberOfRooms(100);
        String expectedResult = "100 rooms are added in a hotel";
        assertCommandSuccess(new AddNumberOfRoomsCommand(100), model, expectedResult, expectedModel);
    }
}
