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

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CovigentApp covigentApp;
    private final UserPrefs userPrefs;
    private final RoomList roomList;
    private final FilteredList<Patient> filteredPatients;

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

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedCovigentApp}
     */
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

    //=========== RoomList =============================================================

    public PriorityQueue<Room> getRooms() {
        return this.roomList.getRooms();
    }

    public int getNumOfRooms() {
        return this.roomList.getNumOfRooms();
    }

    public void addRooms(int num) {
        roomList.addRooms(num);
    }

    public RoomList getRoomList() {
        return roomList;
    }
}
