package com.iclub.dao;

import java.util.List;

import com.iclub.member.vo.BookedRoom;

public interface BookedRoomDao {
	public List<BookedRoom> getBookedRooms(long id,String date);
	public void deleteBooking(long bookingId,long userId);
	public List getBookingByRoomId(long roomId);
}
