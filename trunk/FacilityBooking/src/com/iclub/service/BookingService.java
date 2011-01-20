package com.iclub.service;

import java.util.List;

import com.iclub.member.vo.Booking;

public interface BookingService {
	void createBooking(Booking c);
	void update(Booking p);
	List<Booking> getAllBookings();
	Booking getBooking(long id);
	void delete(long id);
}
