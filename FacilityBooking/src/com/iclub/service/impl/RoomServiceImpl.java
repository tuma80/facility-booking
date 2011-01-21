package com.iclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iclub.common.UserException;
import com.iclub.dao.BookedRoomDao;
import com.iclub.dao.GenericDao;
import com.iclub.member.vo.BookedRoom;
import com.iclub.member.vo.Booking;
import com.iclub.member.vo.Room;
import com.iclub.service.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	@Autowired
	GenericDao<Room,Long> roomCrudDao;
	
	@Autowired
	GenericDao<Booking,Long> bookingCrudDao;	
	
	@Autowired
	BookedRoomDao bookedRoomDao;
	
	public List<Room> getAllRooms(){
		return roomCrudDao.getAll(Room.class);
	}

	@Transactional
	public void createRoom(Room c) {
		roomCrudDao.create(c);
	}

	@Transactional
	public void update(Room p) {
		roomCrudDao.update(p);
	}

	@Transactional
	public Room getRoom(long id) {
		return roomCrudDao.read(id, Room.class);
	}

	@Transactional
	public void delete(long id) throws UserException {
		Room room = new Room();
		room.setRoomId(id);
		if(!canDelete(id))
			throw new UserException("The room is currently used in booking process");
		roomCrudDao.delete(room);
	}
	
	private boolean canDelete(long roomId){
		List result = bookedRoomDao.getBookingByRoomId(roomId);
		if(result.size()<1) return true;
		return false;
	}
	
	@Transactional
	public void undoBooking(long bookingId,long userId){
		bookedRoomDao.deleteBooking(bookingId, userId);
	}
	
	@Transactional
	public List<BookedRoom> getBookedRooms(long id,String date){
		return bookedRoomDao.getBookedRooms(id,date);
	}
}
