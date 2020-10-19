package seedu.address.testutil.stubs;

import java.nio.file.Path;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPatientRecords;
import seedu.address.model.ReadOnlyRoomList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RoomList;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

/**
 * A default model stub that has all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getCovigentAppFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCovigentAppFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatientRecords(ReadOnlyPatientRecords newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyPatientRecords getPatientRecords() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<Patient> getPatientWithName(Name nameOfPatient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePatient(Patient target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isPatientAssignedToRoom(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRoomList(ReadOnlyRoomList rooms) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getNumOfRooms() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRooms(int num) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRoom(Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSingleRoom(Room target, Room editedRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearRoom(Name patientName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index checkIfRoomPresent(Integer roomNumber) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateRoomListWhenPatientsChanges(Patient patientToEdit, Patient editedPatient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Room> getRoomList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public RoomList getModifiableRoomList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public PriorityQueue<Room> getRooms() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Room> getFilteredRoomList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    public void addTaskToRoom(Task task, Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTaskFromRoom(Task task, Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaskToRoom(Task target, Task taskToEdit, Room room) {
        throw new AssertionError("This method should not be called.");
    }

}
