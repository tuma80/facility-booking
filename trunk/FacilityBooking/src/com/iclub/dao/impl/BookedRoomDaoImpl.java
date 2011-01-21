package com.iclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.iclub.common.CommonUtils;
import com.iclub.dao.BookedRoomDao;
import com.iclub.dao.GenericDao;
import com.iclub.member.vo.BookedRoom;
import com.iclub.member.vo.Booking;
import com.iclub.member.vo.TimeRange;
import com.iclub.member.vo.UserProfile;

@Repository("bookedRoomDao")
public class BookedRoomDaoImpl implements BookedRoomDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	GenericDao<TimeRange,Long> timeRangeDao;
	
	public List<BookedRoom> getBookedRooms(long id,String date){
		List<BookedRoom> list = new ArrayList<BookedRoom>();
		List<TimeRange> timeRanges = timeRangeDao.getAll(TimeRange.class);
		for(TimeRange timeRange:timeRanges){
			BookedRoom bookedRoom = new BookedRoom();
			Booking booking = null;
			try{
				booking = (Booking)entityManager.createNativeQuery("select * from Booking b where b.time_range = ?1 and b.room = ?2 and date_format(date_booked,'%Y-%m-%d') = ?3",Booking.class)
				.setParameter(1,timeRange.getId())
				.setParameter(2,id)
				.setParameter(3,date)
				.getSingleResult();
				
			}catch (NoResultException e) {
				
			}

			if(booking!=null){
				bookedRoom.setBookingId(booking.getBookingId());
				bookedRoom.setDateBooked(booking.getDateBooked());
				bookedRoom.setRoomName(booking.getRoom().getRoomName());
				bookedRoom.setUserName(booking.getUserid().getUsername());
				if(booking.getUserid().getId()==CommonUtils.getCurrentUserProfile().getUserId()){
					bookedRoom.setOwner(true);
				}
			}
			
			bookedRoom.setFromTime(timeRange.getFromTime());
			bookedRoom.setToTime(timeRange.getToTime());
			bookedRoom.setTrId(timeRange.getId());
			list.add(bookedRoom);
		}
		return list;
	}
	public void deleteBooking(long bookingId,long userId){
		entityManager.createNativeQuery("delete from booking where booking_id=?1 and userid=?2")
		.setParameter(1, bookingId)
		.setParameter(2,userId)
		.executeUpdate();
	}
	public List getBookingByRoomId(long roomId){
		return entityManager.createNativeQuery("select * from booking where room = ?1",Booking.class)
		.setParameter(1, roomId)
		.getResultList();
	}
	
}