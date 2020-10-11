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
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PatientRecords patientRecords;
    private final RoomList rooms;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Room> filteredRooms;

    /**
     * Initializes a ModelManager with the given patient records, room records and userPrefs.
     */
    public ModelManager(ReadOnlyPatientRecords patientRecords, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyRoomList rooms) {
        super();
        requireAllNonNull(patientRecords, userPrefs);

        logger.fine("Initializing with Covigent App: " + patientRecords + " and user prefs " + userPrefs);

        this.patientRecords = new PatientRecords(patientRecords);
        this.rooms = new RoomList(rooms);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.patientRecords.getPatientList());
        filteredRooms = new FilteredList<>(this.rooms.asUnmodifiableObservableList());
    }

    public ModelManager() {
        this(new PatientRecords(), new UserPrefs(), new RoomList());
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

    //=========== Patient Records ================================================================================

    @Override
    public void setPatientRecords(ReadOnlyPatientRecords patientRecords) {
        this.patientRecords.resetData(patientRecords);
    }

    @Override
    public ReadOnlyPatientRecords getPatientRecords() {
        return patientRecords;
    }

    //=========== RoomList ================================================================================
    public void setRoomList(ReadOnlyRoomList rooms) {
        this.rooms.resetData(rooms);
    }


    //=========== Patients ====================================================================================

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patientRecords.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        patientRecords.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        patientRecords.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        patientRecords.setPatient(target, editedPatient);
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

        return patientRecords.equals(other.patientRecords)
                && rooms.equals(other.rooms)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients)
                && filteredRooms.equals(other.filteredRooms);
    }

    //=========== Room List ========================================================================================

    @Override
    public int getNumOfRooms() {
        return rooms.getNumOfRooms();
    }

    @Override
    public void addRooms(int num) {
        rooms.addRooms(num);
    }

    //=========== RoomList Accessors ==========================================================================

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    // TODO: remove this method and use getRoomList() instead (I will need this modifableRoomList for editing though)
    @Override
    public RoomList getModifiableRoomList() {
        return rooms;
    }

    @Override
    public PriorityQueue<Room> getRooms() {
        return this.getModifiableRoomList().getRooms();
    }

    @Override
    public boolean hasRoom(Room room) {
        requireAllNonNull(room);
        return rooms.containsRoom(room);
    }

    @Override
    public void setSingleRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);
        rooms.setSingleRoom(target, editedRoom);
    }

    //=========== Tasks ========================================================================================

    @Override
    public void addTaskToRoom(Task task, Room room) {
        requireAllNonNull(task, room);
        rooms.addTaskToRoom(task, room);
    }

}
