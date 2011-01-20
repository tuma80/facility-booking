package com.iclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iclub.dao.BookedRoomDao;
import com.iclub.dao.GenericDao;
import com.iclub.member.vo.BookedRoom;
import com.iclub.member.vo.Booking;
import com.iclub.member.vo.TimeRange;

@Repository("bookedRoomDao")
public class BookedRoomDaoImpl implements BookedRoomDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	GenericDao<TimeRange,Long> timeRangeDao;
	
	public List<BookedRoom> getBookedRooms(long id){
		List<BookedRoom> list = new ArrayList<BookedRoom>();
		List<TimeRange> timeRanges = timeRangeDao.getAll(TimeRange.class);
		for(TimeRange timeRange:timeRanges){
			BookedRoom bookedRoom = new BookedRoom();
			Booking booking = null;
			try{
				booking = (Booking)entityManager.createQuery("select b from Booking b where b.timeRange.id = :tr and b.room.roomId = :rd",Booking.class)
				.setParameter("tr",timeRange.getId())
				.setParameter("rd",id).getSingleResult();
			}catch (NoResultException e) {
				
			}

			if(booking!=null){
				bookedRoom.setDateBooked(booking.getDateBooked());
				bookedRoom.setRoomName(booking.getRoom().getRoomName());
				bookedRoom.setUserName(booking.getUserid().getUsername());
			}
			bookedRoom.setFromTime(timeRange.getFromTime());
			bookedRoom.setToTime(timeRange.getToTime());
			bookedRoom.setTrId(timeRange.getId());
			list.add(bookedRoom);
		}
		return list;
	}
}