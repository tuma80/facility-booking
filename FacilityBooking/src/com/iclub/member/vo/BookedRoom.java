package com.iclub.member.vo;

import java.util.Date;

public class BookedRoom {

	long trId;
	
	long bookingId;
	
	String fromTime;
	
	String toTime;
	
	Date dateBooked;
	
	String userName;
	
	String roomName;
	
	boolean booked;
	
	boolean owner;
	
	
	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public boolean isBooked() {
		return (userName!=null)? true:false ;
	}
	
	public void setBooked(boolean booked) {
		this.booked = booked;
	}
	
	public long getTrId() {
		return trId;
	}
	public void setTrId(long trId) {
		this.trId = trId;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public Date getDateBooked() {
		return dateBooked;
	}
	public void setDateBooked(Date dateBooked) {
		this.dateBooked = dateBooked;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
}