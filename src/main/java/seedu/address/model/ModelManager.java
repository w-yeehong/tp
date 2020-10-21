package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
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
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PatientRecords patientRecords;
    private final RoomList roomList;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Room> filteredRooms;

    /**
     * Initializes a ModelManager with the given patient records, room records and userPrefs.
     */
    public ModelManager(ReadOnlyPatientRecords patientRecords, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyRoomList roomList) {
        super();
        requireAllNonNull(patientRecords, userPrefs);

        logger.fine("Initializing with Covigent App: " + patientRecords + " and user prefs " + userPrefs);

        this.patientRecords = new PatientRecords(patientRecords);
        this.roomList = new RoomList(roomList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.patientRecords.getPatientList());
        filteredRooms = new FilteredList<>(this.roomList.asUnmodifiableObservableList());
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
    @Override
    public void setRoomList(ReadOnlyRoomList rooms) {
        this.roomList.resetData(rooms);
    }


    //=========== Patients ====================================================================================

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patientRecords.hasPatient(patient);
    }

    @Override
    public Optional<Patient> getPatientWithName(Name nameOfPatient) {
        requireNonNull(nameOfPatient);
        return patientRecords.getPatientWithName(nameOfPatient);
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

    @Override
    public boolean isPatientAssignedToRoom(Name name) {
        for (Room room : roomList.getRoomObservableList()) {
            if (room.getPatient() != null) {
                Name patientNameInRoom = room.getPatient().getName();
                if (patientNameInRoom.equals(name)) {
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

    //=========== Room List ========================================================================================

    @Override
    public int getNumOfRooms() {
        return roomList.getNumOfRooms();
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
    public void clearRoom(Name patientName) {
        assert(isPatientAssignedToRoom(patientName));
        roomList.clearRoom(patientName);
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
    public void updateRoomListWhenPatientsChanges(Patient patientToEdit, Patient editedPatient) {
        ObservableList<Room> roomObservableList = this.roomList.getRoomObservableList();
        for (int i = 0; i < roomObservableList.size(); i++) {
            Patient patient = roomObservableList.get(i).getPatient();
            if (isPatientAssignedToRoom(patientToEdit.getName()) && patient != null
                && patient.isSamePatient(patientToEdit)) {
                Room updatedRoom = roomObservableList.get(i);
                if (editedPatient == null) {
                    updatedRoom.setOccupied(false);
                }
                updatedRoom.setPatient(editedPatient);
                roomObservableList.set(i, updatedRoom);
                break;
            }
        }
    }

    //=========== Filtered RoomList Accessors ==========================================================================

    @Override
    public ObservableList<Room> getRoomList() {
        return roomList.asUnmodifiableObservableList();
    }


    @Override
    public RoomList getModifiableRoomList() {
        return roomList;
    }

    @Override
    public PriorityQueue<Room> getRooms() {
        return this.getModifiableRoomList().getRooms();
    }

    @Override
    public ObservableList<Room> getFilteredRoomList() {
        return filteredRooms;
    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        requireNonNull(predicate);
        filteredRooms.setPredicate(predicate);

    }
    //=========== Tasks ========================================================================================

    @Override
    public void addTaskToRoom(Task task, Room room) {
        requireAllNonNull(task, room);
        roomList.addTaskToRoom(task, room);
    }

    @Override
    public void deleteTaskFromRoom(Task task, Room room) {
        requireAllNonNull(task, room);
        roomList.deleteTaskFromRoom(task, room);
    }

    @Override
    public void setTaskToRoom(Task target, Task editedTask, Room room) {
        requireAllNonNull(target, editedTask, room);
        roomList.setTaskToRoom(target, editedTask, room);
    }

    //=========== Miscellaneous ========================================================================================

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
                && roomList.equals(other.roomList)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients)
                && filteredRooms.equals(other.filteredRooms);
    }
}

