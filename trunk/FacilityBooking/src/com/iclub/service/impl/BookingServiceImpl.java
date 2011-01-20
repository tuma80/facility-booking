package com.iclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iclub.dao.GenericDao;
import com.iclub.member.vo.Booking;
import com.iclub.service.BookingService;

@Service("bookingService")
public class BookingServiceImpl implements BookingService{
	@Autowired
	GenericDao<Booking,Long> bookingDao;
	
	public List<Booking> getAllBookings(){
		return bookingDao.getAll(Booking.class);
	}

	@Transactional
	public void createBooking(Booking c) {
		bookingDao.create(c);
	}

	@Transactional
	public void update(Booking p) {
		bookingDao.update(p);
	}

	@Transactional
	public Booking getBooking(long id) {
		return bookingDao.read(id, Booking.class);
	}

	@Transactional
	public void delete(long id) {
		Booking booking = new Booking();
		booking.setBookingId(id);
		bookingDao.delete(booking);
	}

}
