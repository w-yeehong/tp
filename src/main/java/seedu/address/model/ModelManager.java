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
import seedu.address.commons.core.index.Index;
import seedu.address.model.patient.Name;
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

    private final CovigentApp covigentApp;
    private final UserPrefs userPrefs;
    private final RoomList roomList; // TODO: remove roomList from ModelManager and use the list in AddressBook
    private final FilteredList<Patient> filteredPatients;
    private Room findRoom;
    /**
     * Initializes a ModelManager with the given covigentApp and userPrefs.
     */
    public ModelManager(ReadOnlyCovigentApp covigentApp, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyRoomList readOnlyRoomOccupancy) {
        super();
        requireAllNonNull(covigentApp, userPrefs);

        logger.fine("Initializing with address book: " + covigentApp + " and user prefs " + userPrefs);

        this.covigentApp = new CovigentApp(covigentApp);
        this.userPrefs = new UserPrefs(userPrefs);
        this.roomList = new RoomList(readOnlyRoomOccupancy);
        filteredPatients = new FilteredList<>(this.covigentApp.getPatientList());
    }

    public ModelManager() {
        this(new CovigentApp(), new UserPrefs(), new RoomList());
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
    public Path getCovigentAppFilePath() {
        return userPrefs.getCovigentAppFilePath();
    }

    @Override
    public void setCovigentAppFilePath(Path covigentAppFilePath) {
        requireNonNull(covigentAppFilePath);
        userPrefs.setCovigentAppFilePath(covigentAppFilePath);
    }

    //=========== CovigentApp ================================================================================

    @Override
    public void setCovigentApp(ReadOnlyCovigentApp covigentApp) {
        this.covigentApp.resetData(covigentApp);
    }

    @Override
    public ReadOnlyCovigentApp getCovigentApp() {
        return covigentApp;
    }

    //=========== Patients ====================================================================================

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return covigentApp.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        covigentApp.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        covigentApp.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        covigentApp.setPatient(target, editedPatient);
    }

    @Override
    public boolean isPatientAssignedToRoom(Name name) {
        for (Room room : roomList.getRoomObservableList()) {
            if (room.getPatient() != null) {
                String patientNameInRoom = room.getPatient().getName().toString().trim().toLowerCase();
                String patientNameToBeEdit = name.toString().trim().toLowerCase();
                if (patientNameInRoom.equals(patientNameToBeEdit)) {
                    return true;
                }
            }
        }
        return false;
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

        return covigentApp.equals(other.covigentApp)
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

    @Override
    public boolean hasRoom(Room room) {
        requireAllNonNull(room);
        return roomList.containsRoom(room);
    }

    @Override
    public void setSingleRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);
        roomList.setSingleRoom(target, editedRoom);
    }

    @Override
    public Index checkIfRoomPresent(Integer roomNumber) {
        ObservableList<Room> roomObservableList = this.getRoomList();
        Index index = Index.fromZeroBased(0);
        for (int i = 1; i <= roomObservableList.size(); i++) {
            int roomNum = roomObservableList.get(i - 1).getRoomNumber();
            boolean isValidRoom = (Integer.valueOf(roomNum)).equals(roomNumber);
            if (isValidRoom) {
                index = Index.fromZeroBased(i);
                break;
            }
        }
        return index;
    }

    @Override
    public void displayFindRoom(Room room) {
        roomList.displayFindRoomUpdate(room);
    }

    @Override
    public void displayAllRoom () {
        roomList.displayAllRooms();
    }

    @Override
    public ObservableList<Room> getRoomDisplayRoom() {
        return roomList.getRoomDisplayList();
    }

    //=========== RoomList Accessors ==========================================================================

    @Override
    public ObservableList<Room> getRoomList() {
        return roomList.asUnmodifiableObservableList();
    }

    // TODO: remove this method and use getRoomList() instead
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

}
