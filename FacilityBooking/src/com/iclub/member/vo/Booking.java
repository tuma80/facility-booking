package com.iclub.member.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long bookingId;
	
	@ManyToOne(targetEntity=Room.class)
	@JoinColumn
	Room room;
	
	@ManyToOne(targetEntity=TimeRange.class)
	TimeRange timeRange;
	
	@ManyToOne(targetEntity=AuthenticateUser.class)
	AuthenticateUser userid;
	
	Date dateBooked;

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}

	public AuthenticateUser getUserid() {
		return userid;
	}

	public void setUserid(AuthenticateUser userid) {
		this.userid = userid;
	}

	public Date getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(Date dateBooked) {
		this.dateBooked = dateBooked;
	}


}
