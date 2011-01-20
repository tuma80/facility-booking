package com.iclub.room.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iclub.member.vo.Booking;
import com.iclub.member.vo.Room;
import com.iclub.member.vo.UserProfile;
import com.iclub.service.AuthenticateUserService;
import com.iclub.service.BookingService;
import com.iclub.service.RoomService;
import com.iclub.service.TimeRangeService;

@Controller
public class BookedRoomController {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	TimeRangeService timeRangeService;
	
	@Autowired
	AuthenticateUserService authenticateUserService;
	
	@RequestMapping("list-bookedRoom.do")
	public ModelAndView list(@RequestParam(value="id")long id){
		ModelAndView mv = new ModelAndView("list-bookedRoom");
		BookedRoomForm form = new BookedRoomForm();
		Room room = roomService.getRoom(id);
		form.setRoomName(room.getRoomName());
		form.setRoomId(room.getRoomId());
		mv.addObject("bookedRoomForm",form);
		mv.addObject("bookedRoomList",roomService.getBookedRooms(id));
		return mv;
	}
	
	@RequestMapping(value="/list-booking.do",method=RequestMethod.GET)
	public ModelAndView listRoom() throws Exception{
		ModelAndView mv = new ModelAndView("list-booking");
		mv.addObject("roomList",roomService.getAllRooms());
		return mv;
	}		
	
	@RequestMapping("book-room.do")
	public ModelAndView bookRoom(@RequestParam(value="trIds",required=false)long[] timeRanges,@ModelAttribute("bookedRoomForm")BookedRoomForm form,BindingResult bindingResult, HttpServletRequest request, ModelMap model){
		Room room = roomService.getRoom(form.getRoomId());
		UserProfile profile = (UserProfile)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(long timeRange:timeRanges){
			Booking booking = new Booking();
			booking.setDateBooked(new Date());
			booking.setRoom(room);
			booking.setTimeRange(timeRangeService.getTimeRange(timeRange));
			booking.setUserid(authenticateUserService.getAuthenticateUser(profile.getUserId()));
			bookingService.createBooking(booking);
		}
		
		return list(form.getRoomId());
	}
}
