package com.iclub.room.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.iclub.common.CommonUtils;

public class BookedRoomForm {
	private static final int DATE_UP_TO = 3;
	private String roomName;
	private long roomId;
	private String bookedDate;
	private String selDate;
	private List<String> dateAvails;
	
	
	public String getSelDate() {
		return selDate;
	}

	public void setSelDate(String selDate) {
		this.selDate = selDate;
	}

	public List<String> getDateAvails() {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<DATE_UP_TO;i++){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE,i);
			list.add(CommonUtils.formatDate(calendar.getTime(),"yyyy-MM-dd"));
		}
		return list;
	}
	
	public void setDateAvails(List<String> dateAvails) {
		this.dateAvails = dateAvails;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

	
}
