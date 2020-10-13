package seedu.address.model.util;

import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyPatientRecords;
import seedu.address.model.ReadOnlyRoomList;
import seedu.address.model.RoomList;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;
import seedu.address.model.room.Room;

/**
 * Contains utility methods for populating {@code CovigentApp} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatient() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Temperature("36.7"), new PeriodOfStay("20200908-20200918"),
                    new Phone("87438807"), new Age("23"), null),
            new Patient(new Name("Bernice Yu"), new Temperature("37.0"), new PeriodOfStay("20200808-20200819"),
                    new Phone("99272758"), new Age("37"), null),
            new Patient(new Name("Charlotte Oliveiro"), new Temperature("38.0"), new PeriodOfStay("20200301-20200309"),
                    new Phone("93210283"), new Age("87"), null),
            new Patient(new Name("David Li"), new Temperature("35.8"), new PeriodOfStay("20201001-20201014"),
                    new Phone("91031282"), new Age("13"), null),
            new Patient(new Name("Irfan Ibrahim"), new Temperature("37.7"), new PeriodOfStay("20200901-20200915"),
                    new Phone("92492021"), new Age("65"), null)
        };
    }

    public static ReadOnlyPatientRecords getSampleCovigentApp() {
        PatientRecords sampleAb = new PatientRecords();
        for (Patient samplePatient : getSamplePatient()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    // TODO
    public static Room[] getSampleRoom() {
        return new Room[] {};
    }

    public static ReadOnlyRoomList getSampleRoomList() {
        RoomList roomList = new RoomList();
        for (Room room : getSampleRoom()) {
            roomList.addRooms(room);
        }
        return roomList;
    }
}
