package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROOM_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_PATIENT_NAME;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;

/**
 * Edits the details of a room identified by the room number in the app.
 * Allows setting/removing/changing patient in the room and changing the room number of the room.
 */
public class EditRoomCommand extends Command {

    public static final String COMMAND_WORD = "editroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the room identified "
            + "by the room number.\n"
            + "Existing values will be overwritten by the input values. "
            + "To make an occupied room empty, the parameter for patient name should be set to '-'.\n"
            + "Parameters: ROOM NUMBER"
            + "[" + PREFIX_ROOM_NUMBER + "ROOM NUMBER] "
            + "[" + PREFIX_PATIENT_NAME + "PATIENT NAME]\n"
            + "Example: " + COMMAND_WORD + "23 "
            + PREFIX_ROOM_NUMBER + "123 "
            + PREFIX_PATIENT_NAME + "Mary Doe";

    public static final String MESSAGE_EDIT_ROOM_SUCCESS = "Edited Room: %1$s";
    public static final String MESSAGE_ROOM_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in the application.";

    private final Integer roomNumberToEdit;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * Constructs an EditRoomCommand to edit the room with the room number {@code Integer}.
     *
     * @param roomNumberToEdit room number to edit
     * @param editRoomDescriptor details to edit the room with
     */
    public EditRoomCommand(Integer roomNumberToEdit, EditRoomDescriptor editRoomDescriptor) {
        requireAllNonNull(roomNumberToEdit, editRoomDescriptor);
        this.roomNumberToEdit = roomNumberToEdit;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Room> lastShownList = model.getRoomList().getRoomObservableList();
        Index index = model.getRoomList().checkIfRoomPresent(roomNumberToEdit);

        if (index.getZeroBased() == 0) {
            throw new CommandException(MESSAGE_INVALID_ROOM_NOT_FOUND);
        }

        Room roomToEdit = lastShownList.get(index.getZeroBased() - 1);
        Room editedRoom = createEditedRoom(model, roomToEdit, editRoomDescriptor);

        if (!roomToEdit.isSameRoom(editedRoom) && model.containsRoom(editedRoom)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.setSingleRoom(roomToEdit, editedRoom);
        return new CommandResult(String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom));
    }

    /**
     * Creates and returns a {@code Room} with the details of {@code roomToEdit}
     * edited with {@code editRoomDescriptor}.
     *
     * @param model Current model
     * @param roomToEdit Room that is to be edited.
     * @param editRoomDescriptor Details to edit the room with.
     * @return Room that has been edited.
     */
    private static Room createEditedRoom(Model model, Room roomToEdit,
                                         EditRoomDescriptor editRoomDescriptor) throws CommandException {
        assert (roomToEdit != null);

        Integer updatedRoomNumber = editRoomDescriptor.getRoomNumber().orElse(roomToEdit.getRoomNumber());
        Boolean isOccupied = editRoomDescriptor.getIsOccupied().orElse(roomToEdit.isOccupied());
        Boolean hasNewPatient = (editRoomDescriptor.getPatientName().orElse(null) != null);

        if (!isOccupied && !hasNewPatient) { //clear room
            return new Room(updatedRoomNumber);
        }

        Name patientName = editRoomDescriptor.getPatientName().get(); //definitely has name
        List<Patient> patientList = model.getFilteredPatientList();
        Room updatedRoom = isValidPatient(patientList, patientName, updatedRoomNumber);
        return updatedRoom;
    }

    /**
     * Checks if the patient is valid and exists in the application.
     *
     * @param patientList List of the patients in the application.
     * @param patientName Name of the patient.
     * @param updatedRoomNumber Room number of room.
     * @return Room that is updated with the new patient and room number.
     */
    private static Room isValidPatient(List<Patient> patientList, Name patientName,
                                       Integer updatedRoomNumber) throws CommandException {
        for (Patient patient : patientList) {
            String inputPatientName = patientName.toString().trim().toLowerCase();
            String recordName = patient.getName().toString();
            if (recordName.trim().toLowerCase().equals(inputPatientName)) {
                Patient updatedPatient = patient;
                return new Room(updatedRoomNumber, updatedPatient);
            }
        }
        throw new CommandException(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        }

        if (!(other instanceof EditRoomCommand)) { // instanceof handles nulls
            return false;
        }

        EditRoomCommand e = (EditRoomCommand) other; // state check
        return roomNumberToEdit.equals(e.roomNumberToEdit)
                && editRoomDescriptor.equals(e.editRoomDescriptor);
    }

    /**
     * Stores the details to edit the room with. Each non-empty field value will replace the
     * corresponding field value of the room.
     */
    public static class EditRoomDescriptor {
        private Integer roomNumber;
        private Boolean isOccupied;
        private Name patientName;

        public EditRoomDescriptor() {}

        /**
         * Constructs an EditRoomDescriptor object with the following fields.
         *
         * @param toCopy EditRoomDescriptor to copy the fields from.
         */
        public EditRoomDescriptor(EditRoomDescriptor toCopy) {
            setRoomNumber(toCopy.roomNumber);
            setOccupied(toCopy.isOccupied);
            setPatientName(toCopy.patientName);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(roomNumber, patientName, isOccupied);
        }

        public void setRoomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Optional<Integer> getRoomNumber() {
            return Optional.ofNullable(roomNumber);
        }

        public void setOccupied(Boolean isOccupied) {
            this.isOccupied = isOccupied;
        }

        public Optional<Boolean> getIsOccupied() {
            return Optional.ofNullable(isOccupied);
        }

        public void setPatientName(Name patientName) {
            this.patientName = patientName;
        }

        public Optional<Name> getPatientName() {
            return Optional.ofNullable(patientName);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) { // short circuit if same object
                return true;
            }

            if (!(other instanceof EditRoomDescriptor)) { // instanceof handles nulls
                return false;
            }

            EditRoomDescriptor e = (EditRoomDescriptor) other; // state check
            return getRoomNumber().equals(e.getRoomNumber())
                    && getIsOccupied().equals(e.getIsOccupied())
                    && getPatientName().equals(e.getPatientName());
        }
    }
}
