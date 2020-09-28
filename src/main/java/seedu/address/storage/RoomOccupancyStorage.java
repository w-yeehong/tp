package seedu.address.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.Scanner;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.RoomBook;
import seedu.address.model.hotel.Room;

/**
 * Reads data from storage data files and imports them into RoomBook
 */
public class RoomOccupancyStorage {
    private Path fileNumOfRooms;
    private Path roomsOccupied;
    private int numberOfRoomsOccupied = -1;
    public RoomOccupancyStorage() {}
    /**
     * Creates RoomOccupancyStorage object that reads the number of rooms a hotel has and the rooms which are
     * occupied
     */
    public RoomOccupancyStorage(Path fileNumOfRooms, Path roomsOccupied) {
        this.fileNumOfRooms = fileNumOfRooms;
        this.roomsOccupied = roomsOccupied;
    }

    public Path getFileNumOfRooms() {
        return fileNumOfRooms;
    }
    public Path getRoomsOccupied() {
        return roomsOccupied;
    }
    /**
     * Reads the 2 files responsible for number rooms and which rooms are occupied
     *
     * @return RoomBook containing information of which rooms are occupied and number of rooms present
     * @throws IOException
     */
    public RoomBook readOnlyRoomOccupancy() throws IOException {
        Scanner scanner = new Scanner(fileNumOfRooms);
        Scanner roomsOccupiedFile = new Scanner(roomsOccupied);
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        Room[] roomsInArray;
        int numOfRooms;
        if (scanner.hasNextLine()) {
            numOfRooms = scanner.nextInt(); //number of rooms in hotel
            roomsInArray = new Room[numOfRooms];
            for (int i = 0; i < numOfRooms; i++) {
                Room room = new Room(i + 1);
                rooms.add(room);
                roomsInArray[i] = room;
            }
            for (int i = 0; i < numberOfRoomsOccupied; i++) {
                String roomNum = roomsOccupiedFile.next();
                int roomNumber = Integer.parseInt(roomNum); //room number that is currently occupied
                Room room = roomsInArray[roomNumber - 1];
                rooms.remove(room);
                room.setOccupied(true);
                roomsInArray[roomNumber - 1] = room;
                rooms.add(room);
            } ;
        } else {
            roomsInArray = null;
            numOfRooms = 0;
        }
        return new RoomBook(rooms, roomsInArray, numOfRooms);
    }

    /**
     * Function saves the number of rooms in file according to the input given by user
     *
     * @param roomBook contains information of number of rooms as input by user
     * @param fileNumOfRooms Path to where to write the number of rooms in
     */
    public void saveNumberOfRooms(RoomBook roomBook, Path fileNumOfRooms) throws IOException {
        int numOfRoom = roomBook.getNumOfRooms();
        FileUtil.createIfMissing(fileNumOfRooms);
        File file = fileNumOfRooms.toFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(Integer.toString(numOfRoom));
        fileWriter.close();
    }

    /**
     * Function saves the room numbers of occupied rooms
     *
     * @param roomBook contains information of which rooms are occupied
     * @param fileRoomsOccupied Path to where to write the room numbers of occupied rooms
     */
    public void saveOccupiedRooms(RoomBook roomBook, Path fileRoomsOccupied) throws IOException {
        FileUtil.createIfMissing(fileRoomsOccupied);
        File file = fileRoomsOccupied.toFile();
        FileWriter fileWriter = new FileWriter(file);
        Room[] rooms = roomBook.getRoomsInArray();
        String string = "";
        numberOfRoomsOccupied = 0;
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room.isOccupied()) {
                numberOfRoomsOccupied++;
                fileWriter.write(Integer.toString(i + 1) + "\n");
            }
        }
        fileWriter.close();
    }
}
