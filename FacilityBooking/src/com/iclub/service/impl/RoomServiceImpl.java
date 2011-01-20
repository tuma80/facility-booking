package com.iclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iclub.dao.BookedRoomDao;
import com.iclub.dao.GenericDao;
import com.iclub.member.vo.BookedRoom;
import com.iclub.member.vo.Room;
import com.iclub.service.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	@Autowired
	GenericDao<Room,Long> roomDao;
	
	@Autowired
	BookedRoomDao bookedRoomDao;
	
	public List<Room> getAllRooms(){
		return roomDao.getAll(Room.class);
	}

	@Transactional
	public void createRoom(Room c) {
		roomDao.create(c);
	}

	@Transactional
	public void update(Room p) {
		roomDao.update(p);
	}

	@Transactional
	public Room getRoom(long id) {
		return roomDao.read(id, Room.class);
	}

	@Transactional
	public void delete(long id) {
		Room room = new Room();
		room.setRoomId(id);
		roomDao.delete(room);
	}
	
	@Transactional
	public List<BookedRoom> getBookedRooms(long id){
		return bookedRoomDao.getBookedRooms(id);
	}
}
