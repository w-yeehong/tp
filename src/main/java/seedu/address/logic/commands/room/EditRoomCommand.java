package seedu.address.logic.commands.room;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.model.Model;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;
import seedu.address.model.room.Room;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_OCCUPIED;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_PATIENT;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

public class EditRoomCommand {

    public static final String COMMAND_WORD = "editroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the room identified "
            + "by the room number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NUMBER"
            + "[" + PREFIX_OCCUPIED + "OCCUPIED] "
            + "[" + PREFIX_ROOM_PATIENT + "PATIENT NAME] "
            + "[" + PREFIX_ROOM_TASK + "TASK] "
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_OCCUPIED + "false "
            + PREFIX_ROOM_PATIENT + "marydoe";

    public static final String MESSAGE_EDIT_ROOM_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_ROOM_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in the application.";

    private final Integer roomNumberToEdit;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * Constructs an EditCommand to edit the patient with the name {@code String}.
     *
     * @param roomToBeEdited name in the filtered patient list to edit
     * @param editRoomDescriptor details to edit the patient with
     */
    public EditRoomCommand(int roomNumberToEdit, EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(roomNumberToEdit);
        requireNonNull(editRoomDescriptor);

        this.roomNumberToEdit = roomNumberToEdit;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Room> lastShownList = model.getRoomList().getRoomObservableList();
        Index index = checkIfRoomPresent(lastShownList);

        if (index.getZeroBased() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Room roomToEdit = lastShownList.get(index.getZeroBased() - 1);
        Room editedRoom = createEditedRoom(roomToEdit, editRoomDescriptor);

        if (!roomToEdit.isSameRoom(editedRoom) && model.(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.setRoom(roomToEdit, editedRoom);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient));
    }

    /**
     * Checks if the room is present in the application.
     *
     * @param lastShownList List of all the room in the application.
     * @return Index Of room who is found.
     */
    private Index checkIfRoomPresent(List<Room> lastShownList) {
        Index index = Index.fromZeroBased(0);
        for (int i = 1; i <= lastShownList.size(); i++) {
            int roomNumber = lastShownList.get(i - 1).getRoomNumber();
            boolean isValidRoom = (Integer.valueOf(roomNumber)).equals(roomNumberToEdit);
            if (isValidRoom) {
                index = Index.fromZeroBased(i);
                break;
            }
        }
        return index;
    }

    /**
     * Creates and returns a {@code Room} with the details of {@code roomToEdit}
     * edited with {@code editRoomDescriptor}.
     *
     * @param roomToEdit Room that is to be edited.
     * @param editRoomDescriptor Details to edit the room with.
     * @return Room that has been edited.
     */
    private static Room createEditedRoom(Room roomToEdit, EditRoomDescriptor editRoomDescriptor) {
        assert roomToEdit != null;

        Integer updatedRoomNumber = editRoomDescriptor.getRoomNumber().orElse(roomToEdit.getRoomNumber());
        Boolean updatedOccupancy = editRoomDescriptor.isOccupied().orElse(roomToEdit.isOccupied());
        Patient updatedPatient = editRoomDescriptor.getPatient().orElse(roomToEdit.getPatient());
        // TODO Update this when task class has been implemented
//        Task UpdatedTask = editRoomDescriptor.getTask().orElse(roomToEdit.getTask());
        return new Room(updatedRoomNumber, updatedOccupancy/*, updatedPatient, updatedTask*/);
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
        private Patient patient;
        // TODO Update this when task class has been implemented
//        private Task task;

        public EditRoomDescriptor() {}


        /**
         * Constructs a EditRoomDescriptor object with the following fields.
         *
         * @param toCopy EditRoomDescriptor to copy the fields from.
         */
        public EditRoomDescriptor(EditRoomDescriptor toCopy) {
            setRoomNumber(toCopy.roomNumber);
            setOccupied(toCopy.isOccupied);
            setPatient(toCopy.patient);
            // TODO Update this when task class has been implemented
//            setTask(toCopy.task);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(roomNumber, isOccupied, patient);
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

        public Optional<Boolean> isOccupied() {
            return Optional.ofNullable(isOccupied);
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public Optional<Patient> getPatient() {
            return Optional.ofNullable(patient);
        }

        // TODO Update this when task class has been implemented
//        public void setTask(Task task) {
//            this.task = task;
//        }
//
//        public Optional<Task> getPatient() {
//            return Optional.ofNullable(task);
//        }

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
                    && isOccupied().equals(e.isOccupied())
                    && getPatient().equals(e.getPatient());
        }
    }
}
