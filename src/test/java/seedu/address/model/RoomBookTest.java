package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import seedu.address.model.hotel.Room;



class RoomBookTest {

    @Test
    void testEquals() {
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        Room[] arrayOfRooms = new Room[10];
        RoomBook roomBook1 = new RoomBook(rooms, arrayOfRooms, 10);
        RoomBook roomBook2 = new RoomBook(rooms, arrayOfRooms, 10);
        //same object -> returns true
        assertTrue(roomBook1.equals(roomBook1));

        //diff object, but same fields -> returns true
        assertTrue(roomBook1.equals(roomBook2));

        //null object -> returns false
        assertFalse(roomBook1.equals(null));

        roomBook2 = new RoomBook(rooms, arrayOfRooms, 10);
        PriorityQueue<Room> rooms1 = new PriorityQueue<>();
        rooms1.add(new Room(1));
        roomBook2.setRooms(rooms1);
        //different Priority Queue of rooms -> returns false
        assertFalse(roomBook1.equals(roomBook2));

        roomBook2 = new RoomBook(rooms, arrayOfRooms, 10);
        roomBook2.setNumOfRooms(100);
        //different number of rooms -> returns false
        assertFalse(roomBook1.equals(roomBook2));

        roomBook2 = new RoomBook(rooms, arrayOfRooms, 10);
        roomBook2.setRoomsInArray(new Room[100]);
        //different array of rooms -> returns false
        assertFalse(roomBook1.equals(roomBook2));
    }

    @Test
    void testPriorityQueueEquals() {
        RoomBook roomBook = new RoomBook();
        PriorityQueue<Room> rooms1 = new PriorityQueue<>();
        PriorityQueue<Room> rooms2 = new PriorityQueue<>();

        //same PriorityQueue -> returns true
        assertTrue(roomBook.equals(rooms1, rooms1));

        //2 empty PriorityQueue of rooms -> returns true
        assertTrue(roomBook.equals(rooms1, rooms2));

        for (int i = 0; i < 10; i++) {
            Room room = new Room(i);
            rooms1.add(room);
            rooms2.add(room);
        }


        //2 PriorityQueue of rooms with same content -> returns true
        assertTrue(roomBook.equals(rooms1, rooms2));

        for (int i = 0; i < 10; i++) {
            Room room = new Room(i);
            rooms1.add(room);
            rooms2.add(room);
        }

        Room room = rooms2.poll();

        //2 PriorityQueue of different size -> returns false
        assertFalse(roomBook.equals(rooms1, rooms2));

        for (int i = 0; i < 10; i++) {
            Room r = new Room(i);
            rooms1.add(r);
            rooms2.add(r);
        }
        rooms2.add(room);
        rooms1.poll();
        rooms1.add(new Room(100));
        //2 PriorityQueue of different content -> returns false
        assertFalse(roomBook.equals(rooms1, rooms2));

    }
}
