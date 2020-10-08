package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.ReadOnlyRoomList;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final RoomList roomList; // TODO: remove roomList from ModelManager and use the list in AddressBook
    private final FilteredList<Patient> filteredPatients;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyRoomList readOnlyRoomOccupancy) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.roomList = new RoomList(readOnlyRoomOccupancy);
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new RoomList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== Patients ====================================================================================

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        addressBook.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPatient(target, editedPatient);
    }

    //=========== Filtered Patient List Accessors =============================================================

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients)
                && roomList.equals(other.roomList);
    }

    //=========== Rooms ========================================================================================

    @Override
    public int getNumOfRooms() {
        return this.roomList.getNumOfRooms();
    }

    @Override
    public void addRooms(int num) {
        roomList.addRooms(num);
    }

    //=========== RoomList Accessors ==========================================================================

    @Override
    public ObservableList<Room> getRoomList() {
        return roomList.asUnmodifiableObservableList();
    }

    // TODO: remove this method and use getRoomList() instead (I will need this modifableRoomList for editing though)
    @Override
    public RoomList getModifiableRoomList() {
        return roomList;
    }

    @Override
    public PriorityQueue<Room> getRooms() {
        return this.roomList.getRooms();
    }

    //=========== Tasks ========================================================================================

    @Override
    public void addTaskToRoom(Task task, Room room) {
        requireAllNonNull(task, room);

        roomList.addTaskToRoom(task, room);
    }
    /**
     * Checks if the roomList contains {@code room}.
     *
     * @param room That is to be searched for.
     * @return True if roomList contains {@code room}.
     */
    public boolean hasRoom(Room room) {
        requireAllNonNull(room);
        return roomList.containsRoom(room);
    }

    public void setSingleRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);
        roomList.setSingleRoom(target, editedRoom);
    }
}
