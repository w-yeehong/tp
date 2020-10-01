package seedu.address.logic.commands.room;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;

class FindRoomCommandTest {

    @Test
    void execute_numberOfRooms_notDefined() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomList());

        assertCommandFailure(new FindRoomCommand(), model, FindRoomCommand.NUMBER_OF_ROOMS_UNDEFINED);
    }

    @Test
    void execute_numberOfRooms_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new RoomList());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new RoomList());
        model.addRooms(100);
        expectedModel.addRooms(100);
        String expectedMessage = String.format(FindRoomCommand.MESSAGE_SUCCESS, new Room(1));
        assertCommandSuccess(new FindRoomCommand(), model, expectedMessage, expectedModel);
    }
}
