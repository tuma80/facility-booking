package com.iclub.service;

import java.util.List;

import com.iclub.common.UserException;
import com.iclub.member.vo.BookedRoom;
import com.iclub.member.vo.Room;

public interface RoomService {
	void createRoom(Room c);
	void update(Room p);
	List<Room> getAllRooms();
	Room getRoom(long id);
	void delete(long id)throws UserException;
	List<BookedRoom> getBookedRooms(long id,String date);
	void undoBooking(long bookingId,long userId);
}
