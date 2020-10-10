package seedu.address.model;

import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' covigent app file path.
     */
    Path getCovigentAppFilePath();

    /**
     * Sets the user prefs' covigent app file path.
     */
    void setCovigentAppFilePath(Path covigentAppFilePath);

    /**
     * Replaces address book data with the data in {@code covigentApp}.
     */
    void setPatientRecords(ReadOnlyPatientRecords covigentApp);

    /** Returns the CovigentApp */
    ReadOnlyPatientRecords getPatientRecords();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the address book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code UniquePatientList}.
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Returns total number of rooms in the application's {@code RoomList}.
     */
    int getNumOfRooms();

    void addRooms(int num);

    /**
     * Returns an unmodifiable view of the list of {@code Room} backed by the internal list of
     * {@code RoomList}.
     */
    ObservableList<Room> getRoomList();

    RoomList getModifiableRoomList();

    /**
     * Returns Priority Queue of rooms
     */
    PriorityQueue<Room> getRooms();

    void addTaskToRoom(Task task, Room room);

    /**
     * Returns true if a room with the same identity as {@code room} exists in the application.
     */
    boolean hasRoom(Room room);

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in the application.
     * The room identity of {@code editedRoom} must not be the same as
     * another existing room in the application.
     */
    void setSingleRoom(Room target, Room editedRoom);

}
